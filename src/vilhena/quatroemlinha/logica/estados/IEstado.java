package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;

public interface IEstado {

    IEstado comecaJogo(String nome1, String nome2, int GameMode);

    IEstado jogaPecaEspecial();

    IEstado jogaPecaNormal();

    IEstado pecaJogada(int col);

    IEstado jogaMiniJogo();

    IEstado fimMiniJogo(int jogo);

    IEstado ganha();

    IEstado QuadroCheio();

    IEstado jogaOutraVez(Dados data);

    Situacao getSituacao();

}
