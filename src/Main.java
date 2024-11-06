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

        // Tipuri de documente
        Document buletin = new Buletin();
        Document pasaport = new Pasaport();
        Document adeverinta = new Adeverinta();

        // Crearea a câte 5 ghișee pentru fiecare tip de document
        Ghiseu ghiseuPasaport1 = new Ghiseu(pasaport);
        Ghiseu ghiseuPasaport2 = new Ghiseu(pasaport);
        Ghiseu ghiseuPasaport3 = new Ghiseu(pasaport);
        Ghiseu ghiseuPasaport4 = new Ghiseu(pasaport);
        Ghiseu ghiseuPasaport5 = new Ghiseu(pasaport);

        Ghiseu ghiseuBuletin1 = new Ghiseu(buletin);
        Ghiseu ghiseuBuletin2 = new Ghiseu(buletin);
        Ghiseu ghiseuBuletin3 = new Ghiseu(buletin);
        Ghiseu ghiseuBuletin4 = new Ghiseu(buletin);
        Ghiseu ghiseuBuletin5 = new Ghiseu(buletin);

        Ghiseu ghiseuAdeverinta1 = new Ghiseu(adeverinta);
        Ghiseu ghiseuAdeverinta2 = new Ghiseu(adeverinta);
        Ghiseu ghiseuAdeverinta3 = new Ghiseu(adeverinta);
        Ghiseu ghiseuAdeverinta4 = new Ghiseu(adeverinta);
        Ghiseu ghiseuAdeverinta5 = new Ghiseu(adeverinta);

        // Alocarea ghișeelor în birouri
        ArrayList<Ghiseu> listaGhiseuriBirou1 = new ArrayList<>();
        listaGhiseuriBirou1.add(ghiseuPasaport1);
        listaGhiseuriBirou1.add(ghiseuPasaport2);
//        listaGhiseuriBirou1.add(ghiseuBuletin1);
//        listaGhiseuriBirou1.add(ghiseuBuletin2);
//        listaGhiseuriBirou1.add(ghiseuAdeverinta1);

        ArrayList<Ghiseu> listaGhiseuriBirou2 = new ArrayList<>();
        listaGhiseuriBirou2.add(ghiseuPasaport3);
        listaGhiseuriBirou2.add(ghiseuPasaport4);
        listaGhiseuriBirou2.add(ghiseuBuletin3);
        listaGhiseuriBirou2.add(ghiseuBuletin4);
        listaGhiseuriBirou2.add(ghiseuAdeverinta2);
        listaGhiseuriBirou2.add(ghiseuAdeverinta3);

        ArrayList<Ghiseu> listaGhiseuriBirou3 = new ArrayList<>();
        listaGhiseuriBirou3.add(ghiseuPasaport5);
        listaGhiseuriBirou3.add(ghiseuBuletin5);
        listaGhiseuriBirou3.add(ghiseuAdeverinta4);
        listaGhiseuriBirou3.add(ghiseuAdeverinta5);

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
