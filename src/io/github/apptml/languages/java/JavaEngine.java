package io.github.apptml.languages.java;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import cpa.subos.io.IO;
import cpa.subos.io.file.FileIOBase;
import io.github.apptml.urlscripting.LanguageEngine;
import io.github.coalangsoft.ifw.use.Javac;
import io.github.coalangsoft.ifw.use.JavacResult;

public class JavaEngine implements LanguageEngine {

	private LanguageEngine classEngine = new ClassEngine();
	
	@Override
	public Object evalUrl(String script) {
		String[] split = script.split("/");
		
		try {
			File dir = File.createTempFile("javaengine", "compiling");
			dir.delete();
			dir.mkdir();
			
			FileIOBase javaSource = IO.file(dir).child(split[split.length - 1])
				.downloadFrom(IO.url(script));
			
			JavacResult res = Javac.getInstance().compile(Arrays.asList(new File(javaSource.getPath())));
			if(!res.isSuccess()){
				throw new RuntimeException("Failed to compile java class" + script + ": " + res.getOut());
			}
			System.out.println(res.getOut());
			
			script = javaSource.toUrl();
			script = script.substring(0,script.length() - 4) + "class";
			
			return classEngine.evalUrl(script);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object get(String obj) {
		return classEngine.get(obj);
	}

	@Override
	public Object invoke(String func, Object... args) {
		return classEngine.invoke(func,args);
	}

	@Override
	public void put(String name, Object value) {
		classEngine.put(name, value);
	}

}
