package com.nikhiljarora.pong.graphics;

import java.util.Arrays;

// Holds a single instance of a sprite which is on a sprite sheet
public class Sprite {


    private final int sprintSize;
    private final int x, y;



    public int[] pixels;
    private final SpriteSheet spriteSheet;

    public Sprite(int sprintSize, int x, int y, SpriteSheet spriteSheet) {
        this.sprintSize = sprintSize;
        pixels = new int[sprintSize*sprintSize];
        this.x = x;
        this.y = y;
        this.spriteSheet = spriteSheet;
        setPixels();
    }

    public int getSprintSize() {
        return sprintSize;
    }

    private void setPixels() {
        for (int i = y*sprintSize; i < y*sprintSize + sprintSize; i++)
            for (int j = x * sprintSize; j < x * sprintSize + sprintSize; j++) {
                int rowOffset = i * spriteSheet.sheetSize;
                int colOffset = j;
                int il = i - y*sprintSize;
                int jl = j - x*sprintSize;
                pixels[il * sprintSize + jl] = spriteSheet.pixels[rowOffset + colOffset];
            }
    }

    public static void main(String[] args) {
        String sheetPath = "/home/nja/dev/gh/pong/res/spritesheets/pong.png";
        SpriteSheet testSpriteSheet = new SpriteSheet(sheetPath);
        Sprite testSprite = new Sprite(16, 1, 0, testSpriteSheet);
    }
}
