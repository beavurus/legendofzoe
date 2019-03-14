package Level;

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
        return murs;
    }

    public String[] getObjects() {
        return objects;
    }

}
