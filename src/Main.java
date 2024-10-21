import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Document buletin = new Buletin();

        Ghiseu ghiseu1 = new Ghiseu(buletin);
        ArrayList<Ghiseu> ghiseuri = new ArrayList<Ghiseu>();
        ghiseu1.deschideGhiseu();
        ghiseuri.add(ghiseu1);

        Birou birou1 = new Birou(ghiseuri);

        ArrayList<Document> documenteDetinuteClient1 = new ArrayList<Document>();
        
        Client client1 = new Client("Fernando", documenteDetinuteClient1, buletin);
        Client client2 = new Client("Maria", documenteDetinuteClient1, buletin);
        Client client3 = new Client("Jose", documenteDetinuteClient1, buletin);

        Thread thread1 = new Thread(new ClientRunnable(birou1, ghiseu1, client1));
        Thread thread2 = new Thread(new ClientRunnable(birou1, ghiseu1, client2));
        Thread thread3 = new Thread(new ClientRunnable(birou1, ghiseu1, client3));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}



/*
        Document buletin1 = new Buletin();
        Document pasaport1 = new Pasaport();
        Ghiseu ghiseu1 = new Ghiseu(buletin1);
        Ghiseu ghiseu2 = new Ghiseu(pasaport1);

        ArrayList<Ghiseu> ghiseuri = new ArrayList<Ghiseu>();

        ghiseu1.deschideGhiseu();

        ghiseuri.add(ghiseu1);
        ghiseuri.add(ghiseu2);

        Birou birou1 = new Birou(ghiseuri);

        ArrayList<Document> documenteDetinuteClient1 = new ArrayList<Document>();
        documenteDetinuteClient1.add(buletin1);

        Client client1 = new Client("Fernando", documenteDetinuteClient1, pasaport1);
        birou1.adaugaClient(client1);
        ghiseu1.eliminaClient(client1);
 */