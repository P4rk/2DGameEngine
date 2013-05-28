package com.lukecorpe.crow.engine;

public enum Options {
	Instance; 

	private boolean _fullScreen=true;
	private boolean _pasued=false;
	private boolean _debug=false;
	
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
//removing my test comment(?!)