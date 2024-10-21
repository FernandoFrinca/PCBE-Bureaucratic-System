import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Birou {
    //ArrayList<Client> clienti = new ArrayList<Client>();
    ArrayList<Ghiseu> ghiseuri = new ArrayList<Ghiseu>();

    public Birou(ArrayList<Ghiseu> ghiseuri) {
        this.ghiseuri = ghiseuri;
    }

    public void adaugaClient(Client client){
        Iterator<Ghiseu> it = ghiseuri.iterator();
        while(it.hasNext()){
            Ghiseu ghiseu = it.next();
            if(ghiseu.getStare() == true){
                ghiseu.adaugaClient(client);
            }
        }
    }
}
