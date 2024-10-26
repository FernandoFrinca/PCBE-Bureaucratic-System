import java.lang.reflect.Array;
import java.util.ArrayList;

abstract public class  Document {
    private String id;
    private ArrayList<Document> constrangeri;

    public Document(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String toString(){
        return id;
    }
}
