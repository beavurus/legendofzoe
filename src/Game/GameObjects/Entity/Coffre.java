package Game.GameObjects.Entity;

/**
 * Class d'un coffre, contient un item. L'apparence par defaut d'un coffre est '$'
 */
public class Coffre extends Entity {

    private String item;

    /**
     * Constructeur d'un coffre. On ne fait qu'ajoutter la variable item.
     * @param posX {@link}
     * @param posY {@link}
     * @param item String, Item contenu dans le coffre.
     */
    public Coffre(int posX, int posY, String item) {
        super(posX, posY, '$');
         this.item = item;
    }

    public String getItem() {
        this.setAppearance('_');
        return this.item;
    }

    /**
     * Methode pour verifier si un coffre a ete ouvert.
     * @return boolean, si l'apparence du coffre est '_', on sait qu'il est ouvert.
     */
    public boolean isOpen() {
        return this.getAppearance() == '_';
    }

}
