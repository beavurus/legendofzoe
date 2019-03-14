package GameObjects;

public abstract class GameObject {

    private int posX, posY;
    private char appearance;

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public char getAppearance() {
        return this.appearance;
    }

}
