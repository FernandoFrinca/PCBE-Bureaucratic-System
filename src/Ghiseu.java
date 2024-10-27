import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Ghiseu {

    private Document tip_de_document_eliberat;

    public Ghiseu(Document tip_de_document_eliberat) {
        this.tip_de_document_eliberat = tip_de_document_eliberat;
    }

    public String getTip_de_document_eliberat() {
        return tip_de_document_eliberat.getTip();
    }
}