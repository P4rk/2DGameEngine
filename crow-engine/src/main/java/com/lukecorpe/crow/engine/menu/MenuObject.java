package com.lukecorpe.crow.engine.menu;

import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.geom.Vector2f;

import com.lukecorpe.crow.engine.level.Level;
import com.lukecorpe.crow.engine.math.MathUtils;
import com.lukecorpe.crow.engine.objects.GameObject;

public abstract class MenuObject extends GameObject {

	public MenuObject(com.lukecorpe.crow.engine.Game game) {
		//super(game);
	}
	public MenuObject(com.lukecorpe.crow.engine.Game game, BodyType type) {
		//super(game, type);
	}
	
	@Override
	public void setPosition(Vector2f _position) {
		//this._position = new Vector2f(MathUtils.clamp(_position.getX(), 0, getGame().getCurrentMenu().getWidth()-getBounding().getWidth()), MathUtils.clamp(_position.getY(), 0, getGame().getCurrentMenu().getHeight()-getBounding().getHeight()));
	}
	@Override
	public void setPosition(float x, float y) {
		//this._position = new Vector2f(MathUtils.clamp(x, 0, getGame().getCurrentMenu().getWidth()-getBounding().getWidth()), MathUtils.clamp(y, 0, getGame().getCurrentMenu().getHeight()-getBounding().getHeight()));
	}
	
	

}
