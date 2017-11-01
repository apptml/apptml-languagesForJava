package io.github.apptml.languages.python;

import java.net.MalformedURLException;
import java.net.URL;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.python.core.PyFunction;

import io.github.apptml.urlscripting.LanguageEngine;
import jdk.nashorn.api.scripting.URLReader;

public class PythonEngine implements LanguageEngine {

	private ScriptEngine e;

	PythonEngine(ScriptEngine e){
		this.e = e;
	}
	
	@Override
	public void put(String name, Object value) {
		e.put(name, value);
	}
	
	@Override
	public Object invoke(String func, Object... args) {
		return ((PyFunction) e.get(func))._jcall(args);
	}
	
	@Override
	public Object get(String obj) {
		return e.get(obj);
	}
	
	@Override
	public Object evalUrl(String script) {
		try {
			return e.eval(new URLReader(new URL(script)));
		} catch (MalformedURLException | ScriptException e) {
			throw new RuntimeException(e);
		}
	}

}
