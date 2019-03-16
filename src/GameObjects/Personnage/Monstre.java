package GameObjects.Personnage;

public class Monstre extends Personnage {

    private String item;

    public Monstre(String item, int x, int y) {
        super(1,1, '@', x,y);
        this.item = item;
    }

    public void deplacer(Zoe zoe) {

        switch (getDirection(zoe)) {
            case "N":
                super.deplacer(0, -1);
                break;
            case "NE":
                super.deplacer(1, -1);
                break;
            case "E":
                super.deplacer(1,0);
                break;
            case "SE":
                super.deplacer(1, 1);
                break;
            case "S":
                super.deplacer(0,1);
                break;
            case "SW":
                super.deplacer(-1, 1);
                break;
            case "W":
                super.deplacer(-1, 0);
                break;
            case "NW":
                super.deplacer(-1, -1);
                break;
            default:
                break;

        }

    }

    /**
     * Methode qui retourne une direction (comme une boussole), pour savoir
     * comment le monstre devrait se deplacer.
     * @param zoe Utilise pour deduire la direction de Zoe
     * @return retourne un String de direction (NW, N, NE, E, SE, S, SW, W)
     */
    // TODO Voir si un mur bloque le monstre ou pas.
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

    // TODO des tests pour la methode direction.
    private void getDirectionTests() {

    }

}

