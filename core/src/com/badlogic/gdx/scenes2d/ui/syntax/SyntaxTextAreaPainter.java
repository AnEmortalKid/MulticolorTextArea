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

import com.badlogic.gdx.scenes2d.ui.textarea.TextAreaLinePainter;

public interface SyntaxTextAreaPainter extends TextAreaLinePainter {

	/**
	 * Notifies this {@link SyntaxTextAreaPainter} if we are currently using
	 * this {@link SyntaxTextAreaPainter} to paint or not
	 * 
	 * @param isPainting
	 *            whether the caller is currently in the painting cycle or not
	 */
	void notifyPainting(boolean isPainting);

}
