package clinica.decorator;

public class TaxaAtendimentoDomiciliar extends ServicoDecorator {
    private static final double TAXA = 50.0;

    public TaxaAtendimentoDomiciliar(ServicoVeterinario servicoDecorado) {
        super(servicoDecorado);
    }

    @Override
    public double getValor() {
        return super.getValor() + TAXA;
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Taxa Domiciliar";
    }
}
