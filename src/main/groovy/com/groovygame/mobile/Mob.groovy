package com.groovygame.mobile

import com.groovygame.Constants
import com.groovygame.util.Direction
import com.groovygame.util.Coords

import java.awt.Rectangle
import java.awt.image.BufferedImage

class Mob {
    protected Coords coords
    protected BufferedImage image
    protected Direction direction = Direction.NONE
    protected Disposition disposition = Disposition.STANDING
    protected Projectile projectile
    protected int cooldown
    protected int currentCooldown

    BufferedImage getImage() {
        image
    }

    Coords getCoords() {
        coords
    }

    boolean canAttack() {
        currentCooldown < 1
    }

    void decrementCooldown() {
        currentCooldown--
    }

    void resetCooldown() {
        currentCooldown = cooldown
    }

    Projectile getNewProjectile() {
        new Projectile(
                direction: direction,
                speed: projectile.getSpeed(),
                image: projectile.getImage(),
                damage: projectile.getDamage(),
                decay: projectile.getDecay(),
                coords: coords
        )
    }

    Rectangle getHitBox() {
        new Rectangle(coords.getX() * Constants.TILE_SIZE, coords.getY() * Constants.TILE_SIZE, image.width, image.height)
    }
}
