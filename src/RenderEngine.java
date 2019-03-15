import GameObjects.Entity.Entity;
import GameObjects.Personnage.*;
import Level.Paire;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Classe qui s'occupe d'afficher le jeu sur la console.
 */
public abstract class RenderEngine {

    /**
     * S'occupe d'afficher correctement le niveau a la console. On s'epare les entites
     * @param game Tableau 2D qui contient l'information sur tout les objets du niveau
     * @param monstres Tableau qui contient tout les monstres du niveau actuel
     * @param zoe Joueur.
     */
    public static void render(Entity[][] game, LinkedList<Monstre> monstres, Zoe zoe) {

        char[][] level = new char[14][40];

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 40; j++) {
                if (game[i][j] != null) {
                    level[i][j] = game[i][j].getAppearance();
                } else {
                    level[i][j] = ' ';
                }
            }
        }

        for (Monstre monstre : monstres) {
            level[monstre.getPosX()][monstre.getPosY()] = monstre.getAppearance();
        }

        if (zoe != null) {
            level[zoe.getPosX()][zoe.getPosY()] = zoe.getAppearance();
        }

        String s = new String();
        for (char[] c : level) {
            for (char c1 : c) {
                s += c1;
            }
            System.out.println(s);
            s = "";
        }

    }

}
