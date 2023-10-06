
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        int soma = 0; // Variável para armazenar a soma dos 'active' dos países com 'confirmed' >= n1
        String arquivoCSV = "dados.csv"; // Nome do arquivo CSV a ser lido
        String separador = ","; // Separador utilizado no arquivo CSV
        List<Dados> dados_arquivo = new ArrayList<>(); // Lista para armazenar os dados lidos do arquivo

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            // Lendo o arquivo CSV e mapeando cada linha para um objeto da classe Dados
            dados_arquivo = br.lines()
                    .map(linha -> linha.split(separador))
                    .map(dados -> new Dados(dados[0].trim(), Integer.parseInt(dados[1].trim()),
                            Integer.parseInt(dados[2].trim()), Integer.parseInt(dados[3].trim()),
                            Integer.parseInt(dados[4].trim())))
                    .collect(Collectors.toList());

            // Calculando a soma dos 'active' dos países com 'confirmed' maior ou igual a n1
            soma = dados_arquivo.stream()
                    .filter(dados -> dados.getConfirmed() >= n1)
                    .mapToInt(Dados::getActive)
                    .sum();

            // Obtendo os 'n2' países com maior valor de 'active' e ordenando-os em ordem
            // decrescente
            List<Dados> maioresActive = dados_arquivo.stream()
                    .sorted(Comparator.comparing(Dados::getActive).reversed())
                    .limit(n2)
                    .collect(Collectors.toList());

            // Obtendo os 'n3' países com menor valor de 'confirmed' entre os
            // 'maioresActive' e extraindo seus valores de 'deaths'
            List<Integer> menoresConfirmedDeaths = maioresActive.stream()
                    .sorted(Comparator.comparing(Dados::getConfirmed))
                    .limit(n3)
                    .map(Dados::getDeaths)
                    .collect(Collectors.toList());

            // Obtendo os 'n4' países com maior valor de 'confirmed' e ordenando-os por nome
            List<Dados> maioresConfirmed = dados_arquivo.stream()
                    .sorted(Comparator.comparing(Dados::getConfirmed).reversed())
                    .limit(n4)
                    .sorted(Comparator.comparing(Dados::getPais))
                    .collect(Collectors.toList());

            // Exibindo os resultados
            System.out.println(soma); // Imprime a soma dos 'active' dos países com 'confirmed' >= n1

            menoresConfirmedDeaths.forEach(System.out::println); // Imprime os valores de 'deaths' dos 'n3' países com
                                                                 // menor valor de 'confirmed' entre os 'maioresActive'

            maioresConfirmed.forEach(dados -> System.out.println(dados.getPais())); // Imprime os nomes dos 'n4' países
                                                                                    // com maior valor de 'confirmed'
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método principal que lê os valores de n1, n2, n3, n4 e chama o método
    // 'tratamento_dados'
    public static void main(String[] args) {
        int n1, n2, n3, n4;
        Scanner scan = new Scanner(System.in);
        n1 = scan.nextInt();
        n2 = scan.nextInt();
        n3 = scan.nextInt();
        n4 = scan.nextInt();
        scan.close();
        tratamento_dados(n1, n2, n3, n4);
    }

}
