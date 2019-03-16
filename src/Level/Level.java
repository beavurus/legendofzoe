package Level;

import java.util.Arrays;

public class Level {

    private Paire<boolean[][], String[]> niveau;
    private boolean[][] murs;
    private String[] objects;

    public Level(int numNiveau) {

        niveau = LevelGenerator.generateLevel(numNiveau);
        murs = niveau.getKey();
        objects = niveau.getValue();

    }

    public boolean[][] getMurs() {

        boolean[][] resulat = new boolean[14][40];

        for (int y = 0; y < murs.length; y++) {
            for (int x = 0; x < murs[0].length; x++) {
                resulat[y][x] = murs[y][x];
            }
        }
        assert Arrays.deepEquals(resulat, murs) : "Erreur de copie de tableau.";

        return resulat;
    }

    public String[] getObjects() {
        return objects;
    }

}
