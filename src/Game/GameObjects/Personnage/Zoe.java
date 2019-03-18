package Game.GameObjects.Personnage;

public class Zoe extends Personnage {

    public Zoe(int x, int y) {
        super(5, 1, '&', x, y);
    }

    @Override
    public void modVie(int mod) {
        if (this.getPDV()+mod < 5) {
            super.modVie(mod);
        } else {
            super.modVie(5, true);
        }
    }
}