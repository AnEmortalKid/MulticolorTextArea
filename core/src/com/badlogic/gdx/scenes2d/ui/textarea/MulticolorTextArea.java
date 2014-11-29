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

package com.badlogic.gdx.scenes2d.ui.textarea;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes2d.ui.textarea.TextAreaLinePainter.ColoredTextElement;

/**
 * A multi-line multi-colored text area based on {@link TextArea}.
 */
public class MulticolorTextArea extends ExtensibleTextArea {

	/**
	 * A List of {@code List<ColoredText>} which represents the lines and the
	 * text elements for each of those lines. Every internal
	 * {@code List<ColoredText>} represents the characters that compose that
	 * line.
	 */
	private List<List<ColoredTextElement>> coloredTextLines = new ArrayList<List<ColoredTextElement>>();

	protected TextAreaLinePainter textPainter;

	private String lastTextSeen;

	public MulticolorTextArea(String text, Skin skin) {
		super(text, skin);
	}

	public MulticolorTextArea(String text, Skin skin, String styleName) {
		super(text, skin, styleName);
	}

	public MulticolorTextArea(String text, TextFieldStyle style) {
		super(text, style);
	}

	public MulticolorTextArea(String text, Skin skin, TextAreaLinePainter painter) {
		super(text, skin);
		setTextPainter(painter);
	}

	public void setTextPainter(TextAreaLinePainter textPainter) {
		if (textPainter == null)
			throw new IllegalArgumentException("TextPainter cannot be null.");
		this.textPainter = textPainter;
	}

	@Override
	protected void calculateOffsets() {
		super.calculateOffsets();
		if (!this.text.equals(lastTextSeen)) {
			buildColoredLines();
			this.lastTextSeen = text;
		}
	}

	@Override
	protected void drawText(Batch batch, BitmapFont font, float x, float y) {
		float offsetY = 0;
		// currStart is where we should draw the text
		float currStart = 0;
		for (List<ColoredTextElement> line : coloredTextLines) {
			for (ColoredTextElement ct : line) {
				font.setColor(ct.getColor());
				font.draw(batch, ct.getText(), x + currStart, y + offsetY);
				currStart += font.getBounds(ct.getText()).width;
			}
			currStart = 0;
			offsetY -= font.getLineHeight();
		}
	}

	/**
	 * Populates the {@code List<List<ColoredText>>} representing each line and
	 * the chunks of text with their respective color.
	 */
	protected void buildColoredLines() {
		coloredTextLines.clear();
		for (int i = firstLineShowing * 2; i < (firstLineShowing + getLinesShowing()) * 2
				&& i < linesBreak.size; i += 2) {
			CharSequence lineText = text.subSequence(linesBreak.items[i],
					linesBreak.items[i + 1]);
			coloredTextLines.add(textPainter.getTextElementsForLine(lineText));
		}
	}
}
