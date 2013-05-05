package com.lukecorpe.crow.engine.menu;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.lukecorpe.crow.engine.Component;
import com.lukecorpe.crow.engine.Game;
import com.lukecorpe.crow.engine.interfaces.Drawable;
import com.lukecorpe.crow.engine.objects.GameObject;

public class Menu extends Component{
	
	//private ArrayList<Button> buttons;
	
	private Image renderTarget;
	
	private Graphics renderTargetGraph;
	
	
	
	public Menu(Game game){
		super(game);
		//buttons = new ArrayList<Button>();
		finalise();
	}
	
	/*public void addButton(Button button){
		buttons.add(button);
	}*/
	
	public void finalise() {
		try{
	        try {
				renderTarget = new Image(getGame().getContainer().getWidth(), getGame().getContainer().getHeight());
				renderTargetGraph = renderTarget.getGraphics();
			
			} catch (IndexOutOfBoundsException e){
				renderTarget = new Image(10,10);
				renderTargetGraph = renderTarget.getGraphics();
			}
        }catch (SlickException e){
        	e.printStackTrace();
        }
	}
	
	@Override
	public void update(int delta) {
		if(renderTarget==null){
        	try {
				renderTarget = new Image(getGame().getContainer().getWidth(), getGame().getContainer().getHeight());
			} catch (SlickException e) {
				e.printStackTrace();
			}
        }
        if(renderTargetGraph == null){
        	try {
				renderTargetGraph = renderTarget.getGraphics();
			} catch (SlickException e) {
				e.printStackTrace();
			}
        }
		/*for(Button b:buttons){
			b.update(delta);
		}*/
	}

	public void draw() {
		renderTargetGraph.setBackground(new Color(5, 34, 69));
    	renderTargetGraph.clear();
    	
		/*for(Button b:buttons){
			b.draw(renderTargetGraph);
		}*/

    	renderTargetGraph.flush();
	}

	public Image getRenderTarget() {
		return renderTarget;
	}

	public float getHeight() {
		return renderTarget.getHeight();
	}

	public float getWidth() {
		return renderTarget.getWidth();
	}

}
