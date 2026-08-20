import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Paciente> pacientes = new ArrayList<>();
    private static ArrayList<Medico> medicos = new ArrayList<>();
    private static ArrayList<Consulta> consultas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;

        do {
            exibirMenu();
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarPaciente();
                    break;
                case 2:
                    cadastrarMedico();
                    break;
                case 3:
                    agendarConsulta();
                    break;
                case 4:
                    listarPacientes();
                    break;
                case 5:
                    listarMedicos();
                    break;
                case 6:
                    listarConsultas();
                    break;
                case 7:
                    cancelarConsulta();
                    break;
                case 8:
                    System.out.println("\nSaindo do sistema... Até logo!");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
        } while (opcao != 8);
    }

    private static void exibirMenu() {
        System.out.println("\n====================================");
        System.out.println("       CLÍNICA VIDA SAUDÁVEL        ");
        System.out.println("====================================");
        System.out.println("1 - Cadastrar paciente");
        System.out.println("2 - Cadastrar médico");
        System.out.println("3 - Agendar consulta");
        System.out.println("4 - Listar pacientes");
        System.out.println("5 - Listar médicos");
        System.out.println("6 - Listar consultas");
        System.out.println("7 - Cancelar consulta");
        System.out.println("8 - Sair");
        System.out.print("\nDigite uma opção: ");
    }

    private static void cadastrarPaciente() {
        System.out.println("\n--- CADASTRO DE PACIENTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = Integer.parseInt(scanner.nextLine());
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        pacientes.add(new Paciente(nome, cpf, idade, telefone));
        System.out.println("Paciente cadastrado com sucesso!");
    }

    private static void cadastrarMedico() {
        System.out.println("\n--- CADASTRO DE MÉDICO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CRM: ");
        String crm = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();

        medicos.add(new Medico(nome, crm, especialidade));
        System.out.println("Médico cadastrado com sucesso!");
    }

    private static void agendarConsulta() {
        System.out.println("\n--- AGENDAMENTO DE CONSULTA ---");
        System.out.print("Digite o CPF do paciente: ");
        String cpf = scanner.nextLine();
        
        Paciente pacienteEncontrado = null;
        for (Paciente p : pacientes) {
            if (p.getCpf().equalsIgnoreCase(cpf)) {
                pacienteEncontrado = p;
                break;
            }
        }

        if (pacienteEncontrado == null) {
            System.out.println("Erro: Paciente não encontrado com o CPF informado!");
            return;
        }

        System.out.print("Digite o CRM do médico: ");
        String crm = scanner.nextLine();

        Medico medicoEncontrado = null;
        for (Medico m : medicos) {
            if (m.getCrm().equalsIgnoreCase(crm)) {
                medicoEncontrado = m;
                break;
            }
        }

        if (medicoEncontrado == null) {
            System.out.println("Erro: Médico não encontrado com o CRM informado!");
            return;
        }

        System.out.print("Digite a data (dd/mm/aaaa): ");
        String data = scanner.nextLine();
        System.out.print("Digite o horário (hh:mm): ");
        String horario = scanner.nextLine();

        // Validação de conflito de agenda (Regra de Negócio)
        for (Consulta c : consultas) {
            if (c.getMedico().getCrm().equalsIgnoreCase(crm) &&
                c.getData().equalsIgnoreCase(data) &&
                c.getHorario().equalsIgnoreCase(horario)) {
                
                System.out.println("\n====================================");
                System.out.println("ERRO NO AGENDAMENTO");
                System.out.println("====================================");
                System.out.println("O médico já possui uma consulta");
                System.out.println("agendada neste dia e horário.");
                System.out.println("\nEscolha outro horário.");
                return;
            }
        }

        Consulta novaConsulta = new Consulta(pacienteEncontrado, medicoEncontrado, data, horario);
        consultas.add(novaConsulta);

        System.out.println("\n====================================");
        System.out.println("       CONSULTA AGENDADA            ");
        System.out.println("====================================");
        novaConsulta.exibirConsulta();
        System.out.println("Consulta realizada com sucesso!");
    }

    private static void listarPacientes() {
        System.out.println("\n========= PACIENTES CADASTRADOS =========");
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.print((i + 1) + ". ");
            pacientes.get(i).exibiInfo();
        }
    }

    private static void listarMedicos() {
        System.out.println("\n========= MÉDICOS CADASTRADOS =========");
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }
        for (int i = 0; i < medicos.size(); i++) {
            System.out.print((i + 1) + ". ");
            medicos.get(i).exibiInfo();
        }
    }

    private static void listarConsultas() {
        System.out.println("\n========= CONSULTAS AGENDADAS =========");
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta agendada.");
            return;
        }
        for (int i = 0; i < consultas.size(); i++) {
            System.out.println((i + 1) + ".");
            consultas.get(i).exibirConsulta();
            if (i < consultas.size() - 1) {
                System.out.println("----------------------------------------");
            }
        }
    }

    private static void cancelarConsulta() {
        System.out.println("\n--- CANCELAR CONSULTA ---");
        if (consultas.isEmpty()) {
            System.out.println("Não há consultas agendadas para cancelar.");
            return;
        }

        listarConsultas();
        System.out.print("\nDigite o número da consulta: ");
        int numero = Integer.parseInt(scanner.nextLine());

        int indice = numero - 1;
        if (indice >= 0 && indice < consultas.size()) {
            consultas.remove(indice);
            System.out.println("Consulta cancelada com sucesso!");
        } else {
            System.out.println("Número de consulta inválido!");
        }
    }
}
