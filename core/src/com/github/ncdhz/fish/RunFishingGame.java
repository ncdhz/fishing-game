package com.github.ncdhz.fish;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.ncdhz.fish.game.Game;

public class RunFishingGame extends ApplicationAdapter {
	private Stage stage;
	private Viewport viewport;
	public static float RATE = 1F;
	public static int DY_HEIGHT;
	public static int DY_WIDTH;
	public static final int TEXT_FIELD_WIDTH = 120;
	public static final int TEXT_FIELD_HEIGHT = 30;
	public static String DY_TITLE;
	private static Game game;

	static {
		DY_HEIGHT = (int) (542.0F * RATE);
		DY_WIDTH = (int) (1024.0F * RATE);
		DY_TITLE = "捕鱼达人";
	}

	@Override
	public void create() {
		if (this.viewport == null && this.stage == null) {
			this.viewport = new ScalingViewport(Scaling.stretch, (float) DY_WIDTH, (float) DY_HEIGHT);
			this.stage = new Stage(this.viewport);
			Gdx.input.setInputProcessor(this.stage);
		}

		game = new Game(this.stage);
		game.create();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
		Gdx.gl.glClear(16384);
		game.update();
	}

	@Override
	public void dispose() {
		game.dispose();
	}
}