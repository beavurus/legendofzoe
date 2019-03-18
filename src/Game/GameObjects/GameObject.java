package Game.GameObjects;

/**
 * Classe mere de tout les objets / personnages du jeu.
 * Ils partagent tous des coordonnes, et ont une apparence (un char).
 * Setters et getters aussi.
 */
public abstract class GameObject {

    private int posX, posY;
    private char appearance;

    /**
     * Constructeur d'un gameObject
     * @param posX int, coordonnee X de l'objet
     * @param posY int, coordonnee Y de l'objet
     * @param appearance char, apparence de l'objet
     */
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
