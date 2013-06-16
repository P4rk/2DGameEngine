package com.lukecorpe.crow.engine.level;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

import com.lukecorpe.crow.engine.objects.GameObject;
import com.lukecorpe.crow.engine.objects.Hero;

public class HeroContantListener implements ContactListener {

    
    @Override
    public void beginContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getBody().getUserData();
        Object userDataB = contact.getFixtureB().getBody().getUserData();

        Hero hero = null;
        GameObject obj = null; 
        if(userDataA != null && userDataB!=null){
            if(userDataA instanceof Hero){
                hero = (Hero) userDataA;
                obj = (GameObject) userDataB;
                hero.getContacts().add(obj);
            }else if(userDataB instanceof Hero){
                hero = (Hero)userDataB;
                obj = (GameObject) userDataA;
                hero.getContacts().add(obj);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getBody().getUserData();
        Object userDataB = contact.getFixtureB().getBody().getUserData();

        Hero hero = null;
        GameObject obj = null; 
        if(userDataA != null && userDataB!=null){
            if(userDataA instanceof Hero){
                hero = (Hero) userDataA;
                obj = (GameObject) userDataB;
                hero.getContacts().remove(obj);
            }else if(userDataB instanceof Hero){
                hero = (Hero)userDataB;
                obj = (GameObject) userDataA;
                hero.getContacts().remove(obj);
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // TODO Auto-generated method stub
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        
    }
}
