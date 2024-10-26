import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Document buletin = new Buletin();
        Document pasaport = new Pasaport();

        Ghiseu ghiseu1 = new Ghiseu(1,buletin);
        Ghiseu ghiseu2 = new Ghiseu(2,buletin);
        Ghiseu ghiseu3 = new Ghiseu(3,buletin);
        Ghiseu ghiseu4 = new Ghiseu(4,pasaport);
        Ghiseu ghiseu5 = new Ghiseu(5,pasaport);

        ArrayList<Ghiseu> ghiseuri = new ArrayList<Ghiseu>();
        ghiseu1.deschideGhiseu();
        ghiseu2.deschideGhiseu();
        ghiseu3.deschideGhiseu();
        ghiseu4.deschideGhiseu();
        ghiseu5.deschideGhiseu();
        ghiseuri.add(ghiseu1);
        ghiseuri.add(ghiseu2);
        ghiseuri.add(ghiseu3);
        ghiseuri.add(ghiseu4);
        ghiseuri.add(ghiseu5);

        Birou birou1 = new Birou(ghiseuri);

        ArrayList<Document> documenteDetinuteClient1 = new ArrayList<Document>();

        ArrayList<Document> documenteNecesareClient1 = new ArrayList<Document>();
        documenteNecesareClient1.add(buletin);
        documenteNecesareClient1.add(pasaport);
        ArrayList<Document> documenteNecesareClient2 = new ArrayList<Document>();
        documenteNecesareClient2.add(pasaport);
        documenteNecesareClient2.add(buletin);
        ArrayList<Document> documenteNecesareClient3 = new ArrayList<Document>();
        documenteNecesareClient3.add(pasaport);
        
        Client client1 = new Client("Fernando", documenteDetinuteClient1, documenteNecesareClient1);
        Client client2 = new Client("Maria", documenteDetinuteClient1, documenteNecesareClient2);
        Client client3 = new Client("Jose", documenteDetinuteClient1, documenteNecesareClient3);

        Thread thread1 = new Thread(new ClientRunnable(birou1, client1));
        Thread thread2 = new Thread(new ClientRunnable(birou1, client2));
        Thread thread3 = new Thread(new ClientRunnable(birou1, client3));

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