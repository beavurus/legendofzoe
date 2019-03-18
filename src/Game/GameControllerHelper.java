package Game;

import Game.GameObjects.Entity.*;
import Game.GameObjects.Personnage.Personnage;
import Game.GameObjects.Personnage.Zoe;
import Game.Level.Level;

/**
 * Cette classe aide a alleger le code de GameController, et eviter le duplicat de code.
 */
public abstract class GameControllerHelper {

    /**
     * Cree un tableau d'entite et place les murs.
     * @param murs tableau 2D de boolean, qui determine le layout des murs
     * @return retourne un tableau 2D d'entites
     */
    public static Entity[][] readWalls(boolean[][] murs) {

        Entity[][] entities = new Entity[14][40];

        for (int i = 0; i < murs.length; i++) {
            for (int j = 0; j < murs[0].length; j++) {
                if (murs[i][j]) {
                    entities[i][j] = new Mur(j,i);
                } else {
                    entities[i][j] = null;
                }
            }
        }

        return entities;

    }

    /**
     * Verifie si un personnage peut se deplacer a la case voulue.
     * @param posX coordones de la case a evaluer - X
     * @param posY coordones de la case a evaluer - Y
     * @param layout entites du niveau, et leur positions.
     * @return true si il y a un objet, false sinon.
     */
    public static boolean collides(int posX, int posY, Entity[][] layout) {

        try {
            if (layout[posY][posX] != null) {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            return true;
        }

        return false;

    }

    /**
     * Methode qui verifie si un personnage depasse les limites du niveau lors de son prochain deplacement
     * @param posX coordones de la case a evaluer - X
     * @param posY coordones de la case a evaluer - Y
     * @param layout entites du niveau, et leur positions.
     * @return true si le deplacement est valide, false sinon.
     */
    public static boolean isContained(int posX, int posY, Entity[][] layout) {

        int hauteur = layout.length;
        int largeur = layout[0].length;

        if ( posX < 0 || posX > largeur || posY < 0 || posY > hauteur ) {
            return false;
        }

        return true;

    }

    /**
     * Determine ce qui se passe dependament de l'item que drop un coffre ou un monstre
     * @param item String decrivant l'item
     * @param zoe Utilisee pour modifier les stats de zoe.
     * @param currentLevel Utilise pour modifier si le morceau d'hexaforce est collecte.
     */
    public static void dropItem(String item, Zoe zoe, Level currentLevel) {

        switch (item){
            case "hexaforce":
                currentLevel.setHexaforceCollecte(true);
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

    /**
     * Overload pour un personnage
     * @param main personnage a evaluer
     * @param autre personnage a comparer
     * @return
     */
    public static boolean isClose(Personnage main, Personnage autre) {

        return isClose(main, autre.getPosX(), autre.getPosY());

    }

    /**
     * Overload pour un objet
     * @param main personnage a evaluer
     * @param objet objet a comparer
     * @return
     */
    public static boolean isClose(Personnage main, Entity objet) {

        return isClose(main, objet.getPosX(), objet.getPosY());

    }

    /**
     * Methode pour verifier si un objet, ou un personnage est proche d'un autre personnage.
     * Ici on utilise le methode Overloading pour eviter le duplicat de code.
     * @param main personnage a evaluer
     * @param posX2 position du gameObject X
     * @param posY2 position du gameObject Y
     * @return true si l'objet / personnage est dans les 8 cases adjacentes, false sinon.
     */
    private static boolean isClose(Personnage main, int posX2, int posY2) {
        int posX = main.getPosX();
        int posY = main.getPosY();

        if ((posX-1 <= posX2 && posX2 <= posX+1) && (posY-1 <= posY2 && posY2 <= posY+1)) {
            return true;
        }

        return false;
    }

}
