import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Ghiseu {
    private int id;
    private Document documentAtribuit;
    Boolean stare = false;
    private Semaphore semafor;
    ArrayList<Client> coadaClienti;

    public Ghiseu(int id, Document documentAtribuit) {
        this.id = id;
        this.documentAtribuit = documentAtribuit;
        coadaClienti = new ArrayList<>();
        this.semafor = new Semaphore(1);
    }

    public int getId() {
        return id;
    }

    public void inchideGhiseu(){
        if (stare == true){
            stare = false;
        }
    }

    public void deschideGhiseu(){
        if (stare == false){
            stare = true;
        }
    }

    public void adaugaClient(Client client){
        if (stare == true) {
            coadaClienti.add(client);
            System.out.println(client + " adaugat la coada la " + "ghiseul:" + this.getId());
        }
    }

    // aici rezolvam prima problema de concurenta
    public void eliminaClient(Client client) throws InterruptedException {
        semafor.acquire();  // Obține permisiunea de acces la resursă (dacă este disponibilă)

        try {
            client.getDocumenteNecesare(documentAtribuit);  // Clientul primește documentele necesare
            System.out.println(client.getNume() + " este eliminat de la ghiseul:"+ this.getId());
            coadaClienti.remove(client);  // Clientul este eliminat din coadă
        } finally {
            semafor.release();  // Eliberează permisiunea, permițând altui fir să acceseze resursa
        }
    }

    /*public synchronized void eliminaClient(Client client) throws InterruptedException {
        client.getDocumenteNecesare();
        System.out.println(client.getNume() + " eliminat.");
        coadaClienti.remove(client);
    }*/

    public Boolean getStare(){
        return stare;
    }
    public Document getDocumentAtribuit(){return documentAtribuit;}

    
}
