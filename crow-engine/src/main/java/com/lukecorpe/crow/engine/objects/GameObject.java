package com.lukecorpe.crow.engine.objects;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.lukecorpe.crow.engine.Game;
import com.lukecorpe.crow.engine.Options;
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
	
	boolean isFlipVertically;
	
	public GameObject(Level level, BodyType type, Vec2 startPostion, String imagePath) throws SlickException{
		this.level = level;
		
		_texture = new Image(imagePath);
	    level.getDrawList().add(this);
	    _texture.setCenterOfRotation(_texture.getWidth()/2, _texture.getHeight()/2);
		
		bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set(startPostion);
		body = level.getWorld().createBody(bodyDef);
		setPosition(startPostion.x, startPostion.y);
		
		if(shapeCoordinates==null){
			shape = new PolygonShape();
			float hy = _texture.getHeight()/2;
			float hx = _texture.getWidth()/2;
			Vec2[] points = {
					new Vec2(-hx, -hy),
					new Vec2(hx, -hy),
					new Vec2(hx, hy),
					new Vec2(-hx, hy)
					};
			shape.set(points, 4);
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
			if(Options.Instance.isDebug()){
				float[] points = new float[shape.getVertices().length*2];
				int pointCount=0;
				for(int i=0; i<shape.getVertices().length; i++){
					points[pointCount] = shape.getVertices()[i].x;
					points[pointCount+1] = shape.getVertices()[i].y;
					pointCount+=2;
				}
				Polygon polygon = new Polygon(points);
				polygon = (Polygon) polygon.transform(Transform.createRotateTransform(body.getAngle()));
				polygon.setX(body.getPosition().x);
				polygon.setY(body.getPosition().y);
				renderTargetGraph.fill(polygon);
				renderTargetGraph.draw(polygon);	
			}
		}
		//System.out.printf("%4.2f %4.2f %4.2f\n", getPosition().x, getPosition().y,  Math.toDegrees(body.getAngle()));
	}
	
	public Vector2f getPosition() {
		return new Vector2f(body.getPosition().x-_texture.getWidth()/2, body.getPosition().y-_texture.getHeight()/2);
	}

	public Vector2f getPositionCenter() {
		return new Vector2f(body.getPosition().x + _texture.getWidth()/2,
				body.getPosition().y + _texture.getHeight()/2);
	}

	public void setPosition(float x, float y) {
		body.getPosition().set(x+_texture.getWidth()/2,y+_texture.getHeight()/2);
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
