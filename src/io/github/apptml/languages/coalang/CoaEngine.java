package io.github.apptml.languages.coalang;

import java.net.MalformedURLException;

import ccl.csy.CCL;
import ccl.jrt.J;
import ccl.rt.Expression;
import ccl.rt.store.Scope;
import ccl.rt.use.MnemoRunner;
import ccl.rt.vm.CclVm;
import cpa.subos.io.IO;
import io.github.apptml.urlscripting.LanguageEngine;
import io.github.coalangsoft.ifw.use.InterfaceWorld;

public class CoaEngine implements LanguageEngine {

	private Scope scope;
	private CclVm vm;
	
	{
		vm = new CclVm();
		scope = new Scope(vm, CCL.classFinder = InterfaceWorld.DEFAULT_CLASS_FINDER);
	}

	@Override
	public Object get(String obj) {
		return scope.load(obj).getValue();
	}

	@Override
	public Object evalUrl(String script) {
		MnemoRunner r = new MnemoRunner(new UrlFileAccessor(script));
		try {
			r.creation(IO.url(script));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		return r.execute(vm, scope).getValue();
	}

	@Override
	public Object invoke(String func, Object... args) {
		try {
			return scope.load(func).invoke(J.pack(vm, args)).getValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void put(String name, Object value) {
		scope.reserve(name).setValue(Expression.make(vm, value));
	}

}
