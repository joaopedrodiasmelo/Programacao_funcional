# Programacao_funcional

Trabalho 1

Placar e pontuação de um jogo de boliche
O boliche é um esporte praticado com uma bola pesada e tem como objetivo lançar a bola por uma pista, derrubar 10 pinos do lado oposto da pista dispostos em formação triangular (https://www.infoescola.com/esportes/boliche/).

A fórmula da contagem de pontos no boliche tem as seguintes variáveis (https://boliche.com.br/esporte-boliche/contagem-dos-pontos-no-boliche/):

Os pontos são a soma dos pinos derrubados.
Exceto quando fizer Strike (derrubar 10 pinos na 1.ª bola) ou Spare (derrubar 10 pinos nas duas bolas jogadas)
Se fizer Strike ganha bônus nas 2 bolas jogadas a seguir. Se fizer Spare ganha bônus na próxima bola jogada. O bônus é igual ao número de pinos derrubados.
O total de 1 partida pode variar de zero a 300 pontos.
A pontuação pode ir de zero (quando nenhum pino é derrubado nas dez jogadas ou “frames”) até o máximo possível de 300 pontos, ou seja, 12 “strikes” consecutivos. Supostamente, como cada partida tem 10 “frames” (jogadas), só seriam possíveis 10 “strikes”. Porém, se o jogador derrubar todos os pinos no primeiro arremesso do 10.º “frame”, tem o direito de jogar mais duas bolas, podendo completar 12 “strikes” numa mesma linha.

Faça um programa que leia a quantidade de pinos derrubados por um praticante de boliche em cada jogada e imprima:

A sequência de pinos derrubados (de acordo com os exemplos de entrada e saída e as anotações de contagem de pontos - https://boliche.com.br/esporte-boliche/contagem-dos-pontos-no-boliche/);
A pontuação final do jogador.
Dica: Para testar seu programa, sugere-se utilizar o seguinte simulador de pontos:

https://www.bowlinggenius.com/
Exemplos de entrada e saída:

Exemplo 1:

Entrada

Saída

1 4 4 5 6 4 5 5 10 0 1 7 3 6 4 10 2 8 6

1 4 | 4 5 | 6 / | 5 / | X _ | 0 1 | 7 / | 6 / | X _ | 2 / 6 | 133

Exemplo 2:

Entrada

Saída

10 10 10 10 10 10 10 10 10 10 10 10

X _ | X _ | X _ | X _ | X _ | X _ | X _ | X _ | X _ | X X X | 300





Trabalho 2


Para cada versão, faça UM programa, que leia uma linha com 5 inteiros separados por espaço. Sejam esses inteiros n1, n2, n3 e n4. Ele deve imprimir as seguintes informações:

A soma de "Active" de todos os países em que "Confirmed" é maior o igual que n1.
Dentre os n2 países com maiores valores de "Active", o "Deaths" dos n3 países com menores valores de "Confirmed".
Os n4 países com os maiores valores de "Confirmed". Os nomes devem estar em ordem alfabética.

No Run.Codes, o arquivo .java deve conter uma classe Main, e com um objeto main, como de costume.

O programa deve ler um arquivo "dados.csv", que já estará disponível no run.codes no diretório que o programa vai ser executado (não precisa inclui-lo no trabalho). Para teste, uma cópia do arquivo está disponível em https://drive.google.com/file/d/1ejA--Cvw2AeNq5jhcellpwl4TxuXPXFf/view?usp=sharing As colunas do arquivo são: Country, Confirmed, Deaths, Recovery, Active.

