package GameObjects.Personnage;

public class Zoe extends Personnage {

    private int posX;
    private int posY;
    private char appearance = '&';

    public Zoe(int x, int y) {
        super(x, y, '&');
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
}