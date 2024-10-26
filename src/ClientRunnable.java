class ClientRunnable implements Runnable {
    private Birou birou;

    private Client client;

    public ClientRunnable(Birou birou, Client client) {
        this.birou = birou;

        this.client = client;
    }


    @Override
    public void run() {

        birou.adaugaClient(client);
//        try {
//            ghiseu.eliminaClient(client);
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}