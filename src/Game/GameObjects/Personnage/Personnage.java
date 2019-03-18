package Game.GameObjects.Personnage;

import Game.GameObjects.GameObject;

public abstract class Personnage extends GameObject {

    private int pdv;
    private int dmg;
    private final static int MIN_VIE = 0;

    public Personnage(int pdv, int dmg, char appearance, int posX, int posY) {
        super(posX, posY, appearance);
        this.pdv = pdv;
        this.dmg = dmg;
    }

    public void checkVie() {
    }

    public int getPDV() {
        return pdv;
    }

    public int getDmg() {
        return dmg;
    }

    public void deplacer(int x, int y) {
        setPosX(this.getPosX() + x);
        setPosY(this.getPosY() + y);
    }

    public boolean isAlive() {
        return this.getPDV() > MIN_VIE;
    }

    public void modVie(int mod) {

        this.pdv += mod;
        if  (this.pdv < MIN_VIE) {
            this.pdv = 0;
        }

    }

    public void modVie(int max, boolean full) {
        this.pdv = max;
    }

    public void attaquer(Personnage personnage) {

	        personnage.modVie(- this.dmg);
    }
}