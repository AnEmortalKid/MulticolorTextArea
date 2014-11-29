package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes2d.ui.syntax.SyntaxTextArea;
import com.badlogic.gdx.scenes2d.ui.syntax.SyntaxTextAreaPainterImpl;
import com.badlogic.gdx.scenes2d.ui.textarea.DefaultTextAreaLinePainter;
import com.badlogic.gdx.scenes2d.ui.textarea.MulticolorTextArea;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Stage stage;

	@Override
	public void create() {
		SyntaxTextArea ta = new SyntaxTextArea(
				"public static void main(String[] args)", getSkin());
		ta.setTextPainter(new SyntaxTextAreaPainterImpl(Color.WHITE));
		ta.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		stage.addActor(ta);
	}

	private Skin getSkin() {
		Skin aSkin = new Skin();
		aSkin.addRegions(new TextureAtlas(Gdx.files
				.local("skin/default/uiskin.atlas")));
		aSkin.load(Gdx.files.local("skin/default/uiskin.json"));
		return aSkin;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		stage.draw();
		batch.end();
	}
}
