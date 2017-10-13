package com.groovygame

import com.groovygame.map.Map
import com.groovygame.map.provider.DemoMapProvider
import com.groovygame.map.provider.MapProvider
import com.groovygame.mobile.player.DemoPlayerProvider
import com.groovygame.mobile.player.Player
import com.groovygame.mobile.player.PlayerProvider
import com.groovygame.ui.Board
import com.groovygame.ui.Frame

import java.awt.EventQueue

class Main {
    static void main(String[] args) {
        def map = getMapProvider().getMap()
        def player = getPlayerProvider(map).getPlayer()
        def board = new Board(player: player, map: map)
        initFrame(board, player)

        new Game(player: player, board: board).loop()
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
        new DemoPlayerProvider(map)
    }

    private static MapProvider getMapProvider() {
        new DemoMapProvider()
    }
}
