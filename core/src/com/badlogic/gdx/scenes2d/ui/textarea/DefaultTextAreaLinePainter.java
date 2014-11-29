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

import com.badlogic.gdx.graphics.Color;

/**
 * The {@link DefaultTextAreaLinePainter} is a basic implementation of
 * {@link TextAreaLinePainter} which sets the color of every text to a single
 * color
 */
public class DefaultTextAreaLinePainter implements TextAreaLinePainter {

	/**
	 * The color for every piece of text
	 */
	protected Color defaultColor;

	/**
	 * Constructs a {@link DefaultTextAreaLinePainter} with the given default
	 * color
	 * 
	 * @param defaultColor
	 *            the color to paint every piece of text with
	 */
	public DefaultTextAreaLinePainter(Color defaultColor) {
		this.defaultColor = defaultColor;
	}

	/**
	 * Returns the default {@link Color} for this
	 * {@link DefaultTextAreaLinePainter}
	 * 
	 * @return the default {@link Color} for this
	 *         {@link DefaultTextAreaLinePainter}
	 */
	public Color getDefaultColor() {
		return defaultColor;
	}

	@Override
	public List<ColoredTextElement> getTextElementsForLine(CharSequence line) {
		List<ColoredTextElement> coloredLines = new ArrayList<ColoredTextElement>();
		coloredLines.add(new ColoredTextElement(line, defaultColor));
		return coloredLines;
	}

}
