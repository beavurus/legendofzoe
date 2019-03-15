package GameObjects.Entity;

import GameObjects.GameObject;

public abstract class Entity extends GameObject {

    private static Entity[][] layout;

    public Entity(int posX, int posY, char appearance) {
        super(posX, posY, appearance);
    }

    public static void setLayout(Entity[][] layout) {
        Entity.layout = layout;
    }

    public static Entity[][] getLayout() {
        return layout;
    }
}
