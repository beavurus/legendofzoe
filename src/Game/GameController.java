package Game;

import Game.GameObjects.Personnage.*;
import Game.GameObjects.Entity.*;
import Game.Level.*;
import Game.Messages.GameMessages;

import java.util.LinkedList;

/**
 * Classe qui controle le fonctionnement du jeu, rassemble l'information, et la rend comprehensible,
 * et utilisable.
 */
public class GameController {

    /* On a ici les attributs pricnipaux du jeu comme :
	 * - un tableau pour representer la grille de jeu
	 * - une liste chainee qui contient les monstres
	 * - une liste chainee qui contient les objets
	 */
    
    private Entity[][] entities = new Entity[14][40];
    private LinkedList<Monstre> monstres = new LinkedList<>();
    private LinkedList<Entity> objets = new LinkedList<>();
    private Zoe zoe;
    private static Level currentLevel;
    private int numNiveau = 1;

    public GameController() {
    }

    /**
     * Verifie si Zoe est morte...
     * @return Vrai si zoe n'as plus de points de vie, faux sinon
     */
    public boolean isZoeDead() {
        return !zoe.isAlive();
    }

    /**
     * Retourne le numero du Niveau prochain.
     * @return int numNiveau
     */
    public int getNumNiveau() {
        return numNiveau;
    }

    /**
     * Passe a travers la liste des monstres:
     * - Si zoe est proche, on l'attaque
     * - Sinon on se deplace vers elle.
     */
    public void tourMonstres() {

        for (Monstre monstre : monstres) {

            if (GameControllerHelper.isClose(monstre, zoe) && monstre.isAlive()) {
                monstre.attaquer(zoe);
            } else if (monstre.isAlive()) {
                monstre.deplacer(zoe, entities);
            }

        }

    }

    /**
     * Methode qui controle le jeu.
     * 
     * Pour wasd, on verifie avant si zoe peut effectivement se deplacer a la case voulu.
     * 
     * Pour c, on enleve les murs autour de zoe.
     * Pour o, on ouvre les coffres autour de zoe.
     * Pour x, on attaque les monstres autour de zoe.
     * Pour q, on arrete le programme.
     * 
     * @param c entree du joueur
     *
     * (Si c'est un string, on le decompose avant de faire appel a cette methode).
     * @return true si zoe joue, false sinon.
     */
    public boolean tourZoe(char c) {

            switch (c) {
                case 'w':
                    if (!GameControllerHelper.collides(zoe.getPosX(), zoe.getPosY() - 1, entities)) {
                        zoe.deplacer(0, -1);
                    }
                    break;
                case 'a':
                    if (!GameControllerHelper.collides(zoe.getPosX() - 1, zoe.getPosY(), entities)) {
                        zoe.deplacer(-1, 0);
                    }
                    break;
                case 's':
                    if (!GameControllerHelper.collides(zoe.getPosX(), zoe.getPosY() + 1, entities)) {
                        zoe.deplacer(0, 1);
                    }
                    break;
                case 'd':
                    if (!GameControllerHelper.collides(zoe.getPosX() + 1, zoe.getPosY(), entities)) {
                        zoe.deplacer(1, 0);
                    }
                    break;
                case 'c':
                    creuser(zoe);
                    break;
                case 'x':
                    for (Monstre monstre : monstres) {
                        if (GameControllerHelper.isClose(zoe, monstre) && monstre.isAlive()) {
                            zoe.attaquer(monstre);
                            if (!monstre.isAlive()) {
                                GameControllerHelper.dropItem(monstre.getItem(), zoe, currentLevel);
                                GameMessages.itemPickup(monstre.getItem());
                            }
                        }
                    }
                    break;
                case 'o':
                    open(zoe);
                    break;
                case 'q':
                    System.exit(0);
                    break;
                default:
                    break;
            }
        // Si Zoe est proche de la sortie, et qu'elle possede le morceau d'hexaforce, on passe au prochain niveau.
        if (currentLevel.isHexaforceCollecte() && exit(zoe)) {
            nextLevel();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Enleve les instances de murs dans entities, autour des coordonnes de zoe.
     * @param zoe Pour trouver les coordonnes.
     */
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

    /**
     * On verifie s'il y a des coffres autour de zoe
     * Si c'est le cas, on verifie s'ils sont ouverts
     * Si oui, on drop l'item, puis on change l'apparence du coffre.
     * @param zoe Pour les coordonnes de zoe.
     */
    private void open(Zoe zoe) {

        for (Entity objet : objets) {
            if (objet instanceof Coffre && GameControllerHelper.isClose(zoe, objet) && !((Coffre) objet).isOpen()) {
                GameControllerHelper.dropItem(((Coffre) objet).getItem(), zoe, currentLevel);
                GameMessages.itemPickup(((Coffre) objet).getItem());
            }
        }

    }

    /**
     * Methode qui verifie si zoe est proche d'une sortie.
     * @param zoe instance de zoe.
     * @return true si une sortie est proche, false sinon.
     */
    private boolean exit(Zoe zoe) {

        for (Entity objet : objets) {
            if (objet instanceof Sortie && GameControllerHelper.isClose(zoe, objet)) {
                return true;
            }
        }

        return false;

    }

    /**
     * Fait apel a RenderEngine, pour afficher le jeu textuellement.
     */
    public void render() {
        RenderEngine.render(entities, monstres, zoe, numNiveau);
    }

    /**
     * Cree un nouveau niveau, et lis les donnees en creant les objets necessaires, et en remplissant leurs
     * caracteristiques.
     */
    public void nextLevel() {

        currentLevel = new Level(numNiveau);
        numNiveau++;
        boolean[][] murs = currentLevel.getMurs();
        String[] objets = currentLevel.getObjects();

        entities = GameControllerHelper.readWalls(murs);
        this.monstres.clear();
        this.objets.clear();

        /**
        * On lit le string contenant tous les objets
        * En fonction du nom de l'objet (s[0]),
        * on le place dans le tableau des entitees
        */
    
        
        for (int i = 0; i < objets.length; i++) {
            String[] s = objets[i].split(":");
            switch (s[0]) {
                case "tresor":
                    int posX = Integer.parseInt(s[2]);
                    int posY = Integer.parseInt(s[3]);
                    entities[posY][posX] = new Coffre(posX, posY, s[1]);
                    this.objets.add(entities[posY][posX]);
                    break;
                case "monstre":
                    posX = Integer.parseInt(s[2]);
                    posY = Integer.parseInt(s[3]);
                    monstres.add(new Monstre(s[1], posX, posY, numNiveau));
                    break;
                case "sortie":
                    posX = Integer.parseInt(s[1]);
                    posY = Integer.parseInt(s[2]);
                    entities[posY][posX] = new Sortie(posX, posY);
                    this.objets.add(entities[posY][posX]);
                    break;
                case "zoe":
                    posX = Integer.parseInt(s[1]);
                    posY = Integer.parseInt(s[2]);
                    if (zoe == null) {
                        this.zoe = new Zoe(posX, posY);
                    } else {
                        zoe.setPosX(posX);
                        zoe.setPosY(posY);
                    }
                    break;
                default:
                    break;
            }
        }

    }

}
