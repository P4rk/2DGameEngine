package com.lukecorpe.crow.engine;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import com.lukecorpe.crow.engine.math.MathUtils;
import com.lukecorpe.crow.engine.objects.GameObject;

public class Camera extends Component{
	
	private Vector2f _position = new Vector2f(0,0);
    public Vector2f getPosition() {
		return _position;
	}

	public void setPosition(Vector2f _p) {
		_position = new Vector2f(MathUtils.clamp(_p.getX(), 0, getGame().getCurrentLevel().getWidth()-getGame().getContainer().getWidth()), MathUtils.clamp(_p.getY(), 0, getGame().getCurrentLevel().getHeight()-getGame().getContainer().getHeight()));
	}

	public GameObject getTarget() {
		return _target;
	}

	public void setTarget(GameObject _target) {
		this._target = _target;
		HasGameObjectTarget = true;
		LockedTarget = true;
	}
	public void setTargetSoft(GameObject _target) {
		this._target = _target;
		HasGameObjectTarget = true;
		LockedTarget = false;
	}

	public Vector2f getVectorTarget() {
		return _vectorTarget;
	}

	public void setVectorTarget(Vector2f _vectorTarget) {
		this._vectorTarget = _vectorTarget;
	}

	public float getCamSpeed() {
		return _camSpeed;
	}

	public void setCamSpeed(int _camSpeed) {
		this._camSpeed = Math.max(_camSpeed, Math.max(Constants.MAX_FALL_SPEED,Constants.MAX_FALL_SPEED));
	}

	public int getScrollBorder() {
		return _scrollBorder;
	}

	public void setScrollBorder(int _scrollBorder) {
		this._scrollBorder = _scrollBorder;
	}

    private GameObject _target;
    
    private Random rnd;


    private Vector2f _vectorTarget;

    public boolean HasGameObjectTarget = false; 
    public boolean LockedTarget = false;

    private float _camSpeed = 3000f;
    private int _scrollBorder = 150;

	private float _shakeDuration;


	private boolean _shake = true;


	private int _shakeAmount = 1;


	private int _shakeTargetX = 0;


	private int _shakeTargetY = 0;
	
    public Camera(Game game)
    {
    	super(game);
        _position = new Vector2f(0,0);
        setCamSpeed(0);
        
        rnd = new Random();
         
    }
    
    public Boolean inFrame(GameObject obj)
    {
    	if ((obj.getPosition().getY() - obj.getTexture().getHeight()> getPosition().getY() + getGame().getContainer().getHeight()) || (obj.getPosition().getX()- obj.getTexture().getWidth()  > getPosition().getX() + getGame().getContainer().getWidth()))
        {
            return false;
        }
        if ((obj.getPosition().getY()+ obj.getTexture().getHeight() < getPosition().getY()) || (obj.getPosition().getX()+ obj.getTexture().getWidth() < getPosition().getX()))
        {
            return false;
        }
        return true;
    }

	public float right() {
		return getPosition().getX() + getGame().getContainer().getWidth();
	}
	public float left(){
		return getPosition().getX();
	}
	public float bottom() {
		return getPosition().getY() + getGame().getContainer().getHeight();
	}
	public float top(){
		return getPosition().getY();
	}
	
    public Boolean inFrame(Vector2f vec)
    {
    	if ((vec.getY() > getPosition().getY() + getGame().getContainer().getHeight()) || (vec.getX() > getPosition().getX() + getGame().getContainer().getWidth()))
        {
            return false;
        }
        if ((vec.getY() < getPosition().getY()) || (vec.getX() < getPosition().getX()))
        {
            return false;
        }
        return true;
    }
    
    public boolean inFrame(Image texture, Vector2f vec) {
    	if ((vec.getY() - texture.getHeight()> getPosition().getY() + getGame().getContainer().getHeight()) || (vec.getX()- texture.getWidth() > getPosition().getX() + getGame().getContainer().getWidth()))
        {
            return false;
        }
        if ((vec.getY() + texture.getHeight()< getPosition().getY()) || (vec.getX() + texture.getWidth() < getPosition().getX()))
        {
            return false;
        }
        return true;
	}
    public void update(int delta)
    {

        switch(getGame().getGameState()){
        	case splashScreen:
        		//TODO Update menu splash
        		getGame().getCurrentMenu().update(delta);
        		break;
        	case inGame:
        		if(!Options.Instance.isPaused()){
        	    	//setPosition(new Vector2f(this.getPosition().getX()-0.1f, this.getPosition().getY()-0.1f));
        	        if (HasGameObjectTarget)
        	        {
        	            if (LockedTarget)
        	            {
        	                strongGameObjectTrack();
        	            }
        	            else
        	            {
        	                weakGameObjectTrack();
        	            }
        	        }
        	       /* else
        	        {
        	            strongVectorTrack(_vectorTarget);
        	        }*/
        	        
        	        
        	        
        	        getGame().getCurrentLevel().update(delta);
        	        
        	        
        	        doShake(delta);
        		}else{
        		//UpdateInGameMenu
        		}
        		break;
        }
        //TODO
      //  Game.PostProcessor.Update(getGame()Time);
    }
    
    /*Random r = new Random();
    private void randomlySetVectorTaget() {
    	if(_vectorTarget==null) _vectorTarget = Vector2f.Zero;
    	//if(getPosition().equals(_vectorTarget)){
    		_vectorTarget.x = -(r.nextInt(getGame().getCurrentLevel().getWidth()));
    		_vectorTarget.y = -(r.nextInt(getGame().getCurrentLevel().getHeight()));
    	//}
		
    	
    	
	}*/
    
    
    private void doShake(int delta)
    {
        if (_shake)
        {
            /*if ((Position.X == _shakeTargetX))
            {
                
            }
            if ((Position.Y == _shakeTargetY))
            {
                
            }*/
            _shakeTargetX = rnd.nextInt(_shakeAmount);
            _shakeTargetY = rnd.nextInt(_shakeAmount);
            setPosition(new Vector2f(_shakeTargetX + getPosition().getX(), _shakeTargetY + getPosition().getY()));
                //new Vector2(((_shakeTargetX- Position.X  ) * 0.9f), (( _shakeTargetY- Position.Y) * 0.9f));

            _shakeDuration -= ((float)delta)/1000;
            if (_shakeDuration <= 0)
            {
                _shake = false;
            }
        }
    }
    public void Shake(float duration, int amount)
    {
        _shakeDuration = duration;
        _shake = true;
        _shakeAmount = amount;
    }

	private void strongVectorTrack(Vector2f _vectorTarget)
    {
    	if(_vectorTarget!=null){
    		if (getPosition().getX() < _vectorTarget.getX()- (getGame().getContainer().getWidth() / 2))
	        {
	            setPosition(getPosition().add(new Vector2f(MathUtils.clamp((_vectorTarget.getX() - (getGame().getContainer().getWidth() / 2)) - getPosition().getX(), -_camSpeed, _camSpeed), 0)));
	        }
	        if (getPosition().getX() > _vectorTarget.getX()- (getGame().getContainer().getWidth() / 2))
	        {
	        	setPosition(getPosition().sub( new Vector2f(MathUtils.clamp(getPosition().getX() - (_vectorTarget.getX() - (getGame().getContainer().getWidth() / 2)), -_camSpeed, _camSpeed), 0)));
	        }
	        if (getPosition().getY() < _vectorTarget.getY() - (getGame().getContainer().getHeight() / 2)) 
	        {
	        	setPosition(getPosition().add(new Vector2f(0,
	        		MathUtils.clamp(
	                (_vectorTarget.getY() - (getGame().getContainer().getHeight() / 2) - getPosition().getY()), -_camSpeed, _camSpeed))));
	        }
	        if (getPosition().getY() > _vectorTarget.getY() - (getGame().getContainer().getHeight() / 2))
	        {
	        	setPosition(getPosition().sub(new Vector2f(0, MathUtils.clamp(getPosition().getY() - (_vectorTarget.getY() - (getGame().getContainer().getHeight() / 2)), -_camSpeed, _camSpeed))));
	        }
    	}
    }

    private void weakGameObjectTrack()
    {
        weakVectorTrack(_target.getPositionCenter());
    }

    private void weakVectorTrack(Vector2f vec)
    {
        if (vec.getX() - getPosition().getX() < _scrollBorder)
        {
            if (getPosition().getX() != 0)
            {
            	setPosition(getPosition().add(new Vector2f(MathUtils.clamp((_scrollBorder - vec.getX() - getPosition().getX()), -_camSpeed, _camSpeed), 0)));
            }
        }
        if (vec.getX() - getPosition().getX() >  getGame().container.getWidth() - _scrollBorder)
        {
        	setPosition(getPosition().sub( new Vector2f(MathUtils.clamp((getGame().getContainer().getWidth() - _scrollBorder - vec.getX() - getPosition().getX()), -_camSpeed, _camSpeed), 0)));
        }
        if (vec.getY() - getPosition().getY() < _scrollBorder)
        {
            if (getPosition().getY() != 0)
            {
            	setPosition( getPosition().add( new Vector2f(0,MathUtils.clamp((_scrollBorder -getPosition().getY()  -vec.getY()) , -_camSpeed, _camSpeed))));
            }
        }
        if (vec.getY() - getPosition().getY() > getGame().container.getHeight() - _scrollBorder)
        {
        	setPosition(getPosition().sub(new Vector2f(0,MathUtils.clamp((getGame().getContainer().getHeight() - _scrollBorder - getPosition().getY() - vec.getY()), -_camSpeed, _camSpeed))));
        }
    }

    private void strongGameObjectTrack()
    {
        strongVectorTrack(_target.getPositionCenter());
    }
    public void Draw()
    {
    	//TODO
        //Game.PostProcessor.Draw(getPosition());
    	
    	//Render Current Level to Graphic
    	switch(getGame().getGameState()){
    	case splashScreen:
    		getGame().getCurrentMenu().draw();
    		break;
    	case inGame:
        	getGame().getPostProcessor().draw();
    	    //RenderInGameMenu
        	if(Options.Instance.isPaused()){
        		getGame().getCurrentLevel().getRenderTarget().setAlpha(0.5f);
        		//Render Menu Items
            	//TODO
        		getGame().getContainer().getGraphics().drawString("Paused",getGame().getContainer().getWidth()/2,getGame().getContainer().getHeight()/2);
        	}else{
        		getGame().getCurrentLevel().getRenderTarget().setAlpha(1f);
        	}
    		break;
    	}
    	
    	//Render final image to screen
    	renderImageOnScreen();
    	

    	//Draw to screen
    	if (Options.Instance.isDebug()){
	    	getGame().getContainer().getGraphics().drawString("Camera position : "+ getPosition(), 10, 30);

	    	//getGame().getContainer().getGraphics().drawString("Target position : "+ getTarget().getPosition(), 10,50);
	    	getGame().getContainer().getGraphics().drawString("Gravity "+getGame().getCurrentLevel().getGravity(), 10, 90);

	    	if(getTarget()!=null) {
	    		getGame().getContainer().getGraphics().drawString("Target position : "+ getTarget().getPosition(), 10, 50 );
	    	}else {
	    		getGame().getContainer().getGraphics().drawString("Target position : null", 10, 50);
	    	}
	    	
	    	getGame().getContainer().getGraphics().drawString("Level : "+ (getGame().getCurrentLevelNum()+1)+ " | "+getGame().getLevels().size(), 10, getGame().getContainer().getHeight() - 40);
	    	
	    	getGame().getContainer().getGraphics().drawString("GameState : "+ getGame().getGameState(), 10, getGame().getContainer().getHeight() - 20);

    	}
    	getGame().getContainer().setShowFPS(Options.Instance.isDebug());
    	/*getGame().container.getGraphics().drawString("Target X Collide : "+ getGame().cam.getTarget().is_collidingX(), 10, 200);
    	getGame().container.getGraphics().drawString("Target Y Collide : "+ getGame().cam.getTarget().is_collidingY(), 10, 210);*/
    	
    }

	private void renderImageOnScreen() {
		float positionX = getPosition().getX();
		float positionY = getPosition().getY();
		switch(getGame().getGameState()){
			case splashScreen:
				getGame().getCurrentMenu().getRenderTarget().draw(0f,0f);
				break;
			case inGame:
				if(getGame().getCurrentLevel().getRenderTarget().getWidth()<getGame().getContainer().getWidth()){
					positionX = -(getGame().getContainer().getWidth()/2)+getGame().getCurrentLevel().getRenderTarget().getWidth()/2 ;
				}
				
				if(getGame().getCurrentLevel().getRenderTarget().getHeight()<getGame().getContainer().getHeight()){
					positionY = -(getGame().getContainer().getHeight()/2)+getGame().getCurrentLevel().getRenderTarget().getHeight()/2 ;
				}
				
				getGame().getCurrentLevel().getRenderTarget().draw(-(positionX), -(positionY));
				break;
		}
	}

	/*public Graphics getRenderTarget() {
		return renderTargetGraph;
	}*/

	
}
