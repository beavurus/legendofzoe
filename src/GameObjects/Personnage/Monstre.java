package Personnage;

public class Monstre extends Personnage {

<<<<<<< HEAD
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
=======
};
>>>>>>> 6042cd6f9b3419b482319288cf4cc64ee2536f2b
