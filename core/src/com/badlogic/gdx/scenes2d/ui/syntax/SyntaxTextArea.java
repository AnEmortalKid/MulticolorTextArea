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

package com.badlogic.gdx.scenes2d.ui.syntax;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes2d.ui.textarea.MulticolorTextArea;
import com.badlogic.gdx.scenes2d.ui.textarea.TextAreaLinePainter;

public class SyntaxTextArea extends MulticolorTextArea {

	private SyntaxTextAreaPainter sta = null;

	public SyntaxTextArea(String text, Skin skin) {
		super(text, skin);
	}

	public SyntaxTextArea(String text, Skin skin, String styleName) {
		super(text, skin, styleName);
	}

	public SyntaxTextArea(String text, TextFieldStyle style) {
		super(text, style);
	}

	public SyntaxTextArea(String text, Skin skin, TextAreaLinePainter painter) {
		super(text, skin, painter);
	}

	@Override
	public void setTextPainter(TextAreaLinePainter textPainter) {
		if (!(textPainter instanceof SyntaxTextAreaPainter))
			throw new IllegalArgumentException(
					"TextAreaPainter must be of type: "
							+ SyntaxTextAreaPainter.class.getName() + ".");
		super.setTextPainter(textPainter);
		sta = (SyntaxTextAreaPainter) textPainter;
	}

	@Override
	protected void buildColoredLines() {
		sta.notifyPainting(true);
		super.buildColoredLines();
		sta.notifyPainting(false);
	}
}
