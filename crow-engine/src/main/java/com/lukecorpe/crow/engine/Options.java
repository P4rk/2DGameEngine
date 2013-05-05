package com.lukecorpe.crow.engine;

import org.jbox2d.common.Vec2;

public enum Options {
	Instance; 

	private Vec2 _gravity = new Vec2(0, 0.1f);
	private float _wind = 0.0f;
	private boolean _fullScreen=true;
	private boolean _pasued=false;
	private boolean _debug=true;
	
	public Vec2 Gravity(){
		return _gravity;
	}
	public void setGravity(Vec2 g){
		_gravity = g;
	}
	public void setWind(float _wind) {
		this._wind = _wind;
	}
	public float Wind() {
		return _wind;
	}
	public boolean isFullScreen() {
		return _fullScreen;
	}
	public void setFullScreen(boolean _fullScreen) {
		this._fullScreen = _fullScreen;
	}
	public void pause() {
		setPasued(true);
	}
	public void resume() {
		setPasued(false);
	}
	public void flipPause(){
		setPasued(!_pasued);
	}
	public boolean isPasued() {
		return _pasued;
	}
	public void setPasued(boolean _pasued) {
		this._pasued = _pasued;
	}

	public boolean isDebug() {
		return _debug;
	}
	public void setDebug(boolean _debug) {
		this._debug = _debug;
	}
}
