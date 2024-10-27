import java.util.ArrayList;
import java.util.Arrays;

public class Pasaport extends Document {


    public Pasaport() {
        super("Pasaport",
                new ArrayList<>(Arrays.asList(new Buletin(), new Adeverinta())));

    }
}
