package com.groovygame.mob

import com.groovygame.Constants
import com.groovygame.util.Coords
import com.groovygame.util.Direction

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
    private Patrol patrol

    def getImage() {
        image
    }

    def getCoords() {
        coords
    }

    def canAttack() {
        currentCooldown < 1
    }

    void decrementCooldown() {
        currentCooldown--
    }

    void resetCooldown() {
        currentCooldown = cooldown
    }

    def getNewProjectile() {
        new Projectile(
                direction: direction,
                speed: projectile.getSpeed(),
                image: projectile.getImage(),
                damage: projectile.getDamage(),
                decay: projectile.getDecay(),
                coords: coords
        )
    }

    def getHitBox() {
        new Rectangle(coords.getX() * Constants.TILE_SIZE, coords.getY() * Constants.TILE_SIZE, image.width, image.height)
    }

    void patrol() {
        patrol.proceed(this)
    }

    List<Coords> getPatrolPath() {
        patrol.getPath()
    }

    void setCoords(Coords coords) {
        this.coords = coords
    }
}