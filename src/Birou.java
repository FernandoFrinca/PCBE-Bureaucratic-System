public class Birou {
    private String nume;
    private int clientCount = 0;
    private final int MAX_CLIENTS = 2;

    public Birou(String nume) {
        this.nume = nume;
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

    @Override
    public String toString() {
        return nume;
    }
}
