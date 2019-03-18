package Game.GameObjects.Personnage;

/**
 * class de zoe. On ne fait que fixer pdv maximale a 5, et degats infliges a  1.
 */
public class Zoe extends Personnage {

    /**
     * Constructeur de la class.
     * @param posX {@link}
     * @param posY {@link}
     */
    public Zoe(int posX, int posY) {
        super(5, 1, '&', posX, posY);
    }

    /**
     * On fixe le maximum a 5.
     * @param mod int positif si on veut ajoutter de la vie au personnage, negatif sinon.
     */
    @Override
    public void modVie(int mod) {
        if (this.getPDV()+mod < 5) {
            super.modVie(mod);
        } else {
            super.modVie(5, true);
        }
    }
}