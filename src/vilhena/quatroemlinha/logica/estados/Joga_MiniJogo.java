package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;

public class Joga_MiniJogo extends EstadoAdapter{

    public Joga_MiniJogo(Dados data) {
        super(data);
    }

    @Override
    public IEstado comecaMinijogo() {
        int mini;
        data.setBonusJogador(0);
        data.setMinijogoJogado(1);
        switch (data.getMinijogoJogado()) {
            case 0 -> {
                mini = (int) ((Math.random() * 2) + 1);
                data.setMinijogoJogado(mini);
                if ((mini - 1) == 0) {
                    data.comecaContadorContas();
                    return new Minijogo_Contas(data);
                } else {
                    data.comecaContadorPalavras();
                    return new Minijogo_Palavras(data);
                }
            }
            case 1 -> {
                mini = 2;
                data.setMinijogoJogado(0);
                data.comecaContadorPalavras();
                return new Minijogo_Palavras(data);
            }
            case 2 -> {
                mini = 1;
                data.setMinijogoJogado(0);
                data.comecaContadorContas();
                return new Minijogo_Contas(data);
            }
        }
        return this;
    }

    @Override
    public IEstado naoJogaMiniJogo() {
        data.setBonusJogador(0);
        return new Espera_Jogada(data);
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.Joga_MiniJogo;
    }
}
