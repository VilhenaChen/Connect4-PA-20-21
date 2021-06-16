package vilhena.quatroemlinha.logica.dados.minijogos;

import java.util.Scanner;

public class Minijogo_Contas extends MiniJogos{
    double inicio; //variavel para saber quando e que o minijogo comecou
    double fim; //variavel para saber quando e que o minijogo acaba
    double atual; //Variavel para saber o tempo demorado pelo utilizador
    int corretas; //Variavel para guardar as respostas certas
    int valor_correto; //Variavel para guardar o valor correto de cada pergunta

    public Minijogo_Contas() {
        corretas = 0;
    }

    @Override
    public void comecaTempo() {
        inicio = System.currentTimeMillis() / 1000;
        atual = 0.0;
        fim = 30.0;
    }

    @Override
    public boolean verificaTempo() {
        atual = ((System.currentTimeMillis() / 1000)- inicio);
        return atual >= fim;
    }

    @Override
    public String getPergunta() {
        StringBuilder sb = new StringBuilder();
        int operador = (int)(Math.random() *4);
        int n1 = (int)(Math.random() *10) + 1;
        int n2 = (int)(Math.random() *10) + 1;
        switch(operador) {
            case 0:
                valor_correto = n1 + n2;
                sb.append(n1);
                sb.append("+");
                sb.append(n2);
                break;
            case 1:
                valor_correto = n1 - n2;
                sb.append(n1);
                sb.append("-");
                sb.append(n2);
                break;
            case 2:
                valor_correto = n1 * n2;
                sb.append(n1);
                sb.append("*");
                sb.append(n2);
                break;
            case 3:
                valor_correto = n1 / n2;
                sb.append(n1);
                sb.append("/");
                sb.append(n2);
                break;
        }
        return sb.toString();
    }

    @Override
    public void inputUser(String user) {
        if(Integer.parseInt(user) == valor_correto) {
            corretas++;
        }
    }

    @Override
    public int isWinner() {
        if(corretas == 5 && !verificaTempo())
            return 1;
        else {
            if(verificaTempo())
                return 2;
        }
        return 0;
    }
}
