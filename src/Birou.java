import java.util.ArrayList;

public class Birou {
    private String nume;
    private int clientCount = 0;
    private final int MAX_CLIENTS = 2;
    private ArrayList<Ghiseu> lista_ghiseuri_din_birou;

    public Birou(String nume, ArrayList<Ghiseu> lista_ghiseuri_din_birou) {
        this.nume = nume;
        this.lista_ghiseuri_din_birou = lista_ghiseuri_din_birou;
    }

    public boolean allowClient(Client client) {
        if (clientCount >= MAX_CLIENTS) {
            return false;
        }

        clientCount++;
        client.setBirou_asignat(this);
        System.out.println("Client " + client + " a fost asignat la biroul " + nume);
        return true;
    }

    public synchronized void leaveOffice(Client client) {
        clientCount--;
        System.out.println(client + " a eliberat un loc la biroul " + nume);
        notifyAll(); // Notificăm toți clienții aflați în așteptare
    }

    public ArrayList<Ghiseu> getLista_ghiseuri_din_birou() {
        return lista_ghiseuri_din_birou;
    }

    public boolean obtinereDocumentDeLaGhiseu(Client client) {
        for (Ghiseu ghiseu : lista_ghiseuri_din_birou) {
            if(ghiseu.getTip_de_document_eliberat().equals(client.getDocument_necesar()))
                return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return nume;
    }
}
