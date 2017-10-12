package com.groovygame.map

import java.awt.image.BufferedImage

class Layer {
    private int[][] data
    private BufferedImage[] tiles

    int[][] getData() {
        data
    }

    BufferedImage getTileFromIndex(int i) {
        tiles[i]
    }
}
