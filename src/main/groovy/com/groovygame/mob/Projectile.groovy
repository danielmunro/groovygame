package com.groovygame.mob

import com.groovygame.animation.Animation
import com.groovygame.util.Coords
import com.groovygame.util.Hittable

import java.awt.Rectangle
import java.awt.image.BufferedImage

class Projectile implements Cloneable, Hittable {
    private Direction direction
    private BufferedImage image
    private int damage
    private int speed
    private int decay
    private Coords coords
    private List<BufferedImage> explosionImages

    void update(Direction direction, Coords coords) {
        this.direction = direction
        this.coords = coords
    }

    boolean hasDecayed() {
        decay <= 0
    }

    BufferedImage getImage() {
        image
    }

    Coords getCoords() {
        coords
    }

    void update() {
        decay--
        coords = calculateNewCoordsFromVelocity()
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

    Animation getExplosionAnimation() {
        new Animation(coords, explosionImages)
    }
}
