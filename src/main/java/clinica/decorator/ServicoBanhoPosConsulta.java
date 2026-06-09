package clinica.decorator;

public class ServicoBanhoPosConsulta extends ServicoDecorator {
    private static final double VALOR_BANHO = 30.0;

    public ServicoBanhoPosConsulta(ServicoVeterinario servicoDecorado) {
        super(servicoDecorado);
    }

    @Override
    public double getValor() {
        return super.getValor() + VALOR_BANHO;
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " + Banho Pós-Consulta";
    }
}
