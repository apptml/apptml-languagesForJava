package io.github.apptml.languages.java;

import io.github.apptml.urlscripting.LanguageEngine;
import io.github.apptml.urlscripting.ScriptLanguage;

public class JavaPlatform implements ScriptLanguage {

	@Override
	public LanguageEngine newEngine() {
		return new JavaEngine();
	}

}
