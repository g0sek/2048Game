package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class Animations{
    private SpriteBatch batch;
    private Texture texture1, texture2;
    private Sprite sprite;
    private Animation<TextureRegion> animation;
    private float stateTime;
    float alpha;

    public void create(Texture texture) {
        batch = new SpriteBatch();
        // Utwórz animację
        TextureRegion[] textureRegions = {
                new TextureRegion(texture)
        };
        animation = new Animation<>(1f, textureRegions);

        sprite = new Sprite(texture);
        sprite.setPosition(0, 0);
        alpha = 0.0f;
    }

    public void render() {
        stateTime += Gdx.graphics.getDeltaTime();

        // Pobierz bieżącą ramkę animacji
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);

        batch.begin();
        batch.setColor(1, 1, 1, alpha);
        // Narysuj obiekt Sprite z bieżącą ramką animacji
        batch.draw(currentFrame, sprite.getX(), sprite.getY());
        batch.end();
        alpha = Math.min(1.0f, alpha + 0.01f);
    }
}
