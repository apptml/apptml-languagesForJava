package io.github.apptml.languages.plugin;

import io.github.apptml.base.AppTMLPlugin;
import io.github.apptml.base.iface.StyleLanguage;
import io.github.apptml.languages.python.PythonPlatform;
import io.github.apptml.urlscripting.ScriptLanguage;
import io.github.coalangsoft.lib.data.ImmutablePair;
import io.github.coalangsoft.lib.data.Pair;

public class Python<UI> implements AppTMLPlugin<UI> {
	
	@SuppressWarnings("unchecked")
	@Override
	public Pair<String, ScriptLanguage>[] getScriptLanguages(ClassLoader cl) {
		return new Pair[]{
			new ImmutablePair<>("py", new PythonPlatform(cl))
		};
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pair<String, StyleLanguage<UI>>[] getStyleLanguages() {
		return new Pair[]{};
	}

}
