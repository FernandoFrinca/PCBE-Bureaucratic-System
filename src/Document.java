import java.lang.reflect.Array;
import java.util.ArrayList;

abstract public class  Document {

    private String tip;
    private ArrayList<Document> constrangeri;

    public Document (String tip, ArrayList<Document> constrangeri){
        this.tip = tip;
        this.constrangeri = constrangeri;
    }

    public String getTip() {
        return tip;
    }
}
