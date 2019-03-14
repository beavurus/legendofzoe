package GameObjects.Entity;

public class Coffre extends Entity {

    private String item;
    private char appearance = '$';

    @Override
    public char getAppearance() {
        return appearance;
    }

    public Coffre(String item) {
        this.item = item;
    }
}
