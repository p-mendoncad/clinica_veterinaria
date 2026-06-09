package clinica.observer;

import clinica.model.Atendimento;
import clinica.state.EstadoEmAtendimento;

public class NotificacaoTutor implements Interessado {
    @Override
    public void atualizar(Atendimento atendimento) {
        if (atendimento.getEstado() instanceof EstadoEmAtendimento) {
            System.out.println("Aviso Tutor: O atendimento do " + atendimento.getAnimal().getNome() + " foi iniciado.");
        }
    }
}
