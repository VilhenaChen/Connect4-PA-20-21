package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.dados.Dados;
import vilhena.quatroemlinha.utils.Util;

import java.util.ArrayList;

public abstract class EstadoAdapter implements IEstado, Util {

    Dados data;

    public EstadoAdapter(Dados data) {
        this.data = data;
    }

    @Override
    public IEstado comecaJogo(String nome1, String nome2, int GameMode) {
        return this;
    }

    @Override
    public IEstado jogaPecaEspecial() {
        return this;
    }

    @Override
    public IEstado jogaPecaNormal() {
        return this;
    }

    @Override
    public IEstado pecaJogada(int col) {
        return this;
    }

    @Override
    public IEstado jogaMiniJogo() {
        return this;
    }

    @Override
    public IEstado naoJogaMiniJogo() {
        return this;
    }

    @Override
    public IEstado comecaMinijogo() {
        return this;
    }

    @Override
    public IEstado submeterInput(String resposta) { return this;}

    @Override
    public IEstado fimMiniJogo(Boolean ganhou) {
        return this;
    }

    @Override
    public IEstado verHistorico(int jogo) {
        return this;
    }

    @Override
    public IEstado continuaHistorico(ArrayList<ArrayList<Dados>> historico, int num) {
        return this;
    }

    @Override
    public IEstado sairHistorico(Dados newData) {
        return this;
    }

    @Override
    public IEstado jogaOutraVez(Dados data) {
        return this;
    }
}
