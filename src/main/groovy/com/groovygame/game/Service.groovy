package com.groovygame.game

import com.groovygame.animation.BlueExplosionAnimation
import com.groovygame.map.Map
import com.groovygame.mob.Projectile
import com.groovygame.mob.player.Player
import com.groovygame.ui.Board
import com.groovygame.ui.Sprite
import com.groovygame.util.Explosion
import com.groovygame.util.UpdateTimer

class Service implements Observer {
    private Player player
    private Board board
    private Map map
    private Sprite sprites
    private List<Projectile> projectiles = new ArrayList<Projectile>()
    private List<Explosion> explosions = new ArrayList<Explosion>()
    private UpdateTimer explosionAnimationTimer = new UpdateTimer(24)
    private UpdateTimer playerUpdateTimer = new UpdateTimer(5)

    @Override
    void update(Observable o, Object arg) {
        def deltaInMilliseconds = (int) arg
        updateExplosionAnimationTimer(deltaInMilliseconds)
        updatePlayerTimer(deltaInMilliseconds)
        gameLoopUpdate()
    }

    private void gameLoopUpdate() {
        projectiles = updateProjectiles()
        board.repaint(projectiles, explosions)
    }

    private void updateExplosionAnimationTimer(int deltaInMilliseconds) {
        explosionAnimationTimer.addMilliseconds(deltaInMilliseconds)
        if (explosionAnimationTimer.isReadyForUpdate()) {
            explosions = updateExplosions()
            explosionAnimationTimer.resetUpdateCounter()
        }
    }

    private void updatePlayerTimer(int deltaInMilliseconds) {
        playerUpdateTimer.addMilliseconds(deltaInMilliseconds)
        if (playerUpdateTimer.isReadyForUpdate()) {
            updatePlayer()
            playerUpdateTimer.resetUpdateCounter()
        }
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
            def animation = new BlueExplosionAnimation()
            animation.createAnimationFramesFromSprite(sprites)
            explosions << new Explosion(it.coords, animation.getAnimationFrames())
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
