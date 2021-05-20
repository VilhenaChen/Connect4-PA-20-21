package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.dados.Dados;
import vilhena.quatroemlinha.utils.Util;

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
    public IEstado fimMiniJogo(int jogo) {
        return this;
    }

    @Override
    public IEstado ganha() {
        return this;
    }

    @Override
    public IEstado QuadroCheio() {
        return this;
    }

    @Override
    public IEstado jogaOutraVez(Dados data) {
        return this;
    }
}
