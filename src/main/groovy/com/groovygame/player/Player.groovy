package com.groovygame.player

import com.groovygame.util.Constants
import com.groovygame.mob.Mob
import com.groovygame.ui.Board
import com.groovygame.util.Coords
import com.groovygame.mob.Direction
import com.groovygame.util.UpdateTimer

import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class Player implements Observer, KeyListener {
    private keysPressed = []
    private Mob mob
    private UpdateTimer moveUpdateTimer = new UpdateTimer(updateIntervalInMilliseconds: 5)
    private UpdateTimer attackUpdateTimer = new UpdateTimer(updateIntervalInMilliseconds: 1000)

    @Override
    void update(Observable o, Object arg) {
        def deltaInMilliseconds = (int) arg
        moveUpdateTimer.poll(deltaInMilliseconds, { move() })
        attackUpdateTimer.poll(deltaInMilliseconds, { mob.pollAttack() })
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

    private void evaluateKeyReleased(int keyCode) {
        switch (keyCode) {
            case Constants.KEY_SPACE:
                mob.resetDispositionToStanding()
                break
        }
    }

    void draw(Graphics2D graphics2D, Board board) {
        graphics2D.drawImage(mob.getImage(), mob.getCoords().getX(), mob.getCoords().getY(), board)
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
            if (keyCode == Constants.KEY_ATTACK) {
                mob.setDispositionToAttacking()
                mob.attack()
            }
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

    Coords getCoords() {
        mob.getCoords()
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

    private void moveLeft() {
        mob.moveTo(new Coords(mob.getCoords().getX() - 1, mob.getCoords().getY()), Direction.LEFT)
    }

    private void moveRight() {
        mob.moveTo(new Coords(mob.getCoords().getX() + 1, mob.getCoords().getY()), Direction.RIGHT)
    }

    private void moveDown() {
        mob.moveTo(new Coords(mob.getCoords().getX(), mob.getCoords().getY() + 1), Direction.DOWN)
    }

    private void moveUp() {
        mob.moveTo(new Coords(mob.getCoords().getX(), mob.getCoords().getY() - 1), Direction.UP)
    }
}
