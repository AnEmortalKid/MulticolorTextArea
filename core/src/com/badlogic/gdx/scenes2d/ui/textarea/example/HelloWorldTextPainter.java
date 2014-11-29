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

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes2d.ui.textarea.DefaultTextAreaLinePainter;
import com.badlogic.gdx.scenes2d.ui.textarea.MulticolorTextArea;
import com.badlogic.gdx.scenes2d.ui.textarea.TextAreaLinePainter;

/**
 * The {@link HelloWorldTextPainter} is a sample implementation of
 * {@link TextAreaLinePainter} in order to demonstrate how we can use the
 * {@link MulticolorTextArea} to paint text in different colors. This class
 * makes every instance of Hello be red, and World be green
 */
public class HelloWorldTextPainter extends DefaultTextAreaLinePainter {

	public HelloWorldTextPainter(Color defaultColor) {
		super(defaultColor);
	}

	@Override
	public List<ColoredTextElement> getTextElementsForLine(CharSequence line) {
		String[] words = line.toString().split(" ");
		List<ColoredTextElement> coloredElements = new ArrayList<TextAreaLinePainter.ColoredTextElement>();
		for (String word : words) {
			Color wordColor = defaultColor;
			if (word.equals("Hello"))
				wordColor = Color.RED;
			if (word.equals("World"))
				wordColor = Color.GREEN;
			coloredElements.add(new ColoredTextElement(word + " ", wordColor));
		}
		return coloredElements;
	}

}
