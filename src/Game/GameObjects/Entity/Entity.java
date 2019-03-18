package Game.GameObjects.Entity;

import Game.GameObjects.GameObject;

/**
 * Classe utilisee pour l'heritage, et le groupement d'entitees.
 */
public abstract class Entity extends GameObject {

    public Entity(int posX, int posY, char appearance) {
        super(posX, posY, appearance);
    }

}
