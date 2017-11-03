package com.groovygame.game

import com.groovygame.map.Map
import com.groovygame.mob.Projectile
import com.groovygame.animation.Animation
import com.groovygame.util.UpdateTimer

import javax.swing.JPanel
import java.awt.Graphics2D
import java.awt.Rectangle

class Service implements Observer {
    private Map map
    private List<Projectile> projectiles = new ArrayList<Projectile>()
    private List<Animation> explosions = new ArrayList<Animation>()
    private UpdateTimer explosionAnimationTimer = new UpdateTimer(24)
    private UpdateTimer projectileAnimationTimer = new UpdateTimer(5)

    void draw(Graphics2D g2d, JPanel panel) {
        projectiles.each{
            g2d.drawImage(it.getImage(), it.getCoords().getX(), it.getCoords().getY(), panel)
        }
        explosions.each{
            g2d.drawImage(it.getAnimationFrameImage(), it.getCoords().getX(), it.getCoords().getY(), panel)
        }
    }

    @Override
    void update(Observable o, Object arg) {
        def deltaInMilliseconds = (int) arg
        addMilliseconds(deltaInMilliseconds)
    }

    void addMilliseconds(int milliseconds) {
        updateExplosionAnimationTimer(milliseconds)
        updateProjectileAnimationTimer(milliseconds)
    }

    void addProjectile(Projectile projectile) {
        projectiles << projectile
    }

    List<Projectile> getProjectiles() {
        projectiles
    }

    def isMapBlocking(Rectangle rectangle) {
        map.intersectsBlocking(rectangle)
    }

    private void updateProjectileAnimationTimer(int deltaInMilliseconds) {
        projectileAnimationTimer.poll(deltaInMilliseconds, { projectiles = updateProjectiles() })
    }

    private void updateExplosionAnimationTimer(int deltaInMilliseconds) {
        explosionAnimationTimer.poll(deltaInMilliseconds, { explosions = updateExplosions() })
    }

    private updateProjectiles() {
        projectiles.findAll{
            it.update()
            if (!isProjectileReadyToExplode(it)) {
                return true
            }
            checkMobHit(it)
            explosions << it.getExplosionAnimation()
            return false
        }
    }

    private List<Animation> updateExplosions() {
         explosions.findAll{
            it.proceedAnimationFrame()
            !it.hasCompleted()
        }
    }

    private void checkMobHit(Projectile projectile) {
        def mob = map.findIntersectingMob(projectile.getHitBox())
        if (mob) {
            map.removeMob(mob)
        }
    }

    private boolean isProjectileReadyToExplode(Projectile projectile) {
        projectile.getDecay() <= 0 || map.intersectsBlocking(projectile.getHitBox())
    }
}
