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

    public synchronized boolean allowClient(Client client) {
        if (clientCount >= MAX_CLIENTS) {
            return false;
        }

        clientCount++;
        client.setBirou_asignat(this);
        System.out.println("------------------------------------------------");
        System.out.println("-> Client " + client + " a fost asignat la biroul " + nume);
        return true;
    }

    public synchronized void leaveOffice(Client client) {
        clientCount--;
        System.out.println("<- " + client + " a eliberat un loc la biroul " + nume);
        notifyAll();
    }

    public ArrayList<Ghiseu> getLista_ghiseuri_din_birou() {
        return lista_ghiseuri_din_birou;
    }

    public boolean obtinereDocumentDeLaGhiseu(Client client) {
        ArrayList<String> documente = client.getDocumenteNecesare();

        boolean toateDocumenteleObtinute = true;

        for (Ghiseu ghiseu : lista_ghiseuri_din_birou) {
            String tipDocument = ghiseu.getTip_de_document_eliberat();

            System.out.println("=  Clientul " + client + " a intrat in ghiseul pentru " + tipDocument);

            if (documente.contains(tipDocument)) {
                documente.remove(tipDocument);
                client.setDocumenteNecesare(documente);
                System.out.println("-  A fost scos documentul: " + tipDocument);
                System.out.println("+  Documente ramase: " + documente);
            }

            if (!documente.isEmpty()) {
                toateDocumenteleObtinute = false;
            }
        }

        if (!toateDocumenteleObtinute) {
            leaveOffice(client);
        }
        return toateDocumenteleObtinute;
    }

    @Override
    public String toString() {
        return nume;
    }
}
