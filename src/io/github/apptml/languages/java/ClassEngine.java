package io.github.apptml.languages.java;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import io.github.apptml.urlscripting.LanguageEngine;
import io.github.coalangsoft.lib.data.ImmutablePair;
import io.github.coalangsoft.lib.data.Pair;
import io.github.coalangsoft.reflect.Clss;

public class ClassEngine implements LanguageEngine {

	private Object obj;
	private Class<?> clss;
	
	private ArrayList<Pair<String, Object>> putList = new ArrayList<>();

	@Override
	public Object evalUrl(String script) {
		String[] split = script.substring(0,script.length() - 6).split("/");
		String[] path = new String[split.length - 1];
		for(int i = 0; i < path.length; i++){
			path[i] = split[i];
		}
		
		try {
			clss = new URLClassLoader(new URL[]{new URL(String.join("/", path) + "/")})
				.loadClass(split[split.length - 1]);
			obj = clss.newInstance();
			
			for(int i = 0; i < putList.size(); i++){
				Pair<String,Object> p = putList.get(i);
				put0(p.getA(), p.getB());
			}
			
			try{
				invoke("run");
			}catch(RuntimeException e){}
			
			return obj;
		} catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object get(String obj) {
		try{
			clss.getField(obj).get(obj);
		}catch(Exception e){};
		throw new RuntimeException(obj);
	}

	@Override
	public Object invoke(String func, Object... args) {
		return new Clss(clss).getMethods(obj, func).call(args);
	}

	@Override
	public void put(String name, Object value) {
		putList.add(new ImmutablePair<String, Object>(name, value));
		put0(name,value);
	}
	
	private void put0(String name, Object value){
		if(obj != null){
			try {
				clss.getField(name).set(obj, value);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {}
		}
	}

}
