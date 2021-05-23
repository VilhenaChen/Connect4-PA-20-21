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
            data.jogaPeca(col, J1);
            data.setJogou(jogadorAtual, true);
            if(data.getJogou(J1) && data.getJogou(J2)) {
                data.adicionaTurnoCreditos();
                data.setBonusTodos(data.getBonus() + 1);
                data.setJogou(J1, false);
                data.setJogou(J2, false);
                data.setTurno(data.getTurno() + 1);
            }
            if (data.isVencedor()) { //Verifica se o Jogador ganhou
                data.setGanhou();
                return new GameOver(data);
            }
            else {
                if (data.isBoardFull()) { //Verificar se o Tabuleiro ja esta cheio
                    return new GameOver(data);
                }
                else {
                    data.adicionaTurnoCreditos();
                    data.setJoga(J2);
                }
            }
        }
        else {
            data.jogaPeca(col, J2);
            data.setJogou(jogadorAtual, true);
            if(data.getJogou(J1) && data.getJogou(J2)) {
                data.adicionaTurnoCreditos();
                data.setBonusTodos(data.getBonus() + 1);
                data.setJogou(J1, false);
                data.setJogou(J2, false);
                data.setTurno(data.getTurno() + 1);
            }
            if(data.isVencedor()) { //Verifica se o Jogador ganhou
                data.setGanhou();
                return new GameOver(data);
            }
            else {
                if(data.isBoardFull()) {//Verificar se o Tabuleiro ja esta cheio
                    return new GameOver(data);
                }
                else {
                    data.adicionaTurnoCreditos();
                    data.setJoga(J1);
                }
            }
        }
        return new Espera_Jogada(data);
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.Espera_Peca_Normal;
    }
}
