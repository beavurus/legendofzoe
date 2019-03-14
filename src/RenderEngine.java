import GameObjects.Entity.Entity;
import GameObjects.Personnage.*;

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

        for (int i = 0; i < game.length; i++) {
            String s = new String();
            for (Entity entity : game[i]) {
                if (entity == null) {
                    s += " ";
                } else {
                    s += entity.getAppearance();
                }
            }
            System.out.println(s);
        }
    }

}
