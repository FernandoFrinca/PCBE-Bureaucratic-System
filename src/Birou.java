public class Birou {
    private String nume;
    private int clientCount = 0;
    private final int MAX_CLIENTS = 2;

    public Birou(String nume) {
        this.nume = nume;
    }

    public synchronized boolean allowClient(Client client) {
        if (clientCount >= MAX_CLIENTS) {
            return false;  // Biroul este plin
        }

        clientCount++;
        client.setBirou_asignat(this);
        System.out.println("Client " + client + " a fosts aignat la biroul " + nume);
        return true;
    }

    public synchronized void leaveOffice(Client client) {
        clientCount--;
        System.out.println(client + " a eliberat un loc la biroul " + nume);
        notifyAll(); // Notificăm toți clienții aflați în așteptare
    }

    public synchronized boolean isFull() {
        return clientCount >= MAX_CLIENTS;
    }

    public int getClientCount() {
        return clientCount;
    }

    @Override
    public String toString() {
        return nume;
    }
}
