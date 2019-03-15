package GameObjects.Entity;

public class Coffre extends Entity {

    private String item;

    public Coffre(int posX, int posY, char appearance, String item) {
        super(posX, posY, appearance);
        this.item = item;
    }

//    public void ouvrir() {
//
//    	switch (this.item) {
//
//    		case "hexaforce" : //TODO;
//    			break;
//    		//case "potionvie" : zoe.addPV(1);
//    			break;
//    		//case "coeur" : zoe.setPV(5);
//    			break;
//    		default : break;
//    	}
//
//    	this.appearance = '_';
//    }

}
