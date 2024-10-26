import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Client implements Runnable {
    private String nume;
    private String document_necesar;
    private ArrayList<Birou> lista_de_birouri;
    private final int threadNumber;
    private Semaphore semafor;

    Client(String nume, String document_necesar, ArrayList<Birou> lista_de_birouri, int threadNumber) {
        this.nume = nume;
        this.document_necesar = document_necesar;
        this.lista_de_birouri = lista_de_birouri;
        this.threadNumber = threadNumber;
        this.semafor = new Semaphore(1);  // Limitează accesul simultan
    }

    public void cautaBirou() throws InterruptedException {
        boolean clientAsignat = false;
        semafor.acquire();
        try {
            for (Birou birou : lista_de_birouri) {
                if (birou.allowClient(this)) {
                    clientAsignat = true;
                    break; // Stop searching once assigned
                }
            }
        } finally {
            semafor.release();
        }

        if (!clientAsignat) {
            System.out.println("Client " + nume + " nu a găsit un birou disponibil.");
        }
    }

    @Override
    public void run() {
        try {
            cautaBirou();
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