public class Buletin extends Document{
    private String id;

    public Buletin(){
        this.id = "Buletin";
    }

    @Override
    public String toString() {
        return " "+ this.id;
    }
}
