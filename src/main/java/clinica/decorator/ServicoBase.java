package clinica.decorator;

public class ServicoBase implements ServicoVeterinario {
    private double valorBase;
    private String descricao;

    public ServicoBase(String descricao, double valorBase) {
        this.descricao = descricao;
        this.valorBase = valorBase;
    }

    @Override
    public double getValor() {
        return valorBase;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }
}
