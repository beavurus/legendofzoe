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

        controller.nextLevel();
        controller.render();

        while (controller.getNumNiveau() < 7 && !controller.isZoeDead()) {

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

        if (controller.getNumNiveau() == 7) {

            RenderEngine.flushScreen();
            Messages.afficherVictoire();

        } else if (controller.isZoeDead()) {

            RenderEngine.flushScreen();
            Messages.afficherDefaite();

        }


    }
}
