class ClientRunnable implements Runnable {
    private Birou birou;
    private Ghiseu ghiseu;
    private Client client;

    public ClientRunnable(Birou birou, Ghiseu ghiseu, Client client) {
        this.birou = birou;
        this.ghiseu = ghiseu;
        this.client = client;
    }


    @Override
    public void run() {

        birou.adaugaClient(client);
        try {
            ghiseu.eliminaClient(client);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}