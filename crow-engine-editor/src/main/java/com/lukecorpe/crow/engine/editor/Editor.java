package com.lukecorpe.crow.engine.editor;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.lukecorpe.crow.engine.Game;
import com.lukecorpe.crow.engine.Options;

public class Editor extends BasicGame {

    public Editor(String gameTitle) {
        super(gameTitle);
    }
    
    public static void main(String[] args) {
        try {
            AppGameContainer app=null;
            try{
                app = new AppGameContainer(new Game(args[0]));
            }catch(ArrayIndexOutOfBoundsException e){
                    app = new AppGameContainer(new Game("Title"));
            }
            app.setDisplayMode(
                    app.getScreenWidth(),app.getScreenHeight(), 
                    Options.Instance.isFullScreen());
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
        // TODO Auto-generated method stub
    }

    public void init(GameContainer container) throws SlickException {
        
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
        // TODO Auto-generated method stub
        
    }
}
