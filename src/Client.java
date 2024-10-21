import java.util.ArrayList;

public class Client implements Runnable{
    private ArrayList<Document> documenteDetinute;
    private Document documentNecesar;
    private String nume;

    public Client(String nume, ArrayList<Document> documenteDetinute, Document documentNecesar) {
        this.nume = nume;
        this.documenteDetinute = documenteDetinute;
        this.documentNecesar = documentNecesar;
    }

//    public boolean hasNecessaryDocument() {
//        return documenteDetinute.contains(documentNecesar);
//    }

    public synchronized void getDocumenteNecesare() {
        System.out.println("I need " + documentNecesar + " " + this.nume);
        documenteDetinute.add(documentNecesar);
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
