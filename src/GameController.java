import GameObjects.Entity.Entity;
import Level.*;
import GameObjects.Personnage.*;
import GameObjects.Entity.*;
import java.util.LinkedList;

public class GameController {

    private Entity[][] entities = new Entity[14][40];
    private LinkedList<Monstre> monstres = new LinkedList<Monstre>();

    public GameController() {

    }

    public void tourMonstres() {

    }

    public void tourZoe(char c) {

    	switch (c) {

    		case w : zoe.deplacer(0, 1);
    		case a : zoe.deplacer(-1, 0);
    		case s : zoe.deplacer(0, -1);
    		case d : zoe.deplacer(1, 0);
    		case c : creuser(zoe.getPosX(), zoe.getPosY());
    		case x : zoe.deplacer(-1, 0);
    		case o : zoe.deplacer(0, -1);
    		case q : zoe.deplacer(1, 0);
    	}

    }

    public void creuser(int x, int y) {

        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                if (entities[i][j] instanceof Mur) {
                    entities[i][j] = null;
                }
            }
        }
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
                    entities[Integer.parseInt(s[2])][Integer.parseInt(s[1])] = new Sortie();
                    break;
                case "zoe":
<<<<<<< HEAD
                    this.zoe = new Zoe(Integer.parseInt(s[2]), Integer.parseInt(s[1]));
=======
                    Zoe zoe = new Zoe(Integer.parseInt(s[1]), Integer.parseInt(s[2]));
>>>>>>> 6042cd6f9b3419b482319288cf4cc64ee2536f2b
                    break;
                default:
                    break;
            }
        }

    }

    //TODO methode qui verifie si le jeu est termine (a chaque tour)

}
