import java.util.Random;
import java.util.concurrent.Semaphore;

public class Ghiseu {

    private Document tip_de_document_eliberat;
    private boolean stareGhiseu;
    private int numarRandom;
    private int contor;
    private Semaphore semafor = new Semaphore(1);

    public Ghiseu(Document tip_de_document_eliberat) {
        this.tip_de_document_eliberat = tip_de_document_eliberat;
        this.stareGhiseu = true;

        // Generare număr random între 1 și 10
        Random random = new Random();
        this.numarRandom = random.nextInt(10) + 1;

        this.contor = 0;
    }

    public synchronized String getTip_de_document_eliberat() {
        return tip_de_document_eliberat.getTip();
    }

    public synchronized int getNumarRandom() {
        return numarRandom;
    }

    public synchronized int getContor() {
        return contor;
    }

    public synchronized void incrementeazaContor() {
        try {
            semafor.acquire();
            contor++;
            if (contor >= numarRandom) {
                pauzaGhiseu(); // Se pune ghișeul în pauză dacă se atinge numărul maxim
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semafor.release();
        }
    }

    public synchronized boolean getStareGhiseu() {
        return stareGhiseu;
    }

    private synchronized void pauzaGhiseu() {
        stareGhiseu = false;
        System.out.println("Ghiseul " + tip_de_document_eliberat.getTip() + " este în pauză.");

        // Pornim un thread pentru redeschiderea ghișeului după pauză
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Pauză de 5 secunde
                redeschideGhiseu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private synchronized void redeschideGhiseu() {
        System.out.println("Ghiseul " + tip_de_document_eliberat.getTip() +" s-a redeschis.");
        contor = 0; // Resetăm contorul
        stareGhiseu = true; // Redeschidem ghișeul
        notifyAll(); // Notificăm toate thread-urile că ghișeul este deschis
    }
}
