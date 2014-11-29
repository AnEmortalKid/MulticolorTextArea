/*******************************************************************************
 * Copyright 2014 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.scenes2d.ui.textarea.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes2d.ui.textarea.MulticolorTextArea;

/**
 * The {@link HelloWorldTester} just creates a {@link MulticolorTextArea} with
 * the {@link HelloWorldTextPainter} as a painter
 */
public class HelloWorldTester extends ApplicationAdapter {
	private static final String JSON_PATH = "skin/default/uiskin.json";
	private static final String ATLAS_PATH = "skin/default/uiskin.atlas";

	private SpriteBatch batch;
	private Stage stage;

	@Override
	public void create() {
		MulticolorTextArea ta = new MulticolorTextArea(
				"Hello World look at this!", getSkin(),
				new HelloWorldTextPainter(Color.WHITE));
		ta.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		stage.addActor(ta);
	}

	private Skin getSkin() {
		Skin aSkin = new Skin();
		aSkin.addRegions(new TextureAtlas(Gdx.files.local(ATLAS_PATH)));
		aSkin.load(Gdx.files.local(JSON_PATH));
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
