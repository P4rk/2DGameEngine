package com.lukecorpe.crow.engine.level;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.lukecorpe.crow.engine.Component;
import com.lukecorpe.crow.engine.Game;
import com.lukecorpe.crow.engine.interfaces.Drawable;

public class BackgroundTile extends Component implements Drawable{
	private int _background1WidthRepeats=1;
    private int _background1HeightRepeats=1;

    private int currentLevel = -1;

    public boolean RepeatX ;
    public boolean RepeatY ;
	private Image texture;
	public Image getTexture() {
		return texture;
	}

    public BackgroundTile(Game game)
    {
		super(game);
        RepeatX = true;
        RepeatY = true;
    }

    public void update(int delta)
    {
        if (currentLevel != getGame().getCurrentLevelNum())
        {
            workoutRepeats();
        }
        currentLevel = getGame().getCurrentLevelNum();
        workoutRepeats();
        /*for (int i = 0; i < _background1WidthRepeats; i++)
        {
        	this.getPosition().setX(i);
        	for (int j = 0; j < _background1HeightRepeats; j++)
            {
        		this.getPosition().setY(j);
            }
        }*/
    }
    public void workoutRepeats()
    {
        if ((getTexture().getWidth() < getGame().getCurrentLevel().getWidth()) && RepeatX)
        {
            _background1WidthRepeats = (int)Math.ceil((double)getGame().getCurrentLevel().getWidth() / (double)getTexture().getWidth());
        }
        if ((getTexture().getHeight() < getGame().getCurrentLevel().getHeight()) && RepeatY)
        {
            _background1HeightRepeats = (int)Math.ceil((double)getGame().getCurrentLevel().getHeight() / (double)getTexture().getHeight());
        }
    }
    public void draw(Graphics renderTargetGraph){
    	for (int i = 0; i < _background1WidthRepeats; i++)
        {
        	for (int j = 0; j < _background1HeightRepeats; j++)
            {
        		if(getGame().getCam().inFrame(getTexture(), new Vector2f((getTexture().getWidth() * i) , getTexture().getHeight() * j ))){
        			renderTargetGraph.drawImage(getTexture(),getTexture().getWidth() * i, getTexture().getHeight() * j );
        		}
            }
        }
    }
   /* public void draw(Graphics renderTargetGraph){
    	int maxRepeatsX=0;
    	int maxRepeatsY=0;
        for (int i = 0; i < _background1WidthRepeats; i++)
        {
        	 if (getGame().getCam().inFrame(this)){
        		 //System.out.println("Drawing Width piece : "+i);// + this.getPosition().getX() +" < " + getGame().cam.right());
        	 }
            for (int j = 0; j < _background1HeightRepeats; j++)
            {
            	//getPosition().setX(i);
            	//getPosition().setY(j);
                /*if (getGame().getCam().inFrame(this)) //new Vector2f(getTexture().getWidth() * i, getTexture().getHeight() * j) 
                		/*new Vector2f(
                				(getTexture().getWidth() * i + getTexture().getWidth() ),
                				(getTexture().getHeight() * j + getTexture().getHeight())
                				)
                		)
                	)*/
                /*{
                	renderTargetGraph.drawImage(getTexture(),getTexture().getWidth() * i, getTexture().getHeight() * j );
                	maxRepeatsX = Math.max(maxRepeatsX, i);
                	maxRepeatsY = Math.max(maxRepeatsY, j);
                    //Game.SpriteBatch.Draw(getTexture(), new Rectangle((getTexture().Width * i), (getTexture().Height * j), getTexture().Width, getTexture().Height), Color.White);
                }*/
          //  }
        //}
        //System.out.println(maxRepeatsX);//Drawing Width piece : "+i +"  | Should be : "+ _background1WidthRepeats);
        //System.out.println(maxRepeatsY);//"Drawing Height piece : "+j +"  | Should be : "+ _background1HeightRepeats);
    //}

	public void setTexture(String texturePath) {
		try {
			texture = new Image(texturePath);
			
		} catch (SlickException e) {
			System.out.println("Could not set image, see stack trace.");
			e.printStackTrace();
		}
	}
}
