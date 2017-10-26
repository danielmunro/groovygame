package com.groovygame.mobile

import com.groovygame.util.Direction
import com.groovygame.util.Coords
import groovy.transform.Immutable

import java.awt.Rectangle
import java.awt.image.BufferedImage

@Immutable
class Projectile {
    private Direction direction
    private BufferedImage image
    private int damage
    private int speed
    private int decay
    private Coords coords

    int getSpeed() {
        speed
    }

    int getDamage() {
        damage
    }

    int getDecay() {
        decay
    }

    BufferedImage getImage() {
        image
    }

    Coords getCoords() {
        coords
    }

    Projectile update() {
        new Projectile(
                direction: direction,
                image: image,
                damage: damage,
                speed: speed,
                decay: decay - 1,
                coords: calculateNewCoordsFromVelocity()
        )
    }

    Coords calculateNewCoordsFromVelocity() {
        switch (direction) {
            case Direction.DOWN:
                return new Coords(coords.getX(), coords.getY() + speed)
            case Direction.LEFT:
                return new Coords(coords.getX() - speed, coords.getY())
            case Direction.UP:
                return new Coords(coords.getX(), coords.getY() - speed)
            case Direction.RIGHT:
            default:
                return new Coords(coords.getX() + speed, coords.getY())
        }
    }

    Rectangle getHitBox() {
        new Rectangle(coords.getX(), coords.getY(), image.width, image.height)
    }

    boolean equals(Projectile p) {
        return image.hashCode() == p.getImage().hashCode()
    }
}
