package clinica.state;

import clinica.model.Atendimento;

public class EstadoEmAtendimento implements AtendimentoEstado {
    @Override
    public void iniciar(Atendimento atendimento) {
        throw new IllegalStateException("O atendimento já está em andamento.");
    }

    @Override
    public void finalizar(Atendimento atendimento) {
        atendimento.setEstado(new EstadoFinalizado());
        atendimento.notificarInteressados();
    }

    @Override
    public void cancelar(Atendimento atendimento) {
        throw new IllegalStateException("Não é possível cancelar um atendimento que já está em andamento.");
    }
}
