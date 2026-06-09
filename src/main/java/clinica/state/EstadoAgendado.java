package clinica.state;

import clinica.model.Atendimento;

public class EstadoAgendado implements AtendimentoEstado {
    @Override
    public void iniciar(Atendimento atendimento) {
        atendimento.setEstado(new EstadoEmAtendimento());
        atendimento.notificarInteressados();
    }

    @Override
    public void finalizar(Atendimento atendimento) {
        throw new IllegalStateException("Não é possível finalizar um atendimento que ainda não foi iniciado.");
    }

    @Override
    public void cancelar(Atendimento atendimento) {
        atendimento.setEstado(new EstadoCancelado());
        atendimento.notificarInteressados();
    }
}
