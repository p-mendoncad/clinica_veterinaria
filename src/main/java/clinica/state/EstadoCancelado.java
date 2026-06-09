package clinica.state;

import clinica.model.Atendimento;

public class EstadoCancelado implements AtendimentoEstado {
    @Override
    public void iniciar(Atendimento atendimento) {
        throw new IllegalStateException("O atendimento foi cancelado e não pode ser iniciado.");
    }

    @Override
    public void finalizar(Atendimento atendimento) {
        throw new IllegalStateException("O atendimento foi cancelado e não pode ser finalizado.");
    }

    @Override
    public void cancelar(Atendimento atendimento) {
        throw new IllegalStateException("O atendimento já está cancelado.");
    }
}
