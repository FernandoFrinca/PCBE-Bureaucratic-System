import java.util.concurrent.Semaphore;

public class Birou {
    private String nume;
    private Semaphore semafor;
    private int clientCount = 0;
    private final int MAX_CLIENTS = 2;  // Capacitate maximă de clienți într-un birou

    public Birou(String nume) {
        this.nume = nume;
        this.semafor = new Semaphore(1);
    }

    public synchronized boolean allowClient(Client client) throws InterruptedException {
        while (clientCount >= MAX_CLIENTS) {
            wait(); // Intrăm în așteptare dacă biroul este plin
        }

        semafor.acquire();
        try {
            clientCount++;
            client.setBirou_asignat(this);
            System.out.println("Client " + client + " a fost asignat la biroul " + nume);
        } finally {
            semafor.release();
        }
        return true;
    }

    public synchronized void leaveOffice() {
        clientCount--;
        System.out.println("Un client a eliberat un loc la biroul " + nume);
        notifyAll(); // Notificăm toți clienții aflați în așteptare
    }

    public int getClientCount() {
        return clientCount;
    }

    @Override
    public String toString() {
        return nume;
    }
}
