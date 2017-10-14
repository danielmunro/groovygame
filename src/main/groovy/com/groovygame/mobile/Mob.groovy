package com.groovygame.mobile

import com.groovygame.util.Coords

import java.awt.image.BufferedImage

class Mob {
    protected Coords coords
    protected BufferedImage image
    protected Direction direction = Direction.NONE
    protected Disposition disposition
    protected Projectile projectile
    protected int cooldown
    protected int currentCooldown

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
                coords: coords.clone()
        )
    }
}
