package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;

public class Espera_Peca_Especial extends EstadoAdapter{

    public Espera_Peca_Especial(Dados data) {
        super(data);
    }

    @Override
    public IEstado pecaJogada(int col) {
        int jogadorAtual = data.getJoga();
        data.tiraPecaEspecial();
        if(jogadorAtual == J1) {
            data.limpaColuna(col);
            data.setJogou(jogadorAtual, true);
            if(data.getJogou(J1) && data.getJogou(J2)) {
                data.adicionaTurnoCreditos();
                data.setBonusTodos(data.getBonus() + 1);
                data.setJogou(J1, false);
                data.setJogou(J1, false);
                data.setTurno(data.getTurno() + 1);
                data.setJoga(J2);
            }
            else {
                data.adicionaTurnoCreditos();
                data.setJoga(J2);
            }
        }
        else {
            data.limpaColuna(col);
            data.setJogou(jogadorAtual, true);
            if(data.getJogou(J1) == true && data.getJogou(J2) == true) {
                data.adicionaTurnoCreditos();
                data.setBonusTodos(data.getBonus() + 1);
                data.setJogou(J1, false);
                data.setJogou(J1, false);
                data.setTurno(data.getTurno() + 1);
                data.setJoga(J1);
            }
            else {
                data.adicionaTurnoCreditos();
                data.setJoga(J1);
            }
        }
        return new Espera_Jogada(data);
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.Espera_Peca_Especial;
    }
}
