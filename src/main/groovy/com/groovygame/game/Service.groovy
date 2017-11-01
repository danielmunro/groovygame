package com.groovygame.game

import com.groovygame.map.Map
import com.groovygame.mob.Projectile
import com.groovygame.mob.player.Player
import com.groovygame.animation.Animation
import com.groovygame.util.UpdateTimer

import javax.swing.JPanel
import java.awt.Graphics2D

class Service implements Observer {
    private Player player
    private Map map
    private List<Projectile> projectiles = new ArrayList<Projectile>()
    private List<Animation> explosions = new ArrayList<Animation>()
    private UpdateTimer explosionAnimationTimer = new UpdateTimer(24)
    private UpdateTimer playerUpdateTimer = new UpdateTimer(5)

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
        updateExplosionAnimationTimer(deltaInMilliseconds)
        updatePlayerTimer(deltaInMilliseconds)
        gameLoopUpdate()
    }

    private void gameLoopUpdate() {
        projectiles = updateProjectiles()
    }

    private void updateExplosionAnimationTimer(int deltaInMilliseconds) {
        explosionAnimationTimer.poll(deltaInMilliseconds, {
                explosions = updateExplosions()
        })
    }

    private void updatePlayerTimer(int deltaInMilliseconds) {
        playerUpdateTimer.poll(deltaInMilliseconds, {
                updatePlayer()
        })
    }

    private void updatePlayer() {
        player.move()
        if (player.isAttackKeyPressed()) {
            player.decrementCooldown()
            if (player.canAttack()) {
                player.resetCooldown()
                projectiles << player.getNewProjectile()
            }
        }
    }

    private updateProjectiles() {
        projectiles.each{
            it.update()
        }
        projectiles.findAll{
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
