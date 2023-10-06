

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Dados {

    String pais;
    int confirmed;
    int deaths;
    int recovery;
    int active;

    // Construtor para inicializar os dados de um país
    public Dados(String pais, int confirmed, int deaths, int recovery, int active) {
        this.pais = pais;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovery = recovery;
        this.active = active;
    }

    // Métodos de acesso aos atributos da classe
    public String getPais() {
        return pais;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovery() {
        return recovery;
    }

    public int getActive() {
        return active;
    }

}

class Main {

    // Método responsável por processar os dados e exibir resultados
    public static void tratamento_dados(int n1, int n2, int n3, int n4) {

        int posicao = 0; // Variável auxiliar para acompanhar a posição na lista 'dados_arquivo'
        int soma = 0; // Variável para armazenar a soma dos 'active' dos países com 'confirmed' >= n1
        String arquivoCSV = "dados.csv"; // Nome do arquivo CSV a ser lido
        String linha; // Variável para armazenar cada linha do arquivo
        String separador = ","; // Separador utilizado no arquivo CSV

        List<Dados> dados_arquivo = new ArrayList<>(); // Lista para armazenar os dados lidos do arquivo

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            // Lendo o arquivo CSV linha por linha e mapeando cada linha para um objeto da
            // classe Dados
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);

                // Salvando os dados lidos em uma linha do arquivo em uma variável auxiliar
                Dados auxiliar = new Dados(dados[0].trim(), Integer.parseInt(dados[1].trim()),
                        Integer.parseInt(dados[2].trim()), Integer.parseInt(dados[3].trim()),
                        Integer.parseInt(dados[4].trim()));

                dados_arquivo.add(auxiliar); // Adicionando o objeto 'auxiliar' à lista 'dados_arquivo'

                // Calculando a soma dos 'active' dos países com 'confirmed' maior ou igual a n1
                if (dados_arquivo.get(posicao).getConfirmed() >= n1)
                    soma = soma + dados_arquivo.get(posicao).getActive();

                posicao++; // Incrementando a posição na lista 'dados_arquivo'
            }

            // Ordenando a lista 'dados_arquivo' em ordem decrescente com base no campo
            // 'active'
            dados_arquivo.sort(Comparator.comparing(Dados::getActive).reversed());

            // Obtendo os n2 países com maior valor de 'active' e ordenando-os em ordem
            // crescente com base no campo 'confirmed'
            List<Dados> maioresActive = new ArrayList<>(dados_arquivo.subList(0, Math.min(n2, dados_arquivo.size())));

            maioresActive.sort(Comparator.comparing(Dados::getConfirmed));

            // Ordenando a lista 'dados_arquivo' em ordem decrescente com base no campo
            // 'confirmed'
            dados_arquivo.sort(Comparator.comparing(Dados::getConfirmed).reversed());

            // Obtendo os n4 países com maior valor de 'confirmed' e ordenando-os por nome
            List<Dados> maioresConfirmed = dados_arquivo.subList(0, Math.min(n4, dados_arquivo.size()));

            maioresConfirmed.sort(Comparator.comparing(Dados::getPais));

            // Imprimindo os resultados
            System.out.println(soma); // Imprime a soma dos 'active' dos países com 'confirmed' >= n1

            posicao = 0;
            for (Dados dados : maioresActive) {
                if (posicao < n3)
                    System.out.println(dados.getDeaths()); // Imprime os valores de 'deaths' dos n3 países com menor
                                                           // valor de 'confirmed' entre os 'maioresActive'
                posicao++;
            }

            for (Dados dados : maioresConfirmed) {
                System.out.println(dados.getPais()); // Imprime os nomes dos n4 países com maior valor de 'confirmed'
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Método principal que lê os valores de n1, n2, n3, n4 a partir da entrada do
    // usuário (console) e chama o método 'tratamento_dados'
    public static void main(String[] args) {
        int n1, n2, n3, n4;
        Scanner scan = new Scanner(System.in);
        n1 = scan.nextInt();
        n2 = scan.nextInt();
        n3 = scan.nextInt();
        n4 = scan.nextInt();
        scan.close();
        tratamento_dados(n1, n2, n3, n4); // Chamando o método para processar os dados e exibir resultados
    }

}
