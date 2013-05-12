package com.lukecorpe.crow.engine.objects;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.lukecorpe.crow.engine.Game;
import com.lukecorpe.crow.engine.interfaces.Drawable;
import com.lukecorpe.crow.engine.level.Level;

public class GameObject implements Drawable {

	BodyDef bodyDef;
	Body body;
	PolygonShape shape;
	FixtureDef fixtureDef;
	
	float density = 1.0f, friction = 0.3f;
	Vec2[] shapeCoordinates;
	
	Image _texture;
	
	Level level;
	
	boolean isFlipHorizontally, isFlipVertically;
	
	public GameObject(Level level, BodyType type, Vec2 startPostion, String imagePath) throws SlickException{
		this.level = level;
		
		_texture = new Image(imagePath);
	    level.getDrawList().add(this);
	    _texture.setCenterOfRotation(_texture.getWidth()/2, _texture.getHeight()/2);
		
		bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set(startPostion);
		body = level.getWorld().createBody(bodyDef);
		
		if(shapeCoordinates==null){
			shape = new PolygonShape();
			shape.setAsBox(_texture.getWidth()/2, _texture.getHeight()/2);
		}else{
			shape.set(shapeCoordinates, shapeCoordinates.length);
		}
		
		fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
	    fixtureDef.density = density;
	    fixtureDef.friction = friction;
	    body.createFixture(fixtureDef);
	}
	
	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics renderTargetGraph) {
		if(getLevel().getGame().getCam().inFrame(this)){
			getTexture().setRotation((float) Math.toDegrees(body.getAngle()));
			renderTargetGraph.drawImage(
					getTexture(),
					getPosition().getX(),
					getPosition().getY());
		}
	}
	
	public Vector2f getPosition() {
		return new Vector2f(body.getPosition().x, body.getPosition().y);
	}

	public Vector2f getPositionCenter() {
		return new Vector2f(body.getPosition().x + _texture.getWidth()/2,
				body.getPosition().y + _texture.getHeight()/2);
	}

	public void setPosition(float x, float y) {
		body.getPosition().x = x;
		body.getPosition().y = y;
	}

	public void setPosition(Vector2f _position) {
		body.getPosition().x = _position.x;
		body.getPosition().y = _position.y;
	}
	
	public Level getLevel(){
		return level;
	}
	
	public Image getTexture(){
		return _texture;
	}
	public boolean isFlipVertically(){
		return isFlipVertically;
	}
	public boolean isFlipHorizontally(){
		return isFlipHorizontally;
	}
	public void setShapeCoordinates(Vec2[] vec2){
		shapeCoordinates = vec2;
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public float getFriction() {
		return friction;
	}

	public void setFriction(float friction) {
		this.friction = friction;
	}

}
