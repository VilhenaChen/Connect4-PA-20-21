package vilhena.quatroemlinha.logica.dados;

public class Jogador {
    String nome;
    Boolean human; //Boolean para saber se e um humano ou nao
    boolean pecaEspecial; //Boolean para saber se o jogador tem ou nao peca especial
    boolean jogou; //Booelan para saber se o jogador ja efetou a sua jogada neste turno

    public Jogador(String nome, Boolean human) {
        this.nome = nome;
        this.human = human;
        this.pecaEspecial = false;
        this.jogou = false;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getHuman() {
        return human;
    }

    public boolean isPecaEspecial() {
        return pecaEspecial;
    }

    public boolean getJogou() {
        return jogou;
    }

    public void setJogou(boolean jogou) {
        this.jogou = jogou;
    }
}
