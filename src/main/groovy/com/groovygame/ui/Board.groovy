package com.groovygame.ui

import com.groovygame.game.Service
import com.groovygame.map.Map
import com.groovygame.mob.player.Player

import javax.swing.JPanel
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class Board extends JPanel implements ActionListener, Observer {
    private Map map
    private Player player
    private Service service

    @Override
    void update(Observable o, Object arg) {
        repaint()
    }

    @Override
    void paintComponent(Graphics g) {
        super.paintComponent(g)

        drawImage(g)
    }

    @Override
    void actionPerformed(ActionEvent e) {
        repaint()
    }

    private void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g
        map.draw(g2d, this)
        player.draw(g2d, this)
        service.draw(g2d, this)
    }
}
