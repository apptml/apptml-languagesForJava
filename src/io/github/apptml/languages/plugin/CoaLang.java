package io.github.apptml.languages.plugin;

import io.github.apptml.base.AppTMLPlugin;
import io.github.apptml.base.iface.StyleLanguage;
import io.github.apptml.languages.coalang.CoaLangPlatform;
import io.github.apptml.urlscripting.ScriptLanguage;
import io.github.coalangsoft.lib.data.ImmutablePair;
import io.github.coalangsoft.lib.data.Pair;

public class CoaLang<UI> implements AppTMLPlugin<UI>{

	@SuppressWarnings("unchecked")
	@Override
	public Pair<String, ScriptLanguage>[] getScriptLanguages(ClassLoader cl) {
		CoaLangPlatform coaPlatform = new CoaLangPlatform();
		return new Pair[]{
			new ImmutablePair<>("cl0", coaPlatform),
			new ImmutablePair<>("cc0", coaPlatform)
		};
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pair<String, StyleLanguage<UI>>[] getStyleLanguages() {
		return new Pair[]{};
	}

}