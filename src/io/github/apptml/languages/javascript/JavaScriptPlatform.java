package io.github.apptml.languages.javascript;

import java.net.MalformedURLException;
import java.net.URL;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import io.github.apptml.languages.ScriptEnginePlatform;
import io.github.apptml.urlscripting.LanguageEngine;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import jdk.nashorn.api.scripting.URLReader;

public class JavaScriptPlatform extends ScriptEnginePlatform {
	
	public JavaScriptPlatform() {
		super(ClassLoader.getSystemClassLoader(), "nashorn");
	}

	protected LanguageEngine wrap(ScriptEngine engine) {
		return new LanguageEngine() {
			@Override
			public Object get(String obj) {
				return engine.get(obj);
			}

			@Override
			public Object evalUrl(String url) {
				try {
					return engine.eval(new URLReader(new URL(url)));
				} catch (MalformedURLException | ScriptException e) {
					throw new RuntimeException(e);
				}
			}

			@Override
			public Object invoke(String func, Object... args) {
				try {
					return ((ScriptObjectMirror) engine.eval(func)).call(new Object(), (Object[]) args);
				} catch (ScriptException e) {
					throw new RuntimeException(e);
				}
			}

			@Override
			public void put(String name, Object value) {
				engine.put(name, value);
			}
		};
	}

}
