package vilhena.quatroemlinha.logica.dados.minijogos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Minijogo_Palavras extends MiniJogos {
    double inicio; //variavel para saber quando e que o minijogo comecou
    double fim; //variavel para saber quando e que o minijogo acaba
    double atual; //Variavel para saber o tempo demorado pelo utilizador
    int corretas;
    String escolhidas[];
    StringBuilder sb;
    String escritas_user;
    Boolean jogou;

    public Minijogo_Palavras() {
        escolhidas = new String[5];
        sb = new StringBuilder();
        escritas_user = new String();
        jogou = false;
    }

    public void leFicheiro() {
        int count = 0;
        String palavras[] = new String[100];
        try {
            File ficheiro = new File("palavras.txt");
            Scanner sc = new Scanner(ficheiro);
            while(sc.hasNextLine()) {
                palavras[count] = sc.nextLine();
                count++;
            }
        }catch (FileNotFoundException e) {
            System.out.println("Problema a abrir o ficheiro");
        }

        //Escolher as palavras
        for(int i = 0; i < 5; i++) {
            escolhidas[i] = palavras[(int) (Math.random() * 100)];
            sb.append(escolhidas[i]);
            if(i != 4)
                sb.append(" ");
        }
    }

    @Override
    public void comecaTempo() {
        escolhidas = new String[5];
        sb = new StringBuilder();
        escritas_user = new String();
        jogou = false;
        leFicheiro();
        inicio = System.currentTimeMillis() / 1000;
        atual = 0.0;
        fim = (sb.length() /2);
    }

    @Override
    public boolean verificaTempo() {
        atual = ((System.currentTimeMillis() / 1000)- inicio);
        return atual >= fim;
    }

    @Override
    public String getPergunta() {
        return sb.toString();
    }

    @Override
    public void inputUser(String user) {
        escritas_user = user;
        jogou = true;
    }

    @Override
    public int isWinner() {
        String[] lidas;
        lidas = escritas_user.split(" ");
        if (jogou) {
            if (verificaTempo())
                return 2;
            for(int i = 0; i < 5; i++) {
                if(!lidas[i].equals(escolhidas[i]))
                    return 2;
            }
            return 1;
            }
        return 0;
    }
}
