package com.lukecorpe.crow.engine;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.lukecorpe.crow.engine.level.Level;
import com.lukecorpe.crow.engine.level.LevelLoader;
import com.lukecorpe.crow.engine.menu.Menu;
import com.lukecorpe.crow.engine.objects.GameObject;

public class Game extends BasicGame {
	
	GameContainer container;
	Camera cam;
	private ArrayList<Level> levels;
	private int currentLevelNum = 0;
	private int previousLevelNum =-1;
	
	private ArrayList<Menu> menus;
	private int currentMenuNum = 0;
	private int previousMenuNum =-1;
	
	private PostProcessor postProcessor;
	
	private GameState gameState = GameState.splashScreen;
	
	
	public Input getInput() {
		return getContainer().getInput();
	}

	public GameContainer getContainer() {
		return container;
	}

	public void setContainer(GameContainer container) {
		this.container = container;
	}
	
	public Camera getCam(){
		if(cam==null){
			cam = new Camera(this);
		}
		return cam;
	}

	
	public int getCurrentLevelNum() {
		if(currentLevelNum<0){
			setCurrentLevelNum(0);
		}
		return currentLevelNum;
	}
	public void setCurrentLevelNum(int i){
		previousLevelNum = currentLevelNum;
		currentLevelNum =i;
	}
 	public Level getCurrentLevel(){
		return levels.get(getCurrentLevelNum());
 	}

	public ArrayList<Level> getLevels() {
		return levels;
	}
	
	public int getCurrentMenuNum() {
		if(currentMenuNum<0){
			setCurrentMenuNum(0);
		}
		return currentMenuNum;
	}
	public void setCurrentMenuNum(int i){
		previousMenuNum = currentMenuNum;
		currentMenuNum =i;
	}
 	public Menu getCurrentMenu(){
		return menus.get(getCurrentMenuNum());
 	}

	public ArrayList<Level> getMenus() {
		return levels;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Game(String gameTitle) {
        super(gameTitle);
        menus = new ArrayList<Menu>();
        levels = new ArrayList<Level>();
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {
    	this.container = container;
    	
    	Menu splashMenu = new Menu(this);
    	menus.add(splashMenu);
    	
    	cam = new Camera(this);
    	
    	postProcessor = new PostProcessor(this);
    	postProcessor.init();
    	
    	
    	LevelLoader.loadLevel("src/main/resources/data/levels/level.lvl", this);
    	
    	//http://www.java-gaming.org/index.php/topic,24671.0
    	getContainer().setMaximumLogicUpdateInterval(5);
    	getContainer().setMinimumLogicUpdateInterval(5);
    	getContainer().setTargetFrameRate(60);
    	
    }
    
    public static void setDefaultRes(AppGameContainer ap) throws SlickException{
    	ap.setDisplayMode(ap.getScreenWidth(),ap.getScreenHeight(), Options.Instance.isFullScreen());
    }
    
        @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
    	if(getInput().isKeyDown(Input.KEY_ESCAPE)) container.exit();
		if(getInput().isKeyDown(Input.KEY_P)) Options.Instance.flipPause();
		if(getInput().isKeyDown(Input.KEY_ENTER)) setGameState(GameState.inGame);
		if(getInput().isKeyDown(Input.KEY_SPACE)) getCurrentLevel().setGravity(new Vec2(-getCurrentLevel().getGravity().x, -getCurrentLevel().getGravity().y));
        getCam().update(delta);
    }

    public void render(GameContainer container, Graphics g)
            throws SlickException {
    	try{
    		getCam().Draw();
    	}catch (Exception e){
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	}
    }

	public PostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(PostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    public static void main(String[] args) {
		try {
        	AppGameContainer app=null;
        	try{
        		app = new AppGameContainer(new Game(args[0]));
        	}catch(ArrayIndexOutOfBoundsException e){
        			app = new AppGameContainer(new Game("Title"));
        	}
        	bootStrap(app);
        } catch (SlickException e) {
            e.printStackTrace();
        }
	}
	
    public static void bootStrap(AppGameContainer app){
    	try {
        	setDefaultRes(app);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
    }
}
