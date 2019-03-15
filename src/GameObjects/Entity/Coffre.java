package GameObjects.Entity;

public class Coffre extends Entity {

    private String item;
    private char appearance = '$';

    @Override
    public char getDisp() {
        return disp;
    }

    public Coffre(String item) {
        this.item = item;
    }

    public ouvrir() {

    	switch (this.item) {

    		case "hexaforce" : //TODO;
    			break;
    		case "potionvie" : zoe.addPV(1);
    			break;
    		case "coeur" : zoe.setPV(5);
    			break;
    		default : break;
    	}

    	this.appearence = '_';
    }
}
