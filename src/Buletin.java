import java.util.ArrayList;
import java.util.Arrays;

public class Buletin extends Document{

    public Buletin() {
        super("Buletin", new ArrayList<>(Arrays.asList(new Adeverinta())));
    }
}
