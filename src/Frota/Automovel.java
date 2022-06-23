package Frota;

public class Automovel extends Veiculo {

    private static final long serialVersionUID = 1L;

    private String motor;

    public Automovel(String marca, String modelo, int ano, int quilometragem, String placa, String motor) {
        super(marca, modelo, ano, quilometragem, placa);
        this.motor = motor;
        this.tipo = "Carro";
    }

    @Override
    public String imprimir() {
        return super.imprimir() + "Motor: \t\t\t" + this.motor + "\n";
    }

}
