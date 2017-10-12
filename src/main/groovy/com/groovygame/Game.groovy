package com.groovygame

import com.groovygame.player.Player
import com.groovygame.player.Projectile
import com.groovygame.ui.Board

class Game {
    private static final LOOP_WAIT_IN_MILLISECONDS = 3
    private boolean running = true
    private Player player
    private Board board
    private Projectile[] projectiles

    void loop() {
        def lastUpdateMilliseconds = getCurrentMilliseconds()
        while (running) {
            if (isReadyForUpdate(lastUpdateMilliseconds, getCurrentMilliseconds())) {
                update()
                lastUpdateMilliseconds = getCurrentMilliseconds()
            }
        }
    }

    private static boolean isReadyForUpdate(long lastUpdate, long currentTime) {
        return currentTime > lastUpdate + LOOP_WAIT_IN_MILLISECONDS
    }

    private static long getCurrentMilliseconds() {
        new Date().getTime()
    }

    private void update() {
        player.move()
        if (player.isAttacking()) {
            if (player.canAttack()) {
                player.resetCooldown()
                projectiles = projectiles + player.getNewProjectile()
            } else {
                player.decrementCooldown()
            }
        }
        projectiles = projectiles.collect{
            it.update()
        }
        projectiles = projectiles.findAll{
            it.getDecay() > 0
        }
        board.repaint(projectiles)
    }
}
