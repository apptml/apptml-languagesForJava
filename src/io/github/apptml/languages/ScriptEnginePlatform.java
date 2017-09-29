package io.github.apptml.languages;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import io.github.apptml.urlscripting.LanguageEngine;
import io.github.apptml.urlscripting.ScriptLanguage;

public abstract class ScriptEnginePlatform implements ScriptLanguage{

	private String name;
	private ClassLoader classLoader;

	public ScriptEnginePlatform(ClassLoader cl, String name){
		this.classLoader = cl;
		this.name = name;
	}
	
	@Override
	public LanguageEngine newEngine() {
		ScriptEngine e = new ScriptEngineManager(classLoader).getEngineByName(name);
		return wrap(e);
	}

	protected abstract LanguageEngine wrap(ScriptEngine e);

}
