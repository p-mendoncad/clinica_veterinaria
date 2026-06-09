package clinica;

import clinica.decorator.*;
import clinica.model.Animal;
import clinica.model.Atendimento;
import clinica.model.Tutor;
import clinica.observer.Interessado;
import clinica.state.EstadoCancelado;
import clinica.state.EstadoEmAtendimento;
import clinica.state.EstadoFinalizado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AtendimentoTest {

    private Tutor tutor;
    private Animal animal;
    private ServicoVeterinario servicoBase;
    private Atendimento atendimento;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        tutor = new Tutor("João", "1199999999");
        animal = new Animal("Rex", "Cachorro", true);
        servicoBase = new ServicoBase("Consulta Geral", 100.0);
        atendimento = new Atendimento(tutor, animal, servicoBase);
        
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testMudancaValidaDeSituacao() {
        // Agendado -> EmAtendimento
        atendimento.iniciarAtendimento();
        assertTrue(atendimento.getEstado() instanceof EstadoEmAtendimento);

        // EmAtendimento -> Finalizado
        atendimento.finalizarAtendimento();
        assertTrue(atendimento.getEstado() instanceof EstadoFinalizado);
    }

    @Test
    public void testTentativaDeMudancaInvalida() {
        // Não pode finalizar antes de iniciar
        assertThrows(IllegalStateException.class, () -> atendimento.finalizarAtendimento());

        // Inicia
        atendimento.iniciarAtendimento();
        
        // Não pode cancelar em andamento
        assertThrows(IllegalStateException.class, () -> atendimento.cancelarAtendimento());
        
        // Não pode iniciar se já iniciou
        assertThrows(IllegalStateException.class, () -> atendimento.iniciarAtendimento());

        // Finaliza
        atendimento.finalizarAtendimento();

        // Não pode cancelar se finalizou
        assertThrows(IllegalStateException.class, () -> atendimento.cancelarAtendimento());
    }

    @Test
    public void testEnvioAutomaticoDeAviso() {
        clinica.observer.NotificacaoTutor obsTutor = new clinica.observer.NotificacaoTutor();
        clinica.observer.NotificacaoRecepcao obsRecepcao = new clinica.observer.NotificacaoRecepcao();
        clinica.observer.NotificacaoVeterinario obsVet = new clinica.observer.NotificacaoVeterinario();

        atendimento.adicionarInteressado(obsTutor);
        atendimento.adicionarInteressado(obsRecepcao);
        atendimento.adicionarInteressado(obsVet);

        atendimento.iniciarAtendimento();
        assertTrue(outputStreamCaptor.toString().contains("Aviso Tutor: O atendimento do Rex foi iniciado."));
        
        atendimento.finalizarAtendimento();
        assertTrue(outputStreamCaptor.toString().contains("Aviso Recepção: O atendimento do Rex foi finalizado."));
    }

    @Test
    public void testCalculoDoValorFinalComMultiplasRegras() {
        // Base 100.0
        ServicoVeterinario servico = new ServicoBase("Consulta", 100.0);
        
        // Animal adotado -> 10% desconto = 90.0
        servico = new DescontoAnimalAdotado(servico, animal);
        
        // Taxa domiciliar = +50.0 = 140.0
        servico = new TaxaAtendimentoDomiciliar(servico);
        
        // Banho = +30.0 = 170.0
        servico = new ServicoBanhoPosConsulta(servico);

        Atendimento atendimentoCustom = new Atendimento(tutor, animal, servico);
        assertEquals(170.0, atendimentoCustom.getValorFinal(), 0.01);
        
        String desc = atendimentoCustom.getDescricaoServico();
        assertTrue(desc.contains("Consulta"));
        assertTrue(desc.contains("Desconto Animal Adotado"));
        assertTrue(desc.contains("Taxa Domiciliar"));
        assertTrue(desc.contains("Banho"));
    }
}
