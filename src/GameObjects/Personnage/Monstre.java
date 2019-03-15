package GameObjects.Personnage;

public class Monstre extends Personnage {

    private int posX;
    private int posY;
    private String item;
    private char appearance = '@';

    public Monstre(String item, int x, int y) {
        this.item = item;
        this.posX = x;
        this.posY = y;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public char getAppearance() {
        return this.appearance;
    }
}
