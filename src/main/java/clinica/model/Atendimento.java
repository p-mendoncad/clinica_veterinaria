package clinica.model;

import clinica.decorator.ServicoVeterinario;
import clinica.observer.Interessado;
import clinica.state.AtendimentoEstado;
import clinica.state.EstadoAgendado;

import java.util.ArrayList;
import java.util.List;

public class Atendimento {
    private Tutor tutor;
    private Animal animal;
    private ServicoVeterinario servico;
    private AtendimentoEstado estado;
    private List<Interessado> interessados;

    public Atendimento(Tutor tutor, Animal animal, ServicoVeterinario servico) {
        this.tutor = tutor;
        this.animal = animal;
        this.servico = servico;
        this.estado = new EstadoAgendado(); // Inicia como agendado
        this.interessados = new ArrayList<>();
    }

    public void setEstado(AtendimentoEstado estado) {
        this.estado = estado;
    }

    public AtendimentoEstado getEstado() {
        return estado;
    }

    public void adicionarInteressado(Interessado interessado) {
        this.interessados.add(interessado);
    }

    public void removerInteressado(Interessado interessado) {
        this.interessados.remove(interessado);
    }

    public void notificarInteressados() {
        for (Interessado interessado : interessados) {
            interessado.atualizar(this);
        }
    }

    public void iniciarAtendimento() {
        estado.iniciar(this);
    }

    public void finalizarAtendimento() {
        estado.finalizar(this);
    }

    public void cancelarAtendimento() {
        estado.cancelar(this);
    }

    public double getValorFinal() {
        return servico.getValor();
    }

    public String getDescricaoServico() {
        return servico.getDescricao();
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Animal getAnimal() {
        return animal;
    }
}
