package com.groovygame.game

import com.groovygame.animation.BlueExplosionAnimation
import com.groovygame.map.Map
import com.groovygame.mob.Projectile
import com.groovygame.mob.player.Player
import com.groovygame.ui.Board
import com.groovygame.ui.Sprite
import com.groovygame.util.Explosion

class Service {
    private Player player
    private Board board
    private Map map
    private Sprite sprites
    private List<Projectile> projectiles = new ArrayList<Projectile>()
    private List<Explosion> explosions = new ArrayList<Explosion>()

    void gameLoopUpdate() {
        updatePlayer()
        projectiles = updateProjectiles()
        board.repaint(projectiles, explosions)
    }

    void animationFrameUpdate() {
        explosions = updateExplosions()
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
        projectiles.collect{
            it.update()
        }.findAll{
            if (!isProjectileReadyToExplode(it)) {
                return true
            }
            checkMobHit(it)
            explosions << new Explosion(
                    it.coords,
                    new BlueExplosionAnimation().createAnimationFramesFromSprite(sprites)
            )
            return false
        }
    }

    private List<Explosion> updateExplosions() {
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
