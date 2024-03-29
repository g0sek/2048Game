package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontHandler {
    public final BitmapFont font;

    public FontHandler() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Candara_Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 36;
        parameter.color = Color.valueOf("776e65");
        font = generator.generateFont(parameter);
        generator.dispose();
    }
}
