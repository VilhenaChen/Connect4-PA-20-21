package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;

import java.util.ArrayList;

public interface IEstado {

    IEstado comecaJogo(String nome1, String nome2, int GameMode);

    IEstado jogaPecaEspecial();

    IEstado jogaPecaNormal();

    IEstado pecaJogada(int col);

    IEstado jogaMiniJogo();

    IEstado comecaMinijogo();

    IEstado submeterInput(String resposta);

    IEstado fimMiniJogo(Boolean ganhou);

    IEstado jogaOutraVez(Dados data);

    IEstado verHistorico(int jogo);

    IEstado continuaHistorico(ArrayList<ArrayList<Dados>> historico, int num);

    IEstado sairHistorico(Dados newData);

    Situacao getSituacao();

}
