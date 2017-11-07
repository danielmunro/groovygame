package com.groovygame.map

import com.groovygame.util.Constants
import com.groovygame.mob.Mob
import com.groovygame.ui.Board
import com.groovygame.util.Coords
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
        layer.forEachTile {
            drawTile(
                    layer.getTileAtCoords(it),
                    it.getX() * Constants.TILE_SIZE,
                    it.getY() * Constants.TILE_SIZE,
                    graphics2D,
                    board
            )
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
        findIntersectingMob(hittable.getHitBox()) || intersectsMapBlockingData(hittable.getHitBox())
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

    private def intersectsMapBlockingData(Rectangle rectangle) {
        blocking.find{ Coords c, int i ->
            i && rectangle.intersects(
                    new Rectangle(
                            c.getX() * Constants.TILE_SIZE,
                            c.getY() * Constants.TILE_SIZE,
                            Constants.TILE_SIZE,
                            Constants.TILE_SIZE
                    )
            )
        }
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
