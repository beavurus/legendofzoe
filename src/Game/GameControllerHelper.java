package Game;

import Game.GameObjects.Entity.*;
import Game.GameObjects.Personnage.Personnage;
import Game.GameObjects.Personnage.Zoe;
import Game.Level.Level;

public abstract class GameControllerHelper {

    public static Entity[][] readWalls(boolean[][] murs) {

        Entity[][] entities = new Entity[14][40];

        for (int i = 0; i < murs.length; i++) {
            for (int j = 0; j < murs[0].length; j++) {
                if (murs[i][j]) {
                    entities[i][j] = new Mur(j,i);
                } else {
                    entities[i][j] = null;
                }
            }
        }

        return entities;

    }

    public static boolean collides(int x, int y, Entity[][] layout) {

        try {
            if (layout[y][x] != null) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            return true;
        }

        return false;

    }

    public static boolean isContained(int x, int y, Entity[][] layout) {

        int hauteur = layout.length;
        int largeur = layout[0].length;

        if ( x < 0 || x > largeur || y < 0 || y > hauteur ) {
            return false;
        }

        return true;

    }

    public static void dropItem(String item, Zoe zoe, Level currentLevel) {

        switch (item){
            case "hexaforce":
                currentLevel.setHexaforceCollecte(true);
                break;
            case "potionvie":
                zoe.modVie(100);
                break;
            case "coeur":
                zoe.modVie(1);
                break;
            default:
                break;
        }

    }

    public static boolean isClose(Personnage main, Personnage autre) {

        return isClose(main, autre.getPosX(), autre.getPosY());

    }

    public static boolean isClose(Personnage main, Entity objet) {

        return isClose(main, objet.getPosX(), objet.getPosY());

    }

    private static boolean isClose(Personnage main, int posX2, int posY2) {
        int posX = main.getPosX();
        int posY = main.getPosY();

        if ((posX-1 <= posX2 && posX2 <= posX+1) && (posY-1 <= posY2 && posY2 <= posY+1)) {
            return true;
        }

        return false;
    }

}
