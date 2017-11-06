package com.groovygame.game

import com.groovygame.map.Map
import com.groovygame.mob.Projectile
import com.groovygame.animation.Animation
import com.groovygame.util.Hittable
import com.groovygame.util.UpdateTimer

import javax.swing.JPanel
import java.awt.Graphics2D

class Service implements Observer {
    private Map map
    private List<Projectile> projectiles = new ArrayList<Projectile>()
    private List<Animation> explosions = new ArrayList<Animation>()
    private List<UpdateTimer> updateTimers = [
            new UpdateTimer(24, { updateExplosions() }),
            new UpdateTimer(5, { updateProjectiles() })
    ]

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
        updateTimers.each{ it.poll(milliseconds) }
    }

    void addProjectile(Projectile projectile) {
        projectiles << projectile
    }

    int getProjectileCount() {
        projectiles.size()
    }

    def isMapBlocking(Hittable hittable) {
        map.intersectsBlocking(hittable)
    }

    private void updateProjectiles() {
        projectiles = projectiles.findAll{
            it.update()
            if (!isProjectileReadyToExplode(it)) {
                return true
            }
            checkMobHit(it)
            explosions << it.getExplosionAnimation()
            return false
        }
    }

    private void updateExplosions() {
         explosions = explosions.findAll{
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
        projectile.hasDecayed() || map.intersectsBlocking(projectile)
    }
}
