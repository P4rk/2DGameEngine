package com.lukecorpe.crow.engine.objects;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.SlickException;

import com.lukecorpe.crow.engine.level.Level;

public class Hero extends GameObject {

    private boolean isColliding = false;
    
    private boolean isOnGround = false;
    
    private List<GameObject> contacts;
    
    public Hero(Level level, BodyType type, Vec2 startPostion, String imagePath)
            throws SlickException {
        super(level, type, startPostion, imagePath);
        setHero(true);
        setContacts(new ArrayList<GameObject>());
    }

    @Override
    public void update(int delta){
        super.update(delta);
        this.setColliding(false);
        this.setOnGround(false);
        if(!contacts.isEmpty()){
            this.setColliding(true);
            for(GameObject obj : contacts){
                if(obj.getTop() > this.getBottom()){
                    this.setOnGround(true);
                }
            }
        }
        setUpRight();
    }

    private void setUpRight() {
        
    }

    public boolean isColliding() {
        return isColliding;
    }

    public void setColliding(boolean isColliding) {
        this.isColliding = isColliding;
    }

    public boolean isOnGround() {
        return isOnGround;
    }

    public void setOnGround(boolean isOnGround) {
        this.isOnGround = isOnGround;
    }


    public List<GameObject> getContacts() {
        return contacts;
    }

    public void setContacts(List<GameObject> contacts) {
        this.contacts = contacts;
    }

}
