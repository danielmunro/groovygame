package com.groovygame

import com.groovygame.map.Map
import com.groovygame.mobile.player.Player
import com.groovygame.mobile.Projectile
import com.groovygame.ui.Board
import com.groovygame.util.Time

class Game {
    private static final LOOP_WAIT_IN_MILLISECONDS = 3
    private boolean running = true
    private Player player
    private Board board
    private Map map
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>()

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
        board.repaint(projectiles)
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
            it.getDecay() > 0 && !map.intersectsBlocking(it.getHitBox())
        }
    }
}
