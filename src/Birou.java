import java.util.concurrent.Semaphore;

public class Birou {
    private String nume;
    private Semaphore semafor;
    private int clientCount = 0;
    private final int MAX_CLIENTS = 2;  // Capacitate maximă de clienți

    public Birou(String nume) {
        this.nume = nume;
        this.semafor = new Semaphore(1);  // Limitează accesul simultan
    }

    public boolean allowClient(Client client) throws InterruptedException {
        synchronized (this) {
            // Verificăm dacă s-a atins capacitatea maximă
            if (clientCount >= MAX_CLIENTS) {
                System.out.println("Biroul " + nume + " a atins capacitatea maximă și nu poate accepta mai mulți clienți.");
                return false;
            }
            clientCount++;  // Incrementăm contorul
        }

        // Se încearcă obținerea accesului în secțiunea critică cu semaphore
        semafor.acquire();
        try {
            System.out.println("Client " + client + " a fost asignat la biroul " + nume);
        } finally {
            semafor.release();
        }
        return true; // Return true if successfully assigned
    }

    public int getClientCount() {
        return clientCount;
    }

    @Override
    public String toString() {
        return nume;
    }
}