package clinica.state;

import clinica.model.Atendimento;

public class EstadoFinalizado implements AtendimentoEstado {
    @Override
    public void iniciar(Atendimento atendimento) {
        throw new IllegalStateException("O atendimento já foi finalizado.");
    }

    @Override
    public void finalizar(Atendimento atendimento) {
        throw new IllegalStateException("O atendimento já foi finalizado.");
    }

    @Override
    public void cancelar(Atendimento atendimento) {
        throw new IllegalStateException("Não é possível cancelar um atendimento finalizado.");
    }
}
