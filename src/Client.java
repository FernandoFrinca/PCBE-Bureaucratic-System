import java.util.ArrayList;

public class Client implements Runnable {
    private String nume;
    private String document_necesar;
    private ArrayList<Birou> lista_de_birouri;
    private final int threadNumber;
    private Birou birou_asignat;

    Client(String nume, String document_necesar, ArrayList<Birou> lista_de_birouri, int threadNumber) {
        this.nume = nume;
        this.document_necesar = document_necesar;
        this.lista_de_birouri = lista_de_birouri;
        this.threadNumber = threadNumber;
    }

    public synchronized void cautaBirou() throws InterruptedException {
        boolean clientAsignat = false;

        while (!clientAsignat) {
            for (Birou birou : lista_de_birouri) {
                if (birou.allowClient(this)) {
                    clientAsignat = true;
                    break;
                }
            }

            if (!clientAsignat) {
                System.out.println("Client " + nume + " așteaptă să se elibereze un loc.");
                wait(); // Intrăm în așteptare dacă niciun birou nu este disponibil
            }
        }
    }

    public void setBirou_asignat(Birou birou_asignat) {
        this.birou_asignat = birou_asignat;
    }

    public void print_birou_asignat() {
        System.out.println("Clientul " + nume + " este asignat la biroul " + birou_asignat);
    }

    @Override
    public void run() {
        try {
            cautaBirou();
            print_birou_asignat();
            Thread.sleep(1000); // Clientul petrece un timp la birou
            birou_asignat.leaveOffice(); // Clientul părăsește biroul
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Task " + threadNumber + " was interrupted.");
        }
    }

    @Override
    public String toString() {
        return nume;
    }
}
