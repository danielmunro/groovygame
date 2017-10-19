package com.groovygame.matrix

import com.groovygame.util.Coords

class Matrix {
    def matrix = [:]

    Matrix(int width, int height) {
        buildMatrix(width, height)
    }

    void setValueAtCoords(Coords coords, def value) {
        matrix[coords.getX()][coords.getY()] = value
    }

    def getValueAtCoords(Coords coords) {
        matrix[coords.getX()][coords.getY()]
    }

    private void buildMatrix(int width, int height) {
        for (def y = 0; y < height; y++) {
            matrix[y] = [:]
            for (def x = 0; x < width; x++) {
                matrix[y][x] = null
            }
        }
    }

    int getWidth() {
        matrix.size()
    }

    int getHeight() {
        matrix[0].size()
    }

    def getMatrix() {
        matrix
    }
}
