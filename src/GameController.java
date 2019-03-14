import GameObjects.Entity.Entity;
import Level.*;
import GameObjects.Personnage.*;
import GameObjects.Entity.*;
import java.util.LinkedList;

public class GameController {

    private Entity[][] entities = new Entity[14][40];
    private LinkedList<Monstre> monstres = new LinkedList<Monstre>();
    private Zoe zoe;

    public GameController() {

    }

    public void tourMonstres() {

    }

    public void tourZoe(char c) {

    }

    public void render() {
        RenderEngine.render(entities, monstres, zoe);
    }

    public void nextLevel() {
        Level level = new Level(1); //TODO changer numNiveau
        boolean[][] murs = level.getMurs();
        String[] objets = level.getObjects();

        for (int i = 0; i < murs.length; i++) {
            for (int j = 0; j < murs[0].length; j++) {
                if (murs[i][j]) {
                    entities[i][j] = new Mur();
                } else {
                    entities[i][j] = null;
                }
            }
        }

        for (int i = 0; i < objets.length; i++) {
            String[] s = objets[i].split(":");
            switch (s[0]) {
                case "tresor":
                    entities[Integer.parseInt(s[3])][Integer.parseInt(s[2])] = new Coffre(s[1]);
                    break;
                case "monstre":
                    monstres.add(new Monstre(
                            s[1],
                            Integer.parseInt(s[3]),
                            Integer.parseInt(s[2]))
                    );
                    break;
                case "sortie":
                    System.out.println(s[2]);
                    System.out.println(s[1]);
                    entities[Integer.parseInt(s[2])][Integer.parseInt(s[1])] = new Sortie();
                    break;
                case "zoe":
                    zoe = new Zoe(Integer.parseInt(s[1]), Integer.parseInt(s[2]));
                    break;
                default:
                    break;
            }
        }

    }

    //TODO methode qui verifie si le jeu est ermine (a chaque tour)

}