import GameObjects.Personnage.*;
import GameObjects.Entity.*;
import Level.*;

import java.util.Arrays;
import java.util.LinkedList;

public class GameController {

    private Entity[][] entities = new Entity[14][40];
    private LinkedList<Monstre> monstres = new LinkedList<Monstre>();
    private Zoe zoe;
    private Level currentLevel;

    public GameController() {

    }

    public void tourMonstres() {

        for (Monstre monstre : monstres) {
            monstre.deplacer(zoe, entities);
        }

    }

    public void tourZoe(char c) {

    	switch (c) {
    		case 'w' :
    		    if (!GameControllerHelper.collides(zoe.getPosX(), zoe.getPosY()-1, entities)) {
                    zoe.deplacer(0, -1);
                }
    			break;
    		case 'a' :
                if (!GameControllerHelper.collides(zoe.getPosX()-1, zoe.getPosY(), entities)) {
                    zoe.deplacer(-1, 0);
                }
    			break;
    		case 's' :
                if (!GameControllerHelper.collides(zoe.getPosX(), zoe.getPosY()+1, entities)) {
                    zoe.deplacer(0, 1);
                }
    			break;
    		case 'd' :
                if (!GameControllerHelper.collides(zoe.getPosX()+1, zoe.getPosY(), entities)) {
                    zoe.deplacer(1, 0);
                }
    			break;
    		case 'c' :
    		    creuser(zoe);
    			break;
    		case 'x' :
    		    zoe.deplacer(-1, 0);
    		    break;
    		case 'o' :
                //open(zoe);
		        break;
    		case 'q' :
    		    System.exit(0);
    		    break;
    		default:
    		    break;
    	}
    }

    private void creuser(Zoe zoe) {

        int posX = zoe.getPosX();
        int posY = zoe.getPosY();

        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                if (GameControllerHelper.isContained(posX + x, posY + y, entities)
                        && entities[posY + y][posX + x] instanceof Mur) {
                    entities[posY + y][posX + x] = null;
                }

            }
        }

    }

    public void render() {
        RenderEngine.render(entities, monstres, zoe);
    }

    public void nextLevel() {

        //TODO changer numNiveau
        int numNiveau = 1;
        currentLevel = new Level(numNiveau);
        boolean[][] murs = currentLevel.getMurs();
        String[] objets = currentLevel.getObjects();

        entities = GameControllerHelper.readWalls(murs);

        for (int i = 0; i < objets.length; i++) {
            String[] s = objets[i].split(":");
            switch (s[0]) {
                case "tresor":
                    int posX = Integer.parseInt(s[2]);
                    int posY = Integer.parseInt(s[3]);
                    entities[posY][posX] = new Coffre(posX, posY, '$', s[1]);
                    break;
                case "monstre":
                    posX = Integer.parseInt(s[2]);
                    posY = Integer.parseInt(s[3]);
                    monstres.add(new Monstre(s[1], posX, posY));
                    break;
                case "sortie":
                    posX = Integer.parseInt(s[1]);
                    posY = Integer.parseInt(s[2]);
                    entities[posY][posX] = new Sortie(posX, posY, 'E');
                    break;
                case "zoe":
                    posX = Integer.parseInt(s[1]);
                    posY = Integer.parseInt(s[2]);
                    this.zoe = new Zoe(posX, posY);
                    break;
                default:
                    break;
            }
        }

    }

    //TODO methode qui verifie si le jeu est termine (a chaque tour)

}
