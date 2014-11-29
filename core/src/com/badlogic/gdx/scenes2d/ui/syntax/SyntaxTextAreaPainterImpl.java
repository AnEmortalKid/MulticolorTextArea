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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes2d.ui.textarea.DefaultTextAreaLinePainter;
import com.badlogic.gdx.scenes2d.ui.textarea.TextAreaLinePainter;

public class SyntaxTextAreaPainterImpl extends DefaultTextAreaLinePainter implements
		SyntaxTextAreaPainter {

	private boolean inDockBlock = false;

	private boolean sawEndBlock = false;

	private boolean hasString = false;

	static Set<String> keywords = new HashSet<String>();

	static {
		keywords.addAll(Arrays.asList("public", "static", "void", "for", "do",
				"if", "false", "boolean", "int", "implements", "extends",
				"protected", "private", "super", "new"));
	}

	public SyntaxTextAreaPainterImpl(Color defaultColor) {
		super(defaultColor);
	}

	@Override
	public List<ColoredTextElement> getTextElementsForLine(CharSequence line) {
		List<ColoredTextElement> elements = new ArrayList<TextAreaLinePainter.ColoredTextElement>();

		String lineStr = line.toString();
		if (lineStr.startsWith("/**"))
			inDockBlock = true;
		if (lineStr.endsWith("*/"))
			sawEndBlock = true;
		if (lineStr.contains("\""))
			hasString = true;

		if (inDockBlock) {
			if (lineStr.contains("@")) {
				String[] words = lineStr.split(" ");
				for (String word : words) {
					if (word.startsWith("@")) {
						elements.add(new ColoredTextElement(word + " ",
								Color.CYAN));
					} else {
						elements.add(new ColoredTextElement(word + " ",
								Color.BLUE));
					}
				}

			} else {
				elements.add(new ColoredTextElement(lineStr, Color.BLUE));
			}
			if (sawEndBlock) {
				inDockBlock = false;
				sawEndBlock = false;
			}
		} else if (hasString) {
			StringBuilder bob = new StringBuilder();
			boolean beginStr = false;
			for (int i = 0; i < lineStr.length(); i++) {
				char currChar = lineStr.charAt(i);

				if (currChar == '\"') {
					if (!beginStr) {
						beginStr = true;
						// add the previous non string colored stuff
						elements.add(new ColoredTextElement(bob.toString(),
								defaultColor));
						bob = new StringBuilder();
						bob.append(currChar);
					} else {
						beginStr = false;
						bob.append(currChar);
						elements.add(new ColoredTextElement(bob.toString(),
								Color.YELLOW));
						bob = new StringBuilder();
					}
				} else
					bob.append(currChar);
			}
			elements.add(new ColoredTextElement(bob.toString(), defaultColor));
			hasString = false;
		} else {
			String[] words = lineStr.split(" ");
			StringBuilder strBuilder = new StringBuilder();
			for (String word : words) {
				if (keywords.contains(word)) {
					elements.add(new ColoredTextElement(strBuilder.toString(),
							defaultColor));
					elements.add(new ColoredTextElement(word + " ", Color.RED));
					strBuilder = new StringBuilder();
				} else {
					strBuilder.append(word + " ");
				}
			}
			// add the last part
			elements.add(new ColoredTextElement(strBuilder.toString(),
					defaultColor));
		}
		return elements;
	}

	@Override
	public void notifyPainting(boolean isPainting) {
		if (!isPainting) {
			inDockBlock = false;
			sawEndBlock = false;
			hasString = false;
		}
	}

}
