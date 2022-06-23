package Frota;

public class Onibus extends Veiculo {

    private static final long serialVersionUID = 1L;

    private int passageiros;

    public Onibus(String marca, String modelo, int ano, int quilometragem, String placa, int passageiros) {
        super(marca, modelo, ano, quilometragem, placa);
        this.passageiros = passageiros;
        this.tipo = "Onibus";
    }

    @Override
    public String imprimir() {
        return super.imprimir() + "Passageiros: \t" + this.passageiros + "\n";
    }

}
