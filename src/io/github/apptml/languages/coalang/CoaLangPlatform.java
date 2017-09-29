package io.github.apptml.languages.coalang;

import io.github.apptml.urlscripting.LanguageEngine;
import io.github.apptml.urlscripting.ScriptLanguage;

public class CoaLangPlatform implements ScriptLanguage {

	@Override
	public LanguageEngine newEngine() {
		return new CoaEngine();
	}

}
