package com.groovygame.ui

import com.groovygame.map.Map
import com.groovygame.mobile.player.Player
import com.groovygame.mobile.Projectile
import com.groovygame.util.Explosion

import javax.swing.JPanel
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class Board extends JPanel implements ActionListener {
    private Map map
    private Player player
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>()
    private ArrayList<Explosion> explosions = new ArrayList<Explosion>()

    @Override
    void paintComponent(Graphics g) {
        super.paintComponent(g)

        drawImage(g)
    }

    @Override
    void actionPerformed(ActionEvent e) {
        repaint()
    }

    void repaint(ArrayList<Projectile> projectiles, ArrayList<Explosion> explosions) {
        this.projectiles = projectiles
        this.explosions = explosions
        super.repaint()
    }

    private void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g
        map.draw(g2d, this)
        player.draw(g2d, this)
        projectiles.each{
            g2d.drawImage(it.getImage(), it.getCoords().getX(), it.getCoords().getY(), this)
        }
        explosions.each{
            g2d.drawImage(it.getAnimationFrameImage(), it.getCoords().getX(), it.getCoords().getY(), this)
        }
    }
}
