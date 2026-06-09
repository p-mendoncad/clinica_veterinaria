package clinica.decorator;

public abstract class ServicoDecorator implements ServicoVeterinario {
    protected ServicoVeterinario servicoDecorado;

    public ServicoDecorator(ServicoVeterinario servicoDecorado) {
        this.servicoDecorado = servicoDecorado;
    }

    @Override
    public double getValor() {
        return servicoDecorado.getValor();
    }

    @Override
    public String getDescricao() {
        return servicoDecorado.getDescricao();
    }
}
