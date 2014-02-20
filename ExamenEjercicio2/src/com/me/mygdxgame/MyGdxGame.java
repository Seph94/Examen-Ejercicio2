package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class Imagen extends Sprite{
	Imagen(Texture texture)
	{
		super(texture);
		setPosition(-0.5f,-0.5f);
		setSize(1.0f,1.0f);
		
	}
	
	void avanzar()
	{
		setX(getX() + 0.01f);
		if(getX() >0.5){
			setX(-0.5f);
		}
	}
	
}
public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Texture texture_evil;
	private Sprite sprite;
	private Sprite sprite_evil;
	Imagen imagen;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		

		texture_evil= new Texture(Gdx.files.internal("data/enemigo.png"));
		sprite_evil=new Sprite(texture_evil,128,128);
		imagen = new Imagen(texture_evil);
		//sprite_evil.setPosition(-0.5f,-0.2f);
		//sprite_evil.setSize(0.5f, 0.5f);
	
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		imagen.draw(batch);
		batch.end();
		if(Gdx.input.isTouched())
		{
			imagen.avanzar();
		}
	}
		
	


	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
