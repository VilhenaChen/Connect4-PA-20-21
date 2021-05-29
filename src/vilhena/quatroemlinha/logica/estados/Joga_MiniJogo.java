package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;
import vilhena.quatroemlinha.utils.Util;

public class Joga_MiniJogo extends EstadoAdapter{

    public Joga_MiniJogo(Dados data) {
        super(data);
    }

    @Override
    public IEstado fimMiniJogo() {
        int joga = data.getJoga();
        int miniJogo = data.getMinijogoJogado();
        switch (miniJogo) {
            case 0:
                miniJogo = (int) ((Math.random() * 2)+ 1);
                data.setMinijogoJogado(miniJogo);
                break;
            case 1:
                miniJogo = 2;
                data.setMinijogoJogado(0);
                break;
            case 2:
                miniJogo = 1;
                data.setMinijogoJogado(0);
                break;
            default:
                break;
        }
        data.setBonusJogador(1);
        if(miniJogo == 1) {
            if(data.jogoNumeros()) {
                data.adicionaPecaEspecial();
            }
            else {
                if(joga == J1) {
                    data.setJogou(joga,true);
                    if(data.getJogou(J1) && data.getJogou(J2)) {
                        data.setJogou(J1, false);
                        data.setJogou(J2, false);
                        data.setTurno(data.getTurno() + 1);
                    }
                    data.setJoga(J2);
                }
                else {
                    data.setJogou(joga,true);
                    if(data.getJogou(J1) && data.getJogou(J2)) {
                        data.setJogou(J1, false);
                        data.setJogou(J2, false);
                        data.setTurno(data.getTurno() + 1);
                    }
                    data.setJoga(J1);
                }
            }
        }
        else {
            if (data.jogoPalavras()) {
                data.adicionaPecaEspecial();
            }
            else {
                if(joga == J1) {
                    data.setJogou(joga,true);
                    if(data.getJogou(J1) && data.getJogou(J2)) {
                        data.setJogou(J1, false);
                        data.setJogou(J2, false);
                        data.setTurno(data.getTurno() + 1);
                    }
                    data.setJoga(J2);
                }
                else {
                    data.setJogou(joga,true);
                    if(data.getJogou(J1) && data.getJogou(J2)) {
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
