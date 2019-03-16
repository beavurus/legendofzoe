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

}
