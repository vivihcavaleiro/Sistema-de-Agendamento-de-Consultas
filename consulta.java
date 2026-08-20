public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private String data;
    private String horario;

    public Consulta(Paciente paciente, Medico medico, String data, String horario) {
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.horario = horario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public void exibirConsulta() {
        System.out.println("Paciente: " + paciente.getNome());
        System.out.println("Médico: " + medico.getNome());
        System.out.println("Especialidade: " + medico.getEspecialidade());
        System.out.println("Data: " + data);
        System.out.println("Horário: " + horario);
    }
}
