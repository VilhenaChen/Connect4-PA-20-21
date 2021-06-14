package vilhena.quatroemlinha.logica.dados.minijogos;

import java.io.Serial;
import java.io.Serializable;

abstract public class MiniJogos implements Serializable, Cloneable {
    @Serial
    private static final long serialVersionUID = 4L;

    public MiniJogos() {
    }

    public abstract void comecaTempo();

    public abstract boolean verificaTempo();

    public abstract String getPergunta();

    public abstract void inputUser(String user);

    public abstract int isWinner();

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
