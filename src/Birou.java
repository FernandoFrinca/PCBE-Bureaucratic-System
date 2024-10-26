import java.util.concurrent.Semaphore;

public class Birou {
    private String nume;
    private Semaphore semafor;
    private int clientCount = 0;
    private final int MAX_CLIENTS = 2;  // Capacitate maximă de clienți intr-un birou

    public Birou(String nume) {
        this.nume = nume;
        this.semafor = new Semaphore(1);
    }

    public boolean allowClient(Client client) throws InterruptedException {
        semafor.acquire();
        try {
            if (clientCount >= MAX_CLIENTS) {
                // System.out.println("Biroul " + nume + " a atins capacitatea maximă și nu poate accepta mai mulți clienți.");
                return false;
            }
            clientCount++;
            System.out.println("Client " + client + " a fost asignat la biroul " + nume);
        } finally {
            semafor.release();
        }
        return true;
    }

    public int getClientCount() {
        return clientCount;
    }

    @Override
    public String toString() {
        return nume;
    }
}