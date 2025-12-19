package MODELS.CLASS;
import java.util.Date;

public class Consumivel extends Recurso {
    private Date dataValidade;

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }
}