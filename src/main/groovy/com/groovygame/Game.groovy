package com.groovygame

import com.groovygame.map.Map
import com.groovygame.mobile.player.Player
import com.groovygame.mobile.Projectile
import com.groovygame.ui.Board
import com.groovygame.ui.Sprite
import com.groovygame.util.Coords
import com.groovygame.util.Explosion
import com.groovygame.util.Time

import java.awt.image.BufferedImage

class Game {
    private static final LOOP_WAIT_IN_MILLISECONDS = 3
    private static final UPDATES_PER_ANIMATION_LOOP = 8
    private int updatesSinceLastAnimationLoop = 0
    private boolean running = true
    private Player player
    private Board board
    private Map map
    private Sprite sprites
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>()
    private ArrayList<Explosion> explosions = new ArrayList<Explosion>()

    void loop() {
        def lastUpdateMilliseconds = 0
        while (running) {
            if (isReadyForUpdate(lastUpdateMilliseconds, Time.getCurrentMilliseconds())) {
                update()
                lastUpdateMilliseconds = Time.getCurrentMilliseconds()
            }
        }
    }

    private static boolean isReadyForUpdate(long lastUpdate, long currentTime) {
        return currentTime > lastUpdate + LOOP_WAIT_IN_MILLISECONDS
    }

    private void update() {
        updatePlayer()
        updateProjectiles()
        board.repaint(projectiles, explosions)
        if (updatesSinceLastAnimationLoop > UPDATES_PER_ANIMATION_LOOP) {
            updateExplosions()
            updatesSinceLastAnimationLoop = 0
        } else {
            updatesSinceLastAnimationLoop++
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

    private void updateProjectiles() {
        projectiles = projectiles.collect{
            it.update()
        }.findAll{
            if (!hasExpired(it)) {
                return true
            }
            explosions << new Explosion(
                    it.coords,
                    (ArrayList<BufferedImage>) [
                            sprites.getImageAtCoords(Coords.at(19, 10)),
                            sprites.getImageAtCoords(Coords.at(18, 10)),
                            sprites.getImageAtCoords(Coords.at(17, 10))
                    ]
            )

            return false
        }
    }

    private void updateExplosions() {
        explosions.each{
            it.proceedAnimationFrame()
        }
        explosions = explosions.findAll{
            !it.hasCompleted()
        }
    }

    private boolean hasExpired(Projectile projectile) {
        projectile.getDecay() <= 0 || map.intersectsBlocking(projectile.getHitBox())
    }
}
