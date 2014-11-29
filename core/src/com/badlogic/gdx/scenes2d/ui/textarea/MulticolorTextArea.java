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
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
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

	/**
	 * A painter which decides what color the text pieces for a particular line
	 * should be
	 */
	protected TextAreaLinePainter textPainter;

	/**
	 * A reference to the text we last had in order to avoid unnecessary
	 * calculations
	 */
	private String lastTextSeen;

	/**
	 * Constructs an {@link MulticolorTextArea}
	 * 
	 * @param text
	 *            the initial text for this {@link MulticolorTextArea}
	 * @param skin
	 *            the {@link Skin} for this {@link MulticolorTextArea}
	 */
	public MulticolorTextArea(String text, Skin skin) {
		super(text, skin);
	}

	/**
	 * Constructs an {@link MulticolorTextArea}
	 * 
	 * @param text
	 *            the initial text for this {@link MulticolorTextArea}
	 * @param skin
	 *            the {@link Skin} for this {@link MulticolorTextArea}
	 * @param styleName
	 *            the name of the style associated with an existing
	 *            {@link TextFieldStyle}
	 */
	public MulticolorTextArea(String text, Skin skin, String styleName) {
		super(text, skin, styleName);
	}

	/**
	 * Constructs an {@link MulticolorTextArea}
	 * 
	 * @param text
	 *            the initial text for this {@link MulticolorTextArea}
	 * @param style
	 *            the {@link TextFieldStyle} to use for this
	 *            {@link MulticolorTextArea}
	 */
	public MulticolorTextArea(String text, TextFieldStyle style) {
		super(text, style);
	}

	/**
	 * Constructs an {@link MulticolorTextArea}
	 * 
	 * @param text
	 *            the initial text for this {@link MulticolorTextArea}
	 * @param skin
	 *            the {@link Skin} for this {@link MulticolorTextArea}
	 * @param painter
	 *            the {@link TextAreaLinePainter} to use when deciding the color
	 *            of the text for each line
	 */
	public MulticolorTextArea(String text, Skin skin,
			TextAreaLinePainter painter) {
		super(text, skin);
		setTextPainter(painter);
	}

	/**
	 * Sets the {@link TextAreaLinePainter} to use for this
	 * {@link MulticolorTextArea}
	 * 
	 * @param textPainter
	 *            the {@link TextAreaLinePainter} to use when painting the text
	 *            lines
	 */
	public void setTextPainter(TextAreaLinePainter textPainter) {
		if (textPainter == null)
			throw new IllegalArgumentException("TextPainter cannot be null.");
		this.textPainter = textPainter;
	}

	@Override
	protected void calculateOffsets() {
		super.calculateOffsets();

		// prevent unnecessary processing if nothing has changed
		if (!this.text.equals(lastTextSeen)) {
			buildTextLines();
			this.lastTextSeen = text;
		}
	}

	@Override
	protected void drawText(Batch batch, BitmapFont font, float x, float y) {
		float offsetY = 0;
		// where to draw the next piece of text
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
	protected void buildTextLines() {
		coloredTextLines.clear();
		for (int i = firstLineShowing * 2; i < (firstLineShowing + getLinesShowing()) * 2
				&& i < linesBreak.size; i += 2) {
			CharSequence lineText = text.subSequence(linesBreak.items[i],
					linesBreak.items[i + 1]);
			coloredTextLines.add(textPainter.getTextElementsForLine(lineText));
		}
	}
}
