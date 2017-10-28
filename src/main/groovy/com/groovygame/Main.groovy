package com.groovygame

import com.groovygame.game.Loop
import com.groovygame.game.Service
import com.groovygame.map.Map
import com.groovygame.map.provider.DemoMapProvider
import com.groovygame.map.provider.MapProvider
import com.groovygame.mobile.player.DemoPlayerProvider
import com.groovygame.mobile.player.Player
import com.groovygame.mobile.player.PlayerProvider
import com.groovygame.ui.Board
import com.groovygame.ui.Frame
import com.groovygame.ui.Sprite

import javax.imageio.ImageIO
import java.awt.EventQueue

class Main {
    static void main(String[] args) {
        def map = getMapProvider().getMap()
        def player = getPlayerProvider(map).getPlayer()
        def board = new Board(player: player, map: map)
        def sprite = new Sprite(image: ImageIO.read(new File("sprites.png")))
        initFrame(board, player)
        def service = new Service(player: player, board: board, map: map, sprites: sprite)

        new Loop(service: service).loop()
    }

    private static void initFrame(Board board, Player player) {
        def frame = new Frame()
        frame.add(board)
        frame.addKeyListener(player)

        EventQueue.invokeLater({
            frame.setVisible(true)
        })
    }

    private static PlayerProvider getPlayerProvider(Map map) {
        new DemoPlayerProvider(map: map)
    }

    private static MapProvider getMapProvider() {
        new DemoMapProvider()
    }
}
