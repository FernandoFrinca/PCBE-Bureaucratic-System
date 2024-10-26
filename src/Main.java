import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final int NUMBER_OF_THREADS = 70;
        final int NUMBER_OF_OFFICES = 3;

        ArrayList<Birou> listaDeBirouri = new ArrayList<>();
        listaDeBirouri.add(new Birou("Birou 1"));
        listaDeBirouri.add(new Birou("Birou 2"));
        listaDeBirouri.add(new Birou("Birou 3"));

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        for (int i = 1; i <= NUMBER_OF_THREADS; i++) {
            Client client = new Client("Client " + i, "Document " + i, listaDeBirouri, i);
            executorService.submit(() -> {
                try {
                    client.cautaBirou();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
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

        for (Birou birou : listaDeBirouri) {
            System.out.println("Număr de clienți serviți de " + birou + ": " + birou.getClientCount());
        }
    }
}
