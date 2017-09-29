package io.github.apptml.languages.java;

import io.github.apptml.urlscripting.LanguageEngine;
import io.github.apptml.urlscripting.ScriptLanguage;

public class ClassPlatform implements ScriptLanguage {

	@Override
	public LanguageEngine newEngine() {
		return new ClassEngine();
	}

}
