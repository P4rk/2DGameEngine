package com.lukecorpe.crow.engine;

public enum Options {
	Instance; 

	private boolean _fullScreen=true;
	private boolean _paused=false;
	private boolean _debug=true;
	private boolean _postProcessor=false;
	
	public boolean isPostProcessorOn() {
        return _postProcessor;
    }
    public void setPostProcessorOn(boolean _postProcessor) {
        this._postProcessor = _postProcessor;
    }
    public boolean isFullScreen() {
		return _fullScreen;
	}
	public void setFullScreen(boolean _fullScreen) {
		this._fullScreen = _fullScreen;
	}
	public void pause() {
		setPaused(true);
	}
	public void resume() {
		setPaused(false);
	}
	public void flipPause(){
		setPaused(!_paused);
	}
	
	//left these 'pasued' in to not break compatibility
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