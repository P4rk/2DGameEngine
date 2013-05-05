package com.lukecorpe.crow.engine.level;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import com.lukecorpe.crow.engine.Component;
import com.lukecorpe.crow.engine.Game;
import com.lukecorpe.crow.engine.interfaces.Drawable;

public class Background extends Component implements Drawable{
	ArrayList<BackgroundTile> backgrounds;
    public Background(Game game)
    {
    	super(game);
        backgrounds = new ArrayList<BackgroundTile>();
		LoadContent("/src/main/resources/images/BGTile.png");
		
    }
    public void LoadContent(String texturePath)
    {
        BackgroundTile tile = new BackgroundTile(getGame());
        tile.setTexture(texturePath);
        backgrounds.add(tile);
    }

    public void update(int delta)
    {
        for (BackgroundTile t : backgrounds)
        {
            t.update(delta);
        }
        
    }
    public void draw(Graphics renderTargetGraph)
    {
    	for (BackgroundTile t : backgrounds)
        {
            t.draw(renderTargetGraph);
        }
    }
}
