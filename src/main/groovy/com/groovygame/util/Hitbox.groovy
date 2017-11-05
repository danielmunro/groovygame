package com.groovygame.util

import java.awt.Rectangle

class Hitbox implements Hittable {
    private Rectangle hitbox

    Hitbox(Coords coords) {
        hitbox = new Rectangle(coords.getX(), coords.getY(), Constants.TILE_SIZE, Constants.TILE_SIZE)
    }

    Rectangle getHitBox() {
        hitbox
    }
}
