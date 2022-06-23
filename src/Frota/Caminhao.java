package Frota;

public class Caminhao extends Veiculo {

    private static final long serialVersionUID = 1L;

    private int cargaMaxima;

    public Caminhao(String marca, String modelo, int ano, int quilometragem, String placa, int cargaMaxima) {
        super(marca, modelo, ano, quilometragem, placa);
        this.cargaMaxima = cargaMaxima;
        this.tipo = "Caminhao";
    }

    @Override
    public String imprimir() {
        return super.imprimir() + "Carga maxima: \t" + this.cargaMaxima + "t\n";
    }

}
