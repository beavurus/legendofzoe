package GameObjects.Personnage;

public abstract class Personnage {

    private int pdv;
    private int dmg;
    private int posX;
    private int posY;
    private final int MIN_VIE = 0;
    private char appearance;


    public Personnage(int pdv, int dmg, char appearance) {
        this.pdv = pdv;
        this.dmg = dmg;
        this.appearance = appearance;
    }

    public int getPDV() {
        return pdv;
    }

    public int getDmg() {
        return dmg;
    }
    
    public int getPosX() {
        return posX;
    }
    
    public int getPosY() {
        return posY;
    }

    public char getAppearance() {
        return appearance;
    }

    public void deplacer(int x, int y) {
        this.posX += x;
        this.posY += y;
    }

    public void modVie(int mod) {

        this.pdv += mod;
    }

    public void attaquer(Personnage personnage) {

	        personnage.modVie(- this.dmg);
    }
}