package GameObjects;

public abstract class GameObject {

    private int posX, posY;
    private char appearance;

    public GameObject(int posX, int posY, char appearance) {
        this.posX = posX;
        this.posY = posY;
        this.appearance = appearance;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public char getAppearance() {
        return this.appearance;
    }

    public void setAppearance(char appearance) {
        this.appearance = appearance;
    }
}
