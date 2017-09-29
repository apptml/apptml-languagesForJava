package io.github.apptml.languages.coalang;

import java.net.MalformedURLException;
import java.util.Arrays;

import cpa.subos.io.IO;
import cpa.subos.io.IOBase;
import io.github.coalangsoft.lib.data.Func;

public class UrlFileAccessor implements Func<String, Func<Void, IOBase<?>>> {

	private String prefix;

	public UrlFileAccessor(String script) {
		String[] split = script.split("/");
		String[] path = new String[split.length - 1];
		for(int i = 0; i < path.length; i++){
			path[i] = split[i];
		}
		prefix = String.join("/", path) + "/";
	}

	@Override
	public Func<Void, IOBase<?>> call(String p) {
		System.out.println(p);
		
		return new Func<Void, IOBase<?>>() {
			@Override
			public IOBase<?> call(Void v) {
				try {
					IOBase<?> b = IO.url(prefix + p);
					System.out.println(b);
					return b;
				} catch (MalformedURLException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

}
