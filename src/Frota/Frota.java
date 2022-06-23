package Frota;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JOptionPane;

public class Frota {
    private ArrayList<Veiculo> veiculos;

    public Frota () {
        this.veiculos = new ArrayList<>();
    }
    public String[] leValores (String [] dadosEntrada){
        String [] dadosSaida = new String [dadosEntrada.length];

        for (int i = 0; i < dadosEntrada.length; i++)
            dadosSaida[i] = JOptionPane.showInputDialog  ("Entre com " + dadosEntrada[i]+ ": ");

        return dadosSaida;
    }

    public Veiculo adicionaVeiculo(String tipo) {
        String [] valores = new String [6];
        String [] nomeVal = {"Marca", "Modelo", "Ano", "Quilometragem", "Placa", (Objects.equals(tipo, "Carro") ? "Motor" :
                (Objects.equals(tipo, "Caminhao") ? "Carga Máxima" : "Passageiros"))};
        valores = leValores (nomeVal);

        int ano = this.checaNumeroInteiro(valores[2], "Entre com um valor para ANO:\n");
        int quilometragem = this.checaNumeroInteiro(valores[3], "Entre com um valor para QUILOMETRAGEM:\n");

        if (tipo == "Carro") {
            return new Automovel(valores[0],valores[1],ano,quilometragem,valores[4],valores[5]);
        } else if (tipo == "Caminhao") {
            int cargaMaxima = this.checaNumeroInteiro(valores[5], "Entre com um valor para CARGA MÁXIMA:\n");
            return new Caminhao(valores[0],valores[1],ano,quilometragem,valores[4],cargaMaxima);
        } else {
            int passageiro = this.checaNumeroInteiro(valores[5], "Entre com um valor para PASSAGEIROS:\n");
            return new Onibus(valores[0],valores[1],ano,quilometragem,valores[4],passageiro);
        }
    }

    public int checaNumeroInteiro(String entradaDoUsuario, String menu) {
        while (true) {
            try {
                return Integer.parseInt(entradaDoUsuario);
            } catch (NumberFormatException e) {
                entradaDoUsuario = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um valor inteiro.\n\n" + menu);
            }
        }
    }

    public void limparFrota() {
        this.veiculos.clear();
    }

    public void salvarVeiculos(ArrayList<Veiculo> veiculos) {
        ObjectOutputStream streamArquivo = null;
        try {
            streamArquivo = new ObjectOutputStream(new FileOutputStream(".\\frotaApp.dat"));
            for (Veiculo item : veiculos) streamArquivo.writeObject(item);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Não foi possível criar o arquivo.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (streamArquivo != null) {
                    streamArquivo.flush();
                    streamArquivo.close();
                    JOptionPane.showMessageDialog(null,"Arquivo gravado com sucesso.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Veiculo> carregarVeiculos() {
        ArrayList<Veiculo> veiculosTemporario = new ArrayList<Veiculo>();

        ObjectInputStream streamArquivo = null;

        try {
            streamArquivo = new ObjectInputStream(new FileInputStream(".\\frotaApp.dat"));
            Object objeto = null;
            while ((objeto = streamArquivo.readObject()) != null) {
                if (objeto instanceof Veiculo) {
                    veiculosTemporario.add((Veiculo) objeto);
                }
            }
        } catch (EOFException e) {
            System.out.println("Arquivo finalizado.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (streamArquivo != null) {
                    streamArquivo.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return veiculosTemporario;
        }
    }

    public void menuFrotaApp() {
        String menu = "";
        String entradaDoUsuario;
        String informacaoVeiculo = "";
        int opcao1, opcao2;

        do {
            menu = "- Controle de Frota\n" +
                    "Opções disponíveis:\n" +
                    "1. Entrada de veículo\n" +
                    "2. Exibir veículos\n" +
                    "3. Excluir veículo\n" +
                    "4. Limpar veículos\n" +
                    "5. Salvar veículos\n" +
                    "6. Carregar veículos\n" +
                    "9. Sair";
            entradaDoUsuario = JOptionPane.showInputDialog (menu + "\n\n");
            opcao1 = this.checaNumeroInteiro(entradaDoUsuario, menu);

            switch (opcao1) {
                case 1:
                    menu = "- Entrada de veículos na frota\n" +
                    "Opções:\n" +
                    "1. Carro\n" +
                    "2. Caminhao\n" +
                    "3. Onibus\n";
                    entradaDoUsuario = JOptionPane.showInputDialog (menu + "\n\n");
                    opcao2 = this.checaNumeroInteiro(entradaDoUsuario, menu);

                    switch (opcao2) {
                        case 1:
                            veiculos.add((Veiculo)adicionaVeiculo("Carro"));
                            break;
                        case 2:
                            veiculos.add((Veiculo)adicionaVeiculo("Caminhao"));
                            break;
                        case 3:
                            veiculos.add((Veiculo)adicionaVeiculo("Onibus"));
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    if (veiculos.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Não há veículos em sua frota.");
                        break;
                    }
                    informacaoVeiculo = "";
                    for (Veiculo item:veiculos) {
                        informacaoVeiculo += item.imprimir();
                    }
                    JOptionPane.showMessageDialog(null, informacaoVeiculo);
                    break;
                case 3:
                    if (veiculos.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Não há veículos em sua frota.");
                        break;
                    }
                    informacaoVeiculo = "- Selecione o veículo a ser excluído:\n0. Sair\n";
                    for (int i = 0; i<veiculos.size(); i++) {
                        informacaoVeiculo += (i+1) + ". " + veiculos.get(i).tipo +
                                " - Modelo: " + veiculos.get(i).getModelo() +
                                " - Ano: " + veiculos.get(i).getAno() +
                                "\n";
                    }
                    while (true) {
                        entradaDoUsuario = JOptionPane.showInputDialog(informacaoVeiculo);
                        if (entradaDoUsuario.equals("0")) { break; }
                        opcao2 = this.checaNumeroInteiro(entradaDoUsuario, informacaoVeiculo) - 1;
                        try {
                            veiculos.remove(opcao2);
                            JOptionPane.showMessageDialog(null, "Veículo removido com sucesso.");
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            JOptionPane.showMessageDialog(null,"Digite um número válido.");
                        }
                    }
                    break;
                case 4:
                    if (veiculos.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Não há veículos em sua frota.");
                        break;
                    }
                    limparFrota();
                    JOptionPane.showMessageDialog(null,"Veículos excluídos com sucesso!");
                    break;
                case 5:
                    if (veiculos.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Não há veículos em sua frota.");
                        break;
                    }
                    salvarVeiculos(veiculos);
                    break;
                case 6:
                    veiculos = carregarVeiculos();
                    if (veiculos.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Não há veículos a serem carregados.");
                        break;
                    }
                    JOptionPane.showMessageDialog(null, "Dados carregados com sucesso.");
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null,"Aplicativo encerrado.");
            }

        } while (opcao1 != 9);
    }

    public static void main(String[] args) {
        Frota frota = new Frota();
        frota.menuFrotaApp();
    }
}