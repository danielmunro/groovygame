package com.groovygame

import com.groovygame.game.Loop
import com.groovygame.game.Service
import com.groovygame.map.Map
import com.groovygame.map.provider.DemoMapProvider
import com.groovygame.map.provider.MapProvider
import com.groovygame.mob.player.DemoPlayerProvider
import com.groovygame.mob.player.Player
import com.groovygame.mob.player.PlayerProvider
import com.groovygame.ui.Board
import com.groovygame.ui.Frame
import com.groovygame.ui.Sprite

import javax.imageio.ImageIO
import java.awt.EventQueue

class Main {
    static void main(String[] args) {
        def map = getMapProvider().getMap()
        def service = new Service(map: map)
        def player = getPlayerProvider(service, map).getPlayer()
        def board = new Board(player: player, map: map, service: service)
        initFrame(board, player)
        def loop = new Loop()
        loop.addObserver(service)
        loop.addObserver(board)
        loop.addObserver(player)
        loop.loop()
    }

    private static void initFrame(Board board, Player player) {
        def frame = new Frame()
        frame.add(board)
        frame.addKeyListener(player)

        EventQueue.invokeLater({
            frame.setVisible(true)
        })
    }

    private static PlayerProvider getPlayerProvider(Service service, Map map) {
        new DemoPlayerProvider(service: service, map: map)
    }

    private static MapProvider getMapProvider() {
        new DemoMapProvider()
    }
}
