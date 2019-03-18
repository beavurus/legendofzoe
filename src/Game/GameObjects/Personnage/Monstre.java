package Game.GameObjects.Personnage;

import Game.GameObjects.Entity.*;

/**
 * class d'un monstre. On lui attribu un item, et une logique pour se deplacer dependament de la position de zoe.
 */
public class Monstre extends Personnage {

    private String item;

    /**
     * Constructeur d'un monstre
     * @param item Objet que porte le monstre.
     * @param posX {@link}
     * @param posY {@link}
     * @param numNiveau Niveau atteint (pour modifier la difficultee d'un monstre).
     */
    public Monstre(String item, int posX, int posY, int numNiveau) {

        super((int) Math.max(0.6*numNiveau, 1),
                (int) Math.max(0.4*numNiveau, 1),
                '@', posX, posY);

        this.item = item;
    }

    /**
     * Getter de l'item que porte le monstre. Cette methode n'est utilisee que si le monstre est mort.
     * Alors on change l'apparence du monstre a 'x'
     * @return Item que porte le monstre.
     */
    public String getItem() {
        setAppearance('x');
        return this.item;
    }

    /**
     * Methode qui defini comment le monstre devrait se deplacer dependament de la direction de zoe, et
     * de la disposition des murs.
     * @param zoe utilisee pour les coordonnes.
     * @param entities utlilisee pour la disposition des murs.
     */
    public void deplacer(Zoe zoe, Entity[][] entities) {

        if (this.isAlive()) {
            switch (getDirection(zoe)) {
                case "N":
                    if (!(entities[this.getPosY() - 1][this.getPosX()] instanceof Mur)) {
                        super.deplacer(0, -1);
                    }
                    break;
                case "NE":
                    if (!(entities[this.getPosY() - 1][this.getPosX() + 1] instanceof Mur)) {
                        super.deplacer(1, -1);
                    }
                    break;
                case "E":
                    if (!(entities[this.getPosY()][this.getPosX() + 1] instanceof Mur)) {
                        super.deplacer(1, 0);
                    }
                    break;
                case "SE":
                    if (!(entities[this.getPosY() + 1][this.getPosX() + 1] instanceof Mur)) {
                        super.deplacer(1, 1);
                    }
                    break;
                case "S":
                    if (!(entities[this.getPosY() + 1][this.getPosX()] instanceof Mur)) {
                        super.deplacer(0, 1);
                    }
                    break;
                case "SW":
                    if (!(entities[this.getPosY() + 1][this.getPosX()] instanceof Mur)) {
                        super.deplacer(-1, 1);
                    }
                    break;
                case "W":
                    if (!(entities[this.getPosY()][this.getPosX() - 1] instanceof Mur)) {
                        super.deplacer(-1, 0);
                    }
                    break;
                case "NW":
                    if (!(entities[this.getPosY() - 1][this.getPosX() - 1] instanceof Mur)) {
                        super.deplacer(-1, -1);
                    }
                    break;
                default:
                    break;

            }
        } else {
            this.setAppearance('x');
        }

    }

    /**
     * Methode qui retourne une direction (comme une boussole), pour savoir
     * comment le monstre devrait se deplacer.
     * @param zoe Utilise pour deduire la direction de Zoe
     * @return retourne un String de direction (NW, N, NE, E, SE, S, SW, W)
     */
    private String getDirection(Zoe zoe) {

        String direction = new String();

        if (zoe.getPosY() < this.getPosY()) {
            direction += "N";
        } else if (zoe.getPosY() > this.getPosY()) {
            direction += "S";
        }

        if (zoe.getPosX() < this.getPosX()) {
            direction += "W";
        } else if (zoe.getPosX() > this.getPosX()) {
            direction += "E";
        }

        return direction;
    }

}

