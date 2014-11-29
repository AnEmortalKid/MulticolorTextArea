MulticolorTextArea - release
============================
Contained in this branch are the following

- src.com.badlogic.gdx.scenes2d.ui.textarea
 - DefaultTextAreaLinePainter -> default implementation of TextAreaLinePainter
 - TextAreaLinePainter -> interface used in conjunction with MulticolorTextArea
 - ExtensibleTextArea -> a copy pasted slightly modified TextArea (from libgdx)
 - MulticolorTextArea -> a text area that can have text in multiple colors.

- com.badlogic.gdx.scenes2d.ui.textarea.example
 - HelloWorldTester (a basic program that just has a MulticolorTextArea)
 - HelloWorldTextPainter (an implementation of TextAreaLinePainter which paints Hello and World different colors).

Usage
====================
To see the test working, do the following:
- Check HelloWorldTester and make sure the atlas/json path's are the correct ones for your libgdx setup
- Using the DesktopLauncher (or whichever way you launch your program), you can have the following main:

<pre>
public static void main(String[] arg) {
	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	new LwjglApplication(new HelloWorldTester(), config);
}
</pre>

This should be enough to run the text area and show "Hello World look at this!" with Hello and World in different colors.

License
===================
Copyright 2014

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.

You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
