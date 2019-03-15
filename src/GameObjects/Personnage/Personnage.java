package Personnage;

public abstract class Personnage {

    private int pdv;
    private int dmg;
    private int posX;
    private int posY;
    private final int MIN_VIE = 0;
    private char disp;


    public Personnage(int pdv, int dmg, char disp) {
        this.pdv = pdv;
        this.dmg = dmg;
        this.disp = disp;
    }

    public int getPDV() {
        return pointsDeVie;
    }

    public int getDmg() {
        return degats;
    }
    
    public int getPosX() {
        return posX;
    }
    
    public int getPosY() {
        return posY;
    }

    public char getDisp() {
        return apparence;
    }

    public void deplacer(int x, int y) {
        this.posX += x;
        this.posY += y;
    }

    public void modVie(int mod) {

        this.pointsDeVie += mod;
    }

    public void attaquer(Personnage personnage) {

	        personnage.modVie(- this.dmg);
    }
}