package io.github.apptml.languages.plugin;

import io.github.apptml.base.AppTMLPlugin;
import io.github.apptml.base.iface.StyleLanguage;
import io.github.apptml.languages.javascript.JavaScriptPlatform;
import io.github.apptml.urlscripting.ScriptLanguage;
import io.github.coalangsoft.lib.data.ImmutablePair;
import io.github.coalangsoft.lib.data.Pair;

public class JavaScript<UI> implements AppTMLPlugin<UI> {

	@SuppressWarnings("unchecked")
	@Override
	public Pair<String, ScriptLanguage>[] getScriptLanguages(ClassLoader cl) {
		return new Pair[]{
			new ImmutablePair<>("js", new JavaScriptPlatform())
		};
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pair<String, StyleLanguage<UI>>[] getStyleLanguages() {
		return new Pair[]{};
	}

}
