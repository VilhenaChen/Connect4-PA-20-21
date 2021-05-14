package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;

public interface IEstado {

    IEstado comecaJogo(String nome1, String nome2, int GameMode);

    IEstado jogaPecaEspecial();

    IEstado jogaPecaNormal();

    IEstado pecaJogada(int col);

    IEstado jogaMiniJogo();

    IEstado fimMiniJogo();

    IEstado ganha();

    IEstado QuadroCheio();

    IEstado jogaOutraVez();

    Situacao getSituacao();

}
