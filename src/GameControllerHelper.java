import GameObjects.Entity.*;

public abstract class GameControllerHelper {

    public static Entity[][] readWalls(boolean[][] murs) {

        Entity[][] entities = new Entity[14][40];

        for (int i = 0; i < murs.length; i++) {
            for (int j = 0; j < murs[0].length; j++) {
                if (murs[i][j]) {
                    entities[i][j] = new Mur(j,i,'#');
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

}
