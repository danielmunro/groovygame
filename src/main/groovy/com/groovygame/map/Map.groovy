package com.groovygame.map

import com.groovygame.util.Constants
import com.groovygame.mob.Mob
import com.groovygame.ui.Board
import com.groovygame.util.Hittable

import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.image.BufferedImage

class Map {
    private Layer background
    private Layer blocking
    private Layer foreground
    private List<Mob> mobs = new ArrayList<Mob>()

    private static void drawLayer(Layer layer, Graphics2D graphics2D, Board board) {
        layer.getData().eachWithIndex{ def row, int y ->
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

    boolean intersectsBlocking(Hittable hittable) {
        findIntersectingMob(hittable.getHitBox()) || intersectsMapTile(hittable.getHitBox())
    }

    Mob findIntersectingMob(Rectangle rectangle) {
        mobs.find{
            it.getHitBox().intersects(rectangle)
        }
    }

    void removeMob(Mob mob) {
        mobs.remove(mob)
    }

    List<Mob> getMobs() {
        (ArrayList<Mob>) mobs.clone()
    }

    private boolean intersectsMapTile(Rectangle rectangle) {
        def data = blocking.getData()
        for (int y = 0; y < data.size(); y++) {
            for (int x = 0; x < data[y].size(); x++) {
                if (data[y][x] && rectangle.intersects(new Rectangle(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE))) {
                    return true
                }
            }
        }

        false
    }

    void draw(Graphics2D graphics2D, Board board) {
        drawLayer(background, graphics2D, board)
        drawLayer(blocking, graphics2D, board)
        drawMobs(graphics2D, board)
    }

    private void drawMobs(Graphics2D graphics2D, Board board) {
        mobs.each{
            graphics2D.drawImage(it.getImage(), it.getCoords().getX(), it.getCoords().getY(), board)
        }
    }
}
