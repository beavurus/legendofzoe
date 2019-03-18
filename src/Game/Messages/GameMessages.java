package Game.Messages;

public abstract class GameMessages extends Messages {

        private static String activeInfo;

        public static void itemPickup(String item) {

            switch (item) {

                case "potiondevie":
                    activeInfo = "Zoe reçoit une Potion de Vie!";
                    break;
                case "coeur":
                    activeInfo = "Zoe reçoit un Coeur!";
                    break;
                case "hexaforce":
                    activeInfo = "Zoe reçoit le Morceau d'Hexaforce!";
                    break;
                default:
                    break;

            }

        }

        public static String getActiveInfo() {
            return activeInfo;
        }

}
