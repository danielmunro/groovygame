package com.groovygame.map

import com.groovygame.Constants
import com.groovygame.ui.Board

import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.image.BufferedImage

class Map {
    private Layer background
    private Layer blocking
    private Layer foreground

    private static void drawLayer(Layer layer, Graphics2D graphics2D, Board board) {
        layer.getData().eachWithIndex{ int[] row, int y ->
            row.eachWithIndex{ int i, int x ->
                if (i > 0) {
                    drawTile(
                            layer.getTileFromIndex(i-1),
                            x * Constants.TILE_SIZE,
                            y * Constants.TILE_SIZE,
                            graphics2D,
                            board
                    )
                }
            }
        }
    }

    private static void drawTile(BufferedImage image, int x, int y, Graphics2D graphics2D, Board board) {
        graphics2D.drawImage(
                image,
                x,
                y,
                Constants.TILE_SIZE,
                Constants.TILE_SIZE,
                board
        )
    }

    boolean intersectsBlocking(Rectangle rectangle) {
        def data = blocking.getData()
        for (int y = 0; y < data.length; y++) {
            for (int x = 0; x < data[y].length; x++) {
                if (data[y][x] && rectangle.intersects(new Rectangle(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE))) {
                    return true
                }
            }
        }
    }

    void draw(Graphics2D graphics2D, Board board) {
        drawLayer(background, graphics2D, board)
        drawLayer(blocking, graphics2D, board)
    }
}
