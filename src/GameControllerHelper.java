import GameObjects.Entity.*;

public abstract class GameControllerHelper {

//    In progress: BONUS
//    public static int[][] findPath(int posX, int posY, boolean[][] murs) {
//
//        int[][] paths = new int[murs.length][murs[0].length];
//
//        for (int y = 0; y < paths.length; y++) {
//            for (int x = 0; x < paths[0].length; x++) {
//
//                if (!murs[y][x]) {
//                    paths[y][x] = (posY - y) - (posX - x);
//                } else {
//                    paths[y][x] = -1;
//                }
//            }
//        }
//        return paths;
//    }

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
