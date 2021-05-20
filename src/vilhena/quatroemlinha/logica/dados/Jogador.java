package vilhena.quatroemlinha.logica.dados;

public class Jogador {
    String nome;
    Boolean human; //Boolean para saber se e um humano ou nao
    int pecaEspecial; //saber quantas pecas especiais tem
    int creditos; //Saber o numero de creditos
    boolean jogou; //Booelan para saber se o jogador ja efetou a sua jogada neste turno
    boolean ganhou;

    public Jogador(String nome, Boolean human) {
        this.nome = nome;
        this.human = human;
        this.pecaEspecial = 0;
        if(human = true)
            this.creditos = 5;
        else
            this.creditos = 0;
        this.jogou = false;
        this.ganhou = false;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getHuman() {
        return human;
    }

    public int getPecaEspecial() {
        return pecaEspecial;
    }

    public void setPecaEspecial(int pecaEspecial) {
        this.pecaEspecial = pecaEspecial;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public boolean getJogou() {
        return jogou;
    }

    public void setJogou(boolean jogou) {
        this.jogou = jogou;
    }

    public boolean isGanhou() {
        return ganhou;
    }

    public void setGanhou(boolean ganhou) {
        this.ganhou = ganhou;
    }
}
