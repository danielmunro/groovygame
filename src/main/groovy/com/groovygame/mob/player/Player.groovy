package com.groovygame.mob.player

import com.groovygame.game.Service
import com.groovygame.util.Constants
import com.groovygame.util.Coords
import com.groovygame.util.Direction
import com.groovygame.mob.Disposition
import com.groovygame.mob.Mob
import com.groovygame.ui.Board
import com.groovygame.util.UpdateTimer

import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class Player extends Mob implements KeyListener, Observer {
    private keysPressed = []
    private Service service
    private UpdateTimer moveUpdateTimer = new UpdateTimer(5)
    private UpdateTimer attackUpdateTimer = new UpdateTimer(5)

    @Override
    void update(Observable o, Object arg) {
        def deltaInMilliseconds = (int) arg
        moveUpdateTimer.poll(deltaInMilliseconds, { move() })
        attackUpdateTimer.poll(deltaInMilliseconds, { attack() })
    }

    void move() {
        keysPressed.clone().each {
            switch (it) {
                case Constants.KEY_DOWN:
                    moveDown()
                    break
                case Constants.KEY_UP:
                    moveUp()
                    break
                case Constants.KEY_LEFT:
                    moveLeft()
                    break
                case Constants.KEY_RIGHT:
                    moveRight()
                    break
            }
        }
    }

    void attack() {
        if (isAttackKeyPressed()) {
            decrementCooldown()
            if (canAttack()) {
                resetCooldown()
                service.addProjectile(getNewProjectile())
            }
        }
    }

    private void evaluateKeyReleased(int keyCode) {
        switch (keyCode) {
            case Constants.KEY_SPACE:
                disposition = Disposition.STANDING
                break
        }
    }

    void moveLeft() {
        applyMove(new Coords(coords.getX() - 1, coords.getY()), Direction.LEFT)
    }

    void moveRight() {
        applyMove(new Coords(coords.getX() + 1, coords.getY()), Direction.RIGHT)
    }

    void moveDown() {
        applyMove(new Coords(coords.getX(), coords.getY() + 1), Direction.DOWN)
    }

    void moveUp() {
        applyMove(new Coords(coords.getX(), coords.getY() - 1), Direction.UP)
    }

    private void applyMove(Coords coords, Direction direction) {
        if (!service.isMapBlocking(new Rectangle(coords.getX(), coords.getY(), Constants.TILE_SIZE, Constants.TILE_SIZE))) {
            this.coords = coords
        }
        this.direction = direction
    }

    void draw(Graphics2D graphics2D, Board board) {
        graphics2D.drawImage(image, coords.getX(), coords.getY(), board)
    }

    void keyReleased(int keyCode) {
        if (keysPressed.contains(keyCode)) {
            keysPressed.removeElement(keyCode)
            evaluateKeyReleased(keyCode)
        }
    }

    void keyPressed(int keyCode) {
        if (!keysPressed.contains(keyCode)) {
            keysPressed.add(keyCode)
        }
    }

    boolean isAttackKeyPressed() {
        isKeyPressed(Constants.KEY_SPACE)
    }

    boolean isKeyPressed(int keyCode) {
        keysPressed.any {
            return it == keyCode
        }
    }

    List keysPressed() {
        keysPressed
    }

    @Override
    void keyTyped(KeyEvent e) {
    }

    @Override
    void keyReleased(KeyEvent e) {
        keyReleased(e.keyCode)
    }

    @Override
    void keyPressed(KeyEvent e) {
        keyPressed(e.keyCode)
    }
}
