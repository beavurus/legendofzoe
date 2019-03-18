import Game.GameController;
import Game.Messages.Messages;
import Game.RenderEngine;

import java.util.Scanner;

/**
 * Classe principale du programme.
 *
 * NOTEZ : VOUS NE DEVEZ PAS RENOMMER CETTE CLASSE
 */
public class LegendOfZoe {

    public static void main(String[] args) {

        Messages.afficherIntro();

        GameController controller = new GameController();
        Scanner s = new Scanner(System.in);
        boolean gameActive = true;

        controller.nextLevel();
        controller.render();

        while (gameActive) {
            if (controller.getNumNiveau() == 7) {
                gameActive = false;
                RenderEngine.flushScreen();
                Messages.afficherVictoire();
                break;
            } else if (controller.isZoeDead()){
                gameActive = false;
                RenderEngine.flushScreen();
                Messages.afficherDefaite();
            }
            String input = s.nextLine();
            char[] commandes = input.toCharArray();
            if (commandes.length != 0) {
                for (char c : commandes) {
                    if (controller.tourZoe(c)) {
                        controller.tourMonstres();
                    }
                }
            } else {
                controller.tourMonstres();
            }
            controller.render();
        }

    }
}
