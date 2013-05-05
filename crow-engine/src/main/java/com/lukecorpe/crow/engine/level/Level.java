package com.lukecorpe.crow.engine.level;

import java.util.ArrayList;
import java.util.HashMap;

import org.jbox2d.dynamics.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.lukecorpe.crow.engine.Component;
import com.lukecorpe.crow.engine.Game;
import com.lukecorpe.crow.engine.Options;
import com.lukecorpe.crow.engine.interfaces.*;
import com.lukecorpe.crow.engine.objects.GameObject;

public class Level extends Component{
	
	private Image renderTarget;
    private Graphics renderTargetGraph;
	
	private int width;
	private int height;
	
	Background bg;
	
	HashMap<String, String> tileReference = new HashMap<String, String>();
	
	ArrayList<GameObject> objects = new  ArrayList<GameObject>();
	
	//private Player player;
	
	private int tileHeight;
	private int tileWidth;
	
    /*public Player getPlayer() {
    	/*if(player==null){
    		player = new Player(getGame());
    		objects.add(player);
    	}
		return player;
    	return null;
	}*/
	
    public World getWorld(){
    	//return world;
    	return null;
    }
    //Jbox2d
    World world;
    
	public Level(Game game){
		super(game);
		/*bg = new Background(getGame());
		
		//Jbox2d
		this.world = new World(Options.Instance.Gravity(), false);
		
		
		/*getPlayer().setTexture("src/main/resources/Images/SpriteSheet.png", 75, 96);
		getPlayer().addAnimation("Idle", new Vector2f(2, 2), new Vector2f(2, 2), 2, 1, true);
		getPlayer().addAnimation("Walk", new Vector2f(0, 0), new Vector2f(5, 1), 5, 15, true);
		getPlayer().addAnimation("Jump", new Vector2f(1, 2), new Vector2f(1, 1), 2, 8, true);
		*/
		//getGame().getCam().setTarget(getCurrentLevel().getPlayer());
		
	}

	public void finalise() {
		/*try{
	        try {
				renderTarget = new Image(getWidth(), getHeight());
				renderTargetGraph = renderTarget.getGraphics();
			
			} catch (IndexOutOfBoundsException e){
				renderTarget = new Image(10,10);
				renderTargetGraph = renderTarget.getGraphics();
			}
        }catch (SlickException e){
        	e.printStackTrace();
        }
		for(GameObject obj : objects){
			obj.finalisePositions();
		}*/
	}
	public void update(int delta) {
		/*
		bg.update(delta);
		world.step(1f, 8, 3);
		for (GameObject obj : objects) {
			obj.update(delta);
		}*/
	}
	public void draw(){
		/*renderTargetGraph.setBackground(new Color(5, 34, 69));
    	renderTargetGraph.clear();
    	
		
		bg.draw(renderTargetGraph);
		for (GameObject obj : objects) {
			obj.draw(renderTargetGraph);
		}

    	renderTargetGraph.flush();*/
	}
	public void addTileReference(String identifier, String path){
		//tileReference.put(identifier, path);
	}
	/*public String getTilePath(String key){
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

	public ArrayList<GameObject> getObjects() {
		//return objects;
		return null;
	}

	public Image getRenderTarget() {
		//return renderTarget;
		return null;
	}

	public void setTileSizes(int maxTileHeight, int maxTileWidth) {
		this.tileHeight = maxTileHeight;
		this.tileWidth = maxTileWidth;
	}	
}
