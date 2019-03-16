import GameObjects.Personnage.*;
import GameObjects.Entity.*;
import Level.*;
import java.util.LinkedList;

public class GameController {

    private Entity[][] entities = new Entity[14][40];
    private LinkedList<Monstre> monstres = new LinkedList<Monstre>();
    private Zoe zoe;

    public GameController() {

    }



    public void tourMonstres() {

        for (Monstre monstre : monstres) {
            monstre.deplacer(zoe);
        }

    }

    public void tourZoe(char c) {

        // TODO ajoutter methode pour verifier si le deplacement garde zoe dans le niveau
    	switch (c) {
    		case 'w' :
    		    zoe.deplacer(0, -1);
    			break;
    		case 'a' :
    		    zoe.deplacer(-1, 0);
    			break;
    		case 's' :
    		    zoe.deplacer(0, 1);
    			break;
    		case 'd' :
    		    zoe.deplacer(1, 0);
    			break;
    		case 'c' :
    		    creuser(zoe);
    			break;
    		case 'x' :
    		    zoe.deplacer(-1, 0);
    		    break;
    		case 'o' :
		        for (int i = zoe.getPosX()-1; i <= zoe.getPosX()+1; i++) {
		            for (int j = zoe.getPosY()-1; j <= zoe.getPosY()+1; j++) {

		                if (entities[i][j] instanceof Mur) {
		                    entities[i][j] = null;
		                }
		            }
		        }
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
        Level level = new Level(1);
        boolean[][] murs = level.getMurs();
        String[] objets = level.getObjects();

        entities = GameControllerHelper.readWalls(murs);

        // TODO move some code to GameControllerHelper to ligthen class.
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

        Personnage.setLayout(entities);

    }

    //TODO methode qui verifie si le jeu est termine (a chaque tour)

}
