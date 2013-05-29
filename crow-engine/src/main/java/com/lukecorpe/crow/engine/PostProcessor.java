package com.lukecorpe.crow.engine;

import org.lwjgl.Sys;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.shader.ShaderProgram;

import com.lukecorpe.crow.engine.interfaces.Drawable;

public class PostProcessor extends Component{
    private ShaderProgram program;
    private boolean shaderWorks = false;
    
    public PostProcessor(com.lukecorpe.crow.engine.Game game) {
        super(game);
    }

    public void init(){
        // this test requires shaders
        if (!ShaderProgram.isSupported()) {
            // Sys is part of LWJGL -- it's a handy way to show an alert
            Sys.alert("Error", "Your graphics card doesn't support OpenGL shaders.");
            getGame().getContainer().exit();
            return;
        }
    
        // load our shader program
        try {
            // load our vertex and fragment shaders
            final String VERT = "data/shaders/pass.vert";
            final String FRAG = "data/shaders/BlackAndWhite.frag";
            program = ShaderProgram.loadProgram(VERT, FRAG);
            shaderWorks = true;
        } catch (SlickException e) {
            // there was a problem compiling our source! show the log
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(int delta) {
        // TODO Auto-generated method stub
        
    }

    public void draw() {
        //start using our program
        if (shaderWorks)
            program.bind();
        
        //render our shapes with the shader enabled
        getGame().getCurrentLevel().draw();
        
        //stop using our program
        if (shaderWorks)
            program.unbind();
    }
}
