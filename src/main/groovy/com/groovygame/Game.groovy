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

    private void update() {
        board.repaint(
                setAndReturnProjectiles(
                        decayProjectiles(
                                updateProjectiles(
                                        updatePlayer(
                                                projectiles)))))
    }

    private Projectile[] updatePlayer(Projectile[] projectiles) {
        player.move()
        if (player.isAttackKeyPressed()) {
            player.decrementCooldown()
            if (player.canAttack()) {
                player.resetCooldown()
                return projectiles + player.getNewProjectile()
            }
        }

        projectiles
    }

    private Projectile[] setAndReturnProjectiles(Projectile[] projectiles) {
        this.projectiles = projectiles
    }

    private static Projectile[] updateProjectiles(Projectile[] projectiles) {
        projectiles.collect{
            it.update()
        }
    }

    private static Projectile[] decayProjectiles(Projectile[] projectiles) {
        projectiles.findAll{
            it.getDecay() > 0
        }
    }

    private static long getCurrentMilliseconds() {
        new Date().getTime()
    }
}
