import java.util.ArrayList;

public class Client implements Runnable{
    private ArrayList<Document> documenteDetinute;
    private ArrayList<Document> documenteNecesare;
    private String nume;

    public Client(String nume, ArrayList<Document> documenteDetinute, ArrayList<Document> documenteNecesare) {
        this.nume = nume;
        this.documenteDetinute = documenteDetinute;
        this.documenteNecesare = documenteNecesare;
    }

//    public boolean hasNecessaryDocument() {
//        return documenteDetinute.contains(documentNecesar);
//    }

    public ArrayList<Document> getDocumente(){
        return documenteNecesare;
    }

    //aici primeste documentele necesare
    public void getDocumenteNecesare(Document doc) {
            System.out.println("I need " + doc + " " + this.nume + " si l-am obtinut");
            documenteDetinute.add(doc);
    }


    public String toString(){
        return nume;
    }

    @Override
    public void run() {

    }

    public String getNume() {
        return nume;
    }
}
