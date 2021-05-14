package vilhena.quatroemlinha.logica.dados;

public class Jogador {
    String nome;
    Boolean human; //Boolean para saber se e um humano ou nao
    int pecaEspecial; //saber quantas pecas especiais tem
    boolean jogou; //Booelan para saber se o jogador ja efetou a sua jogada neste turno

    public Jogador(String nome, Boolean human) {
        this.nome = nome;
        this.human = human;
        this.pecaEspecial = 0;
        this.jogou = false;
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

    public boolean getJogou() {
        return jogou;
    }

    public void setJogou(boolean jogou) {
        this.jogou = jogou;
    }
}
