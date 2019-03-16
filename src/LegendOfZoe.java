import java.util.Scanner;

/**
 * Classe principale du programme.
 *
 * NOTEZ : VOUS NE DEVEZ PAS RENOMMER CETTE CLASSE
 */
public class LegendOfZoe {

    public static void main(String[] args) {

        Messages.afficherIntro();

//        GameController gameController = new GameController();
//        gameController.nextLevel();
//        gameController.render();
//        Scanner s = new Scanner(System.in);
//        gameController.tourZoe(s.nextLine().charAt(0));
//        gameController.tourMonstres();
//        gameController.render();

        GameController controller = new GameController();
        Scanner s = new Scanner(System.in);
        controller.nextLevel();
        controller.render();
        while (true) {
            String input = s.nextLine();
            char[] commandes = input.toCharArray();
            if (commandes.length != 0) {
                for (char c : commandes) {
                    controller.tourZoe(c);
                    controller.tourMonstres();
                }
            } else {
                controller.tourMonstres();
            }
            controller.render();
        }

    }
}
