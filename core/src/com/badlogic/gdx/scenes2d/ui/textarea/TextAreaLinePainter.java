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

import java.util.List;

import com.badlogic.gdx.graphics.Color;

/**
 * A {@link TextAreaLinePainter} dictates the color of text elements passed into
 * it, for a single line, and is used in conjunction with
 * {@link MulticolorTextArea} in order to paint different pieces of text with
 * different colors.
 */
public interface TextAreaLinePainter {

	/**
	 * Returns a List of {@link ColoredTextElement}, these
	 * {@code ColoredTextElements} represent the text, with their respective
	 * color, for that particular line
	 * 
	 * @param line
	 *            the characters in the given line
	 * @return a {@code List<ColoredTextElement>} with the text elements for the
	 *         given line
	 */
	List<ColoredTextElement> getTextElementsForLine(CharSequence line);

	/**
	 * A {@link ColoredTextElement} represents a sequence of characters which
	 * should be drawn with a specific color
	 */
	class ColoredTextElement {

		/**
		 * The characters to draw
		 */
		private CharSequence text;

		/**
		 * The color to use when drawing the text
		 */
		private Color color;

		/**
		 * Constructs a {@link ColoredTextElement} with the specified text and
		 * color
		 * 
		 * @param text
		 *            the text that should be drawn for this
		 *            {@code ColoredTextElement}
		 * @param color
		 *            the color that should be added to the text contained in
		 *            this {@code ColoredTextElement}, this value can be
		 *            <code>null</code>
		 */
		public ColoredTextElement(CharSequence text, Color color) {
			this.text = text;
			this.color = color;
		}

		/**
		 * Returns the text that should be drawn for this
		 * {@link ColoredTextElement}
		 * 
		 * @return the text that should be drawn for this
		 *         {@code ColoredTextElement}
		 */
		public CharSequence getText() {
			return text;
		}

		/**
		 * Returns the color that the text contained in this
		 * {@link ColoredTextElement} should be drawn with, or <code>null</code>
		 * if the text should be drawn with the default font color
		 * 
		 * @return the color that the text contained in this
		 *         {@code ColoredTextElement} should be drawn with or
		 *         <code>null</code> if the text should be drawn with the
		 *         default font color
		 */
		public Color getColor() {
			return color;
		}

		@Override
		public String toString() {
			return "[Color=" + color + ", text=" + text + "]";
		}
	}

}