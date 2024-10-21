public class Pasaport extends Document{
    private String id;

    public Pasaport(){
        this.id = "Pasaport";
    }

    @Override
    public String toString() {
        return  " "+ this.id;
    }
}
