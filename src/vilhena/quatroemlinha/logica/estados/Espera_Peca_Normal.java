package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;

public class Espera_Peca_Normal extends EstadoAdapter{

    public Espera_Peca_Normal(Dados data) {
        super(data);
    }

    @Override
    public IEstado pecaJogada(int col) {
        int jogadorAtual = data.getJoga();
        if(jogadorAtual == J1) {
            data.escrevePosArray(col - 1, J1);
            data.setJogou(jogadorAtual, true);
            if(data.getJogou(J1) == true && data.getJogou(J2) == true) {
                data.setJogou(J1, false);
                data.setJogou(J2, false);
                data.setTurno(data.getTurno() + 1);
            }
            if (data.isBoardFull()) //Verificar se o Tabuleiro ja esta cheio
                return new GameOver(data);
            else{
                data.setJoga(J2);
            }
        }
        else {
            data.escrevePosArray(col - 1, J2);
            data.setJogou(jogadorAtual, true);
            if(data.getJogou(J1) == true && data.getJogou(J2) == true) {
                data.setJogou(J1, false);
                data.setJogou(J2, false);
                data.setTurno(data.getTurno() + 1);
            }
            if(data.isBoardFull()) //Verificar se o Tabuleiro ja esta cheio
                return new GameOver(data);
            else {
                data.setJoga(J1);
            }
        }
        return new Espera_Jogada(data);
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.Espera_Peca_Normal;
    }
}
