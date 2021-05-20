package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;
import vilhena.quatroemlinha.utils.Util;

public class Joga_MiniJogo extends EstadoAdapter{

    public Joga_MiniJogo(Dados data) {
        super(data);
    }

    @Override
    public IEstado fimMiniJogo(int jogo) {
        int joga = data.getJoga();
        data.setBonusJogador(1);
        if(jogo == 1) {
            if(data.jogoNumeros() == true) {
                data.adicionaPecaEspecial();
            }
            else {
                if(joga == J1) {
                    data.setJogou(joga,true);
                    if(data.getJogou(J1) == true && data.getJogou(J2) == true) {
                        data.setJogou(J1, false);
                        data.setJogou(J2, false);
                        data.setTurno(data.getTurno() + 1);
                    }
                    data.setJoga(J2);
                }
                else {
                    data.setJogou(joga,true);
                    if(data.getJogou(J1) == true && data.getJogou(J2) == true) {
                        data.setJogou(J1, false);
                        data.setJogou(J2, false);
                        data.setTurno(data.getTurno() + 1);
                    }
                    data.setJoga(J1);
                }
            }
        }
        else {
            if (data.jogoPalavras() == true) {
                data.adicionaPecaEspecial();
            }
            else {
                if(joga == J1) {
                    data.setJogou(joga,true);
                    if(data.getJogou(J1) == true && data.getJogou(J2) == true) {
                        data.setJogou(J1, false);
                        data.setJogou(J2, false);
                        data.setTurno(data.getTurno() + 1);
                    }
                    data.setJoga(J2);
                }
                else {
                    data.setJogou(joga,true);
                    if(data.getJogou(J1) == true && data.getJogou(J2) == true) {
                        data.setJogou(J1, false);
                        data.setJogou(J2, false);
                        data.setTurno(data.getTurno() + 1);
                    }
                    data.setJoga(J1);
                }
            }
        }
        return new Espera_Jogada(data);
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.Joga_MiniJogo;
    }
}
