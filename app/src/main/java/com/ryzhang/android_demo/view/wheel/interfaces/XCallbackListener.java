package com.ryzhang.android_demo.view.wheel.interfaces;


public abstract class XCallbackListener {

	protected abstract void callback(Object... obj);

	public void call(Object... obj) {
			callback(obj);
	}

}
