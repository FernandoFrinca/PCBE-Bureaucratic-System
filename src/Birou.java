import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Birou {
    //ArrayList<Client> clienti = new ArrayList<Client>();
    ArrayList<Ghiseu> ghiseuri = new ArrayList<Ghiseu>();

    public Birou(ArrayList<Ghiseu> ghiseuri) {
        this.ghiseuri = ghiseuri;
    }

    public  void adaugaClient(Client client){

        for (Document doc : client.getDocumente()) {
            String idDoc = doc.getId();
            switch (idDoc){
                case "Pasaport", "Buletin":{
                    for (Ghiseu ghiseu : ghiseuri) {
                        if (ghiseu.getStare() && ghiseu.getDocumentAtribuit().getId().equals(idDoc)) {
                            ghiseu.adaugaClient(client);
                            try {
                                ghiseu.eliminaClient(client);

                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        }
                    }
                }break;
                default:break;
            }
        }
    }

}
/*
public void adaugaClient(Client client){
    Iterator<Ghiseu> it = ghiseuri.iterator();
    while(it.hasNext()){
        Ghiseu ghiseu = it.next();
        if(ghiseu.getStare() == true){
            ghiseu.adaugaClient(client);
        }
    }
}*/
