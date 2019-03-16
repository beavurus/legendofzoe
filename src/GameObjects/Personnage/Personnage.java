package GameObjects.Personnage;

import GameObjects.Entity.Entity;
import GameObjects.GameObject;

public abstract class Personnage extends GameObject {

    private int pdv;
    private int dmg;
    private static Entity[][] layout;

    /**
     * Ici on garde la reference active, comme ca tout changement dans le layout
     * au niveau du GameController sont en synchronisation avec la variable layout.
     * (Utilisee pour verifier l'existence de murs, coffres, etc...)
     * @param entities Layout du niveau (murs, coffres, sorties...)
     */
    public static void setLayout(Entity[][] entities) {
        layout = entities;
    }

    public Personnage(int pdv, int dmg, char appearance, int posX, int posY) {
        super(posX, posY, appearance);
        this.pdv = pdv;
        this.dmg = dmg;
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

    public void modVie(int mod) {

        this.pdv += mod;
    }

    public void attaquer(Personnage personnage) {

	        personnage.modVie(- this.dmg);
    }
}