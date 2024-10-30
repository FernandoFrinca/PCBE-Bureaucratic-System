import java.lang.reflect.Array;
import java.util.ArrayList;

abstract public class  Document {

    private String tip;

    public Document (String tip){
        this.tip = tip;
    }


    public String getTip() {
        return tip;
    }
}
