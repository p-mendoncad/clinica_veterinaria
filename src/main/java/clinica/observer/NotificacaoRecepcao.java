package clinica.observer;

import clinica.model.Atendimento;
import clinica.state.EstadoFinalizado;

public class NotificacaoRecepcao implements Interessado {
    @Override
    public void atualizar(Atendimento atendimento) {
        if (atendimento.getEstado() instanceof EstadoFinalizado) {
            System.out.println("Aviso Recepção: O atendimento do " + atendimento.getAnimal().getNome() + " foi finalizado.");
        }
    }
}
