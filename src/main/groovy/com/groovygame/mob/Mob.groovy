package com.groovygame.mob

import com.groovygame.game.Service
import com.groovygame.util.Constants
import com.groovygame.util.Coords
import com.groovygame.util.Direction
import com.groovygame.util.UpdateTimer

import java.awt.Rectangle
import java.awt.image.BufferedImage

class Mob implements Observer {
    protected Coords coords
    protected BufferedImage image
    protected Direction direction = Direction.NONE
    protected Disposition disposition = Disposition.STANDING
    protected Projectile projectile
    protected Service service
    protected UpdateTimer moveUpdateTimer = new UpdateTimer(5)
    protected UpdateTimer attackUpdateTimer = new UpdateTimer(1000)
    private Patrol patrol

    void setService(Service service) {
        this.service = service
    }

    @Override
    void update(Observable o, Object arg) {
        def deltaInMilliseconds = (int) arg
        moveUpdateTimer.poll(deltaInMilliseconds, { move() })
        if (isAttacking()) {
            attackUpdateTimer.poll(deltaInMilliseconds, { attack() })
        }
    }

    void move() {
        if (patrol) {
            patrol.proceed(this)
        }
    }

    void moveLeft() {
        applyMove(new Coords(coords.getX() - 1, coords.getY()), Direction.LEFT)
    }

    void moveRight() {
        applyMove(new Coords(coords.getX() + 1, coords.getY()), Direction.RIGHT)
    }

    void moveDown() {
        applyMove(new Coords(coords.getX(), coords.getY() + 1), Direction.DOWN)
    }

    void moveUp() {
        applyMove(new Coords(coords.getX(), coords.getY() - 1), Direction.UP)
    }

    private void applyMove(Coords coords, Direction direction) {
        if (!service.isMapBlocking(new Rectangle(coords.getX(), coords.getY(), Constants.TILE_SIZE, Constants.TILE_SIZE))) {
            this.coords = coords
        }
        this.direction = direction
    }

    void attack() {
        if (isAttacking()) {
            service.addProjectile(getNewProjectile())
        }
    }

    def getImage() {
        image
    }

    def getCoords() {
        coords
    }

    def canAttack() {
        disposition == Disposition.ATTACKING
    }

    def getNewProjectile() {
        projectile.update(direction, coords)
        (Projectile) projectile.clone()
    }

    def getHitBox() {
        new Rectangle(coords.getX(), coords.getY(), Constants.TILE_SIZE, Constants.TILE_SIZE)
    }

    def isAttacking() {
        disposition == Disposition.ATTACKING
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
