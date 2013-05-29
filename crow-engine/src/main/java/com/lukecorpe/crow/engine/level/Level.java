package com.lukecorpe.crow.engine.level;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.lukecorpe.crow.engine.Component;
import com.lukecorpe.crow.engine.Game;
import com.lukecorpe.crow.engine.interfaces.Drawable;
import com.lukecorpe.crow.engine.interfaces.Updatable;

public class Level extends Component{
	
	private Image renderTarget;
    private Graphics renderTargetGraph;
	
	private int width;
	private int height;
	
	Background bg;
	
	World world;
	
	boolean doSleep = true;
	
	private List<Drawable> drawList;
	
	float timeStep;
    int velocityIterations;
    int positionIterations;
    
    Vec2 intiGravity = new Vec2(0, 98f);
    
	public Level(Game game){
		super(game);
		bg = new Background(getGame());
		
		//Jbox2d
		this.world = new World(intiGravity, false);
		
		timeStep = 1.0f / 60.f;
	    velocityIterations = 6;
	    positionIterations = 2;
		
	    drawList = new ArrayList<Drawable>();
	    
		/*getPlayer().setTexture("src/main/resources/Images/SpriteSheet.png", 75, 96);
		getPlayer().addAnimation("Idle", new Vector2f(2, 2), new Vector2f(2, 2), 2, 1, true);
		getPlayer().addAnimation("Walk", new Vector2f(0, 0), new Vector2f(5, 1), 5, 15, true);
		getPlayer().addAnimation("Jump", new Vector2f(1, 2), new Vector2f(1, 1), 2, 8, true);
		*/
		//getGame().getCam().setTarget(getCurrentLevel().getPlayer());
		
	}

	public void finalise() {
		try{
	        try {
				renderTarget = new Image(getWidth(), getHeight());
				renderTargetGraph = renderTarget.getGraphics();
			
			} catch (IndexOutOfBoundsException e){
				renderTarget = new Image(10,10);
				renderTargetGraph = renderTarget.getGraphics();
			}
        }catch (SlickException e){
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }
		createBounds();
		/*
		for(GameObject obj : objects){
			obj.finalisePositions();
		}*/
	}

	private void createBounds() {
		for(int i=0; i<4; i++){
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyType.STATIC;
			switch(i){
				case 0:
					//Top
					bodyDef.position.set(new Vec2(0,0));
					break;
				case 1:
					//Right
					bodyDef.position.set(new Vec2(width,0));
					break;
				case 2:
					//Bottom
					bodyDef.position.set(new Vec2(0,height));
					break;
				case 3:
					//Left
					bodyDef.position.set(new Vec2(0,0));
					break;
			}
			Body body = this.getWorld().createBody(bodyDef);
			PolygonShape shape = new PolygonShape();
			switch(i){
				case 0:
					//Top
					shape.setAsBox(width, 0);
					break;
				case 1:
					//Right
					shape.setAsBox(0, height);
					break;
				case 2:
					//Bottom
					shape.setAsBox(width, 0);
					break;
				case 3:
					//Left
					shape.setAsBox(0, height);
					break;
			}
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = shape;
			body.createFixture(fixtureDef);
		}
	}
	public void update(int delta) {
		
		bg.update(delta);
		world.step(timeStep, velocityIterations, positionIterations);
		for (Updatable obj : drawList) {
			obj.update(delta);
		}
	}
	public void draw(){
		renderTargetGraph.setBackground(new Color(5, 34, 69));
    	renderTargetGraph.clear();
    	
		bg.draw(renderTargetGraph);
		for (Drawable obj : drawList) {
			obj.draw(renderTargetGraph);
		}

    	renderTargetGraph.flush();
	}
	/*public void addTileReference(String identifier, String path){
		//tileReference.put(identifier, path);
	}
	public String getTilePath(String key){
		//return tileReference.get(key);
	}*/
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight(){
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

    public World getWorld(){
    	return world;
    }

	public Image getRenderTarget() {
		return renderTarget;
	}

	public List<Drawable> getDrawList() {
		return drawList;
	}

	public Vec2 getGravity() {
		return getWorld().getGravity();
	}

	public void setGravity(Vec2 gravity) {
		getWorld().setGravity(gravity);
	}
}
