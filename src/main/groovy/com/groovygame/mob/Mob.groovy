package com.groovygame.mob

import com.groovygame.game.Service
import com.groovygame.util.Constants
import com.groovygame.util.Coords
import com.groovygame.util.Hittable
import com.groovygame.util.UpdateTimer

import java.awt.Rectangle
import java.awt.image.BufferedImage

class Mob implements Observer, Hittable {
    protected Coords coords
    protected BufferedImage image
    protected Direction direction = Direction.NONE
    protected Disposition disposition = Disposition.STANDING
    protected Projectile projectile
    protected Service service
    protected UpdateTimer moveUpdateTimer = new UpdateTimer(updateIntervalInMilliseconds: 25)
    protected UpdateTimer attackUpdateTimer = new UpdateTimer(updateIntervalInMilliseconds: 1000)
    private Patrol patrol

    void setService(Service service) {
        this.service = service
    }

    @Override
    void update(Observable o, Object arg) {
        def deltaInMilliseconds = (int) arg
        moveUpdateTimer.poll(deltaInMilliseconds, { move() })
        attackUpdateTimer.poll(deltaInMilliseconds, { pollAttack() })
    }

    void move() {
        if (patrol) {
            patrol.proceed(this)
        }
    }

    void pollAttack() {
        if (isAttacking()) {
            attack()
        }
    }

    void attack() {
        service.addProjectile(getNewProjectile())
    }

    void resetDispositionToStanding() {
        disposition = Disposition.STANDING
    }

    void setDispositionToAttacking() {
        disposition = Disposition.ATTACKING
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

    Rectangle getHitBox() {
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

    void moveTo(Coords coords, Direction direction) {
        if (service.isMapBlocking(new Hitbox(coords))) {
            return
        }
        this.coords = coords
        this.direction = direction
    }
}
