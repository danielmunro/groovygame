package com.groovygame.ui

import com.groovygame.map.Map
import com.groovygame.mobile.player.Player
import com.groovygame.mobile.Projectile

import javax.swing.JPanel
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class Board extends JPanel implements ActionListener {
    private Map map
    private Player player
    private Projectile[] projectiles

    @Override
    void paintComponent(Graphics g) {
        super.paintComponent(g)

        drawImage(g)
    }

    @Override
    void actionPerformed(ActionEvent e) {
        repaint()
    }

    void repaint(Projectile[] projectiles) {
        this.projectiles = projectiles
        super.repaint()
    }

    private void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g
        map.draw(g2d, this)
        player.draw(g2d, this)
        projectiles.each{
            g2d.drawImage(it.getImage(), it.getCoords().getX(), it.getCoords().getY(), this)
        }
    }
}
