package Frota;

import java.io.Serial;
import java.io.Serializable;

public class Veiculo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String marca;
    private String modelo;
    private int ano;
    private int quilometragem;
    private String placa;
    protected String tipo;

    public Veiculo(String marca, String modelo, int ano, int quilometragem, String placa) {
        this.ano = ano;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa.toUpperCase();
        this.quilometragem = quilometragem;
    }

    public String getModelo() {
        return this.modelo;
    }

    public int getAno() {
        return this.ano;
    }

    public String imprimir() {
        String retorno = "======= ";
        retorno += this.tipo;
        if (this.tipo.equals("Caminhao")) {
            retorno += " =========\n";;
        } else if (this.tipo.equals("Onibus")) {
            retorno += " ===========\n";
        } else {
            retorno += " ============\n";
        }
        retorno += "Ano: \t\t\t"     + this.ano     + "\n";
        retorno += "Marca: \t\t\t"    + this.marca    + "\n";
        retorno += "Modelo: \t\t"     + this.modelo     + "\n";
        retorno += "Placa: \t\t\t"  + this.placa  + "\n";
        retorno += "Quilometragem: \t"  + this.quilometragem  + " km\n";

        return retorno;
    }
}
