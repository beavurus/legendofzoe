import java.util.Scanner;

/**
 * Classe principale du programme.
 *
 * NOTEZ : VOUS NE DEVEZ PAS RENOMMER CETTE CLASSE
 */
public class LegendOfZoe {

    public static void main(String[] args) {

        Messages.afficherIntro();

        GameController gameController = new GameController();
        gameController.nextLevel();
        gameController.render();
        Scanner s = new Scanner(System.in);
        gameController.tourZoe(s.nextLine().charAt(0));
        gameController.render();

//        Messages.afficherIntro();
//
//        GameController controller = new GameController();
//        Scanner s = new Scanner(System.in);
//
//        while (true) {
//            String input = s.nextLine();
//            controller.render();
//            for (int i = 0; i < input.length(); i++) {
//                controller.tourZoe((input.charAt(i)));
//                controller.tourMonstres();
//            }
//        }

    }
}
