package clinica.state;

import clinica.model.Atendimento;

public interface AtendimentoEstado {
    void iniciar(Atendimento atendimento);
    void finalizar(Atendimento atendimento);
    void cancelar(Atendimento atendimento);
}
