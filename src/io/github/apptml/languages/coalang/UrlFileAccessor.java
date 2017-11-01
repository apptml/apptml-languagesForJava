package io.github.apptml.languages.coalang;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import cpa.subos.io.IO;
import cpa.subos.io.IOBase;
import io.github.coalangsoft.lib.data.Func;
import io.github.coalangsoft.lib.io.StreamCopy;

public class UrlFileAccessor implements Func<String, Func<Void, IOBase<?>>> {

	private String prefix;
	private static Map<String, File> map = new HashMap<>();

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
					File prev = map.get(prefix + p);
					if(prev != null){
						return IO.file(prev);
					}
					
					File temp = File.createTempFile(p, ".cl0");
					IOBase<?> b = IO.url(prefix + p);
					StreamCopy.copy(b.reader(), new FileOutputStream(temp));
					map.put(prefix + p, temp);
					return IO.file(temp);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

}
