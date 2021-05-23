package vilhena.quatroemlinha.logica.dados;

import java.io.Serial;
import java.io.Serializable;

public class Jogador implements Cloneable, Serializable {
    @Serial
    private static final long serialVersionUID = 4L;

    String nome;
    Boolean human; //Boolean para saber se e um humano ou nao
    int pecaEspecial; //saber quantas pecas especiais tem
    int creditos; //Saber o numero de creditos
    boolean jogou; //Booelan para saber se o jogador ja efetou a sua jogada neste turno
    boolean ganhou;
    int bonus;
    int minijogo; //variavel para saber se o jogador ja jogou o minijogo dos numeros 1 ou o das palavras 2, ou nenhum 0

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
        this.bonus = 1;
        this.minijogo = 0;
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

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getMinijogo() {
        return minijogo;
    }

    public void setMinijogo(int minijogo) {
        this.minijogo = minijogo;
    }

    @Override
    protected Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return nome;
    }
}
