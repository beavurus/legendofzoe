package GameObjects.Entity;

import GameObjects.Dropable;

public class Coffre extends Entity implements Dropable {

    private String item;

    public Coffre(int posX, int posY, char appearance, String item) {
        super(posX, posY, appearance);
         this.item = item;
    }

    public String getItem() {
        this.setAppearance('_');
        return this.item;
    }

    public boolean isOpen() {
        return this.getAppearance() == '_';
    }

}
