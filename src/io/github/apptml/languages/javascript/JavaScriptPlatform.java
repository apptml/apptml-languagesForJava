package io.github.apptml.languages.javascript;

import javax.script.ScriptEngine;

import io.github.apptml.languages.ScriptEnginePlatform;
import io.github.apptml.urlscripting.LanguageEngine;

public class JavaScriptPlatform extends ScriptEnginePlatform {
	
	public JavaScriptPlatform() {
		super(ClassLoader.getSystemClassLoader(), "nashorn");
	}

	protected LanguageEngine wrap(ScriptEngine engine) {
		return new JavaScriptEngine(engine);
	}

}
