package com.lukecorpe.crow.engine;

public enum Options {
	Instance; 

	private boolean _fullScreen=true;
	private boolean _paused=false;
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
		setPasued(!_paused);
	}
	
	//left these 'pasued' in to not break compatibility
	public boolean isPasued() {
		return isPaused();
	}
	public void setPasued(boolean _pasued) {
		setPaused(_pasued);
	}
	public boolean isPaused(){
		return _paused;
	}
	public void setPaused(boolean _paused){
		this._paused = _paused;
	}

	public boolean isDebug() {
		return _debug;
	}
	public void setDebug(boolean _debug) {
		this._debug = _debug;
	}
	
	
	
	
}