package Game;

import Game.GameObjects.Entity.*;
import Game.GameObjects.Personnage.*;

import java.util.LinkedList;

/**
 * Classe qui s'occupe d'afficher le jeu sur la console.
 */
public abstract class RenderEngine {

    public static void flushScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * S'occupe d'afficher correctement le niveau a la console. On s'epare les entites
     * @param game Tableau 2D qui contient l'information sur tout les objets du niveau
     * @param monstres Tableau qui contient tout les monstres du niveau actuel
     * @param zoe Joueur.
     */
    public static void render(Entity[][] game, LinkedList<Monstre> monstres, Zoe zoe, int numNiveau) {

        flushScreen();

        char[][] level = new char[14][40];

        for (int y = 0; y < 14; y++) {
            for (int x = 0; x < 40; x++) {
                if (game[y][x] != null) {
                    level[y][x] = game[y][x].getAppearance();
                } else {
                    level[y][x] = ' ';
                }
            }
        }

        for (Monstre monstre : monstres) {
            level[monstre.getPosY()][monstre.getPosX()] = monstre.getAppearance();
        }

        if (zoe != null) {
            level[zoe.getPosY()][zoe.getPosX()] = zoe.getAppearance();
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
