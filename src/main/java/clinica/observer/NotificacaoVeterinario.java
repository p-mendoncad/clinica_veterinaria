package clinica.observer;

import clinica.model.Atendimento;
import clinica.state.EstadoCancelado;

public class NotificacaoVeterinario implements Interessado {
    @Override
    public void atualizar(Atendimento atendimento) {
        if (atendimento.getEstado() instanceof EstadoCancelado) {
            System.out.println("Aviso Veterinário: O atendimento do " + atendimento.getAnimal().getNome() + " foi cancelado.");
        }
    }
}
