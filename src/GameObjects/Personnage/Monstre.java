package GameObjects.Personnage;

public class Monstre extends Personnage {

    private String item;

    public Monstre(String item, int x, int y) {
        super(1,1, '@', x,y);
        this.item = item;
    }

}

