import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final int NUMBER_OF_THREADS_PASAPORT = 10;
        final int NUMBER_OF_THREADS_BULETIN = 10;
        final int NUMBER_OF_THREADS_ADEVERINTA = 10;
        final int NUMBER_OF_OFFICES = 3;

        //pasaport -> buletin, adeverinta
        //buletin -> adeverinta
        //adeverinta -> NIMIC

        Document buletin = new Buletin();
        Document pasaport = new Pasaport();
        Document adeverinta = new Adeverinta();

        Ghiseu ghiseuBuletin = new Ghiseu(buletin);
        Ghiseu ghiseuPasaport = new Ghiseu(pasaport);
        Ghiseu ghiseuAdeverinta = new Ghiseu(adeverinta);

        ArrayList<Ghiseu> listaGhiseuriBirou1 = new ArrayList<>();
        listaGhiseuriBirou1.add(ghiseuBuletin);
        listaGhiseuriBirou1.add(ghiseuPasaport);
        listaGhiseuriBirou1.add(ghiseuAdeverinta);

        ArrayList<Ghiseu> listaGhiseuriBirou2 = new ArrayList<>();
        listaGhiseuriBirou2.add(ghiseuBuletin);
        listaGhiseuriBirou2.add(ghiseuAdeverinta);

        ArrayList<Ghiseu> listaGhiseuriBirou3 = new ArrayList<>();
        listaGhiseuriBirou3.add(ghiseuPasaport);

        ArrayList<Birou> listaDeBirouri = new ArrayList<>();
        listaDeBirouri.add(new Birou("Birou 1", listaGhiseuriBirou1));
        listaDeBirouri.add(new Birou("Birou 2", listaGhiseuriBirou2));
        listaDeBirouri.add(new Birou("Birou 3", listaGhiseuriBirou3));

        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        NUMBER_OF_THREADS_BULETIN+ NUMBER_OF_THREADS_PASAPORT + NUMBER_OF_THREADS_ADEVERINTA
                );

        for (int i = 1; i <= NUMBER_OF_THREADS_BULETIN; i++) {
            Client client = new Client("Client " + i, buletin, listaDeBirouri, i);
            executorService.submit(client);
        }
        for (int i = 1; i <= NUMBER_OF_THREADS_PASAPORT; i++) {
            Client client = new Client("Client " + (i+100), pasaport, listaDeBirouri, i);
            executorService.submit(client);
        }
        for (int i = 1; i <= NUMBER_OF_THREADS_ADEVERINTA; i++) {
            Client client = new Client("Client " + (i+200), adeverinta, listaDeBirouri, i);
            executorService.submit(client);
        }
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

    }
}
