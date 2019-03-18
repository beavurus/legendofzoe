package Game.GameObjects.Personnage;

import Game.GameObjects.GameObject;

/**
 * Class d'un personnage.
 */
public abstract class Personnage extends GameObject {

    private int pdv;
    private int dmg;
    private final static int MIN_VIE = 0;

    /**
     * Constructeur d'un personnage.
     * @param pdv Points de vies du personnage.
     * @param dmg Degats que le personnage inflige.
     * @param appearance {@link}
     * @param posX {@link}
     * @param posY {@link}
     */
    public Personnage(int pdv, int dmg, char appearance, int posX, int posY) {
        super(posX, posY, appearance);
        this.pdv = pdv;
        this.dmg = dmg;
    }

    /**
     * Getter de points de vie.
     * @return points de vie du personnage.
     */
    public int getPDV() {
        return pdv;
    }

    /**
     * Deplace le personnage de x et y.
     * @param x Combien deplacer sur l'axe x.
     * @param y Combien deplacer sur l'axe y.
     */
    public void deplacer(int x, int y) {
        setPosX(this.getPosX() + x);
        setPosY(this.getPosY() + y);
    }

    /**
     * Verifie si un personnage est toujours en vie.
     * @return true s'il est encore vivant, false sinon.
     */
    public boolean isAlive() {
        return this.getPDV() > MIN_VIE;
    }

    /**
     * Modifier la vie d'un personnage, positivement ou negativement.
     * @param mod int positif si on veut ajoutter de la vie au personnage, negatif sinon.
     */
    public void modVie(int mod) {

        this.pdv += mod;
        if  (this.pdv < MIN_VIE) {
            this.pdv = 0;
        }

    }

    /**
     * Overload de modVie(int). Sert a donner une valeur directe au personnage.
     * @param max Combien de vie on veut qu'il ai.
     * @param full Parametre en plus pour differencier a modVie(int).
     */
    public void modVie(int max, boolean full) {
        this.pdv = max;
    }

    /**
     * Enleve des points de vie a 'autre' dependament des degats que le personnage inflige.
     * @param autre personnage que l'on veut attaquer.
     */
    public void attaquer(Personnage autre) {
	        autre.modVie(-this.dmg);
    }
}