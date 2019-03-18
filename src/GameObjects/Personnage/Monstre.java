package GameObjects.Personnage;

import GameObjects.Dropable;
import GameObjects.Entity.*;

public class Monstre extends Personnage implements Dropable {

    private String item;

    public Monstre(String item, int x, int y, int numNiveau) {

        super((int) Math.max(0.6*numNiveau, 1),
                (int) Math.max(0.4*numNiveau, 1),
                '@', x,y);
        this.item = item;
    }

    public static void dropItem(String item) {

    }

    @Override
    public void modVie(int mod) {
        super.modVie(mod);
        if (!this.isAlive()) {
            //TODO drop item
        }
    }

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

