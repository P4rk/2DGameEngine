package com.lukecorpe.crow.engine;

import org.jbox2d.common.Vec2;

public class Constants {
	public final static float GROUND_DRAG = 0.3f;
	public final static float AIR_DRAG = 0.1f;
	
	
	public final static float MAX_MOVE_SPEED =  2f;
	public final static float MAX_FALL_SPEED = 5f;
	
	public final static float PHYSICS_FACTOR = 4f;
	
	public static Vec2 toPhysicsSize(Vec2 vec2){
        return new Vec2(toPhysicsSize(vec2.x), toPhysicsSize(vec2.y));
    }
    
	public static Vec2 fromPhysicsSize(Vec2 vec2){
        return new Vec2(fromPhysicsSize(vec2.x), fromPhysicsSize(vec2.y));
    }
    
	public static float toPhysicsSize(float f){
        return f/Constants.PHYSICS_FACTOR;
    }
    
	public static float fromPhysicsSize(float f){
        return f*Constants.PHYSICS_FACTOR;
    }
}
