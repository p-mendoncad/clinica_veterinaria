package clinica.decorator;

import clinica.model.Animal;

public class DescontoAnimalAdotado extends ServicoDecorator {
    private Animal animal;

    public DescontoAnimalAdotado(ServicoVeterinario servicoDecorado, Animal animal) {
        super(servicoDecorado);
        this.animal = animal;
    }

    @Override
    public double getValor() {
        double valor = super.getValor();
        if (animal.isAdotado()) {
            return valor * 0.9; // 10% de desconto
        }
        return valor;
    }

    @Override
    public String getDescricao() {
        if (animal.isAdotado()) {
            return super.getDescricao() + " (Desconto Animal Adotado)";
        }
        return super.getDescricao();
    }
}
