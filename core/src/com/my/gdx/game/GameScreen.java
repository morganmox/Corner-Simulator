package com.my.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	
	private PikemanGame pikemanGame;
	WorldRenderer worldRenderer;
	World world;


	public GameScreen(PikemanGame pikemanGame) {
		this.pikemanGame = pikemanGame;
		world = new World(pikemanGame);
		worldRenderer = new WorldRenderer(pikemanGame, world);

	}

	public void render(float delta) {
		update(delta);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		SpriteBatch batch = pikemanGame.batch;
		worldRenderer.render(delta, batch);
		
	}
	
	private void update(float delta) {
		world.update(delta);

	}

}