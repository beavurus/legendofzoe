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
    private int numNiveau = 1;

    public GameController() {

    }

    public void tourMonstres() {

        for (Monstre monstre : monstres) {

            int posX = monstre.getPosX();
            int posY = monstre.getPosY();
            int zoePosX = zoe.getPosX();
            int zoePosY = zoe.getPosY();

            if ((zoePosX-1 <= posX && posX <= zoePosX+1) && (zoePosY-1 <= posY && posY <= zoePosY+1) && monstre.isAlive()) {
                monstre.attaquer(zoe);
            } else if (monstre.isAlive()) {
                monstre.deplacer(zoe, entities);
            } else {
                monstre.setAppearance('x');
            }

        }

    }

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
                    for (int y = -1; y <= 1; y++) {
                        for (int x = -1; x <= 1; x++) {
                            int posX = x + zoe.getPosX();
                            int posY = y + zoe.getPosY();
                            if ((posX >= 0 && posX < entities[0].length && posY >= 0 && posY < entities.length)) {
                                for (Monstre monstre : monstres) {
                                    if (posX == monstre.getPosX() && posY == monstre.getPosY()) {
                                        zoe.attaquer(monstre);
                                    }
                                }
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

        if (currentLevel.isHexaforceCollecte() && exit(zoe)) {
            nextLevel();
            return false;
        } else {
            return true;
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

    private void open(Zoe zoe) {

        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {

                int posX = x + zoe.getPosX();
                int posY = y + zoe.getPosY();

                try {
                    if (entities[posY][posX] instanceof Coffre) {

                        Coffre tresor = (Coffre) entities[posY][posX];
                        if (!tresor.isOpen()) {
                            switch (tresor.getItem()) {

                                case "hexaforce":
                                    currentLevel.setHexaforceCollecte(true);
                                    System.out.println("Hexaforce collecté!");
                                    break;

                                case "potionvie":
                                    zoe.modVie(100);
                                    break;

                                case "coeur":
                                    zoe.modVie(1);
                                    break;

                                default:
                                    break;

                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException aioobe) {
                }

            }
        }
    }

    private boolean exit(Zoe zoe) {

        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                if (entities[y + zoe.getPosY()][x + zoe.getPosX()] instanceof Sortie) {
                    return true;
                }
            }
        }

        return false;

    }

    public void render() {
        RenderEngine.render(entities, monstres, zoe, numNiveau);
    }

    public void nextLevel() {

        currentLevel = new Level(numNiveau);
        numNiveau++;
        boolean[][] murs = currentLevel.getMurs();
        String[] objets = currentLevel.getObjects();

        entities = GameControllerHelper.readWalls(murs);
        monstres.clear();

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
                    monstres.add(new Monstre(s[1], posX, posY, numNiveau));
                    break;
                case "sortie":
                    posX = Integer.parseInt(s[1]);
                    posY = Integer.parseInt(s[2]);
                    entities[posY][posX] = new Sortie(posX, posY, 'E');
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
