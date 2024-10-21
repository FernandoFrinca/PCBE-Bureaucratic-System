import java.util.ArrayList;
import java.util.Arrays;

public class Ghiseu {
    private Document documentAtribuit;
    Boolean stare = false;
    ArrayList<Client> coadaClienti;

    public Ghiseu(Document documentAtribuit) {
        this.documentAtribuit = documentAtribuit;
        coadaClienti = new ArrayList<>();
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
            System.out.println(client + " adaugat");
        }
    }

    public synchronized void eliminaClient(Client client) throws InterruptedException {
        client.getDocumenteNecesare();

        System.out.println(client.getNume() + " eliminat.");
        coadaClienti.remove(client);

    }

    public Boolean getStare(){
        return stare;
    }

    
}
