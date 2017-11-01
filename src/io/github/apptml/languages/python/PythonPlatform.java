package io.github.apptml.languages.python;

import javax.script.ScriptEngine;

import io.github.apptml.languages.ScriptEnginePlatform;
import io.github.apptml.urlscripting.LanguageEngine;

public class PythonPlatform extends ScriptEnginePlatform{

	public PythonPlatform(ClassLoader cl) {
		super(cl, "python");
	}

	@Override
	protected LanguageEngine wrap(ScriptEngine e) {
		return new PythonEngine(e);
	}

}
