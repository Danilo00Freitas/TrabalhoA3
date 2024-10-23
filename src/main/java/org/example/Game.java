package org.example;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class Game implements GLEventListener {

    private Track track;
    private Models models;
    private Rectangle[] rectangles;
    private Lighting lighting;
    private int maxObstacles = 10;


    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        GLU glu = new GLU();

        //Setting render
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glClearColor(0, 0, 0, 1);

        //Initializing objects
        track = new Track();
        models = new Models();
        rectangles = new Rectangle[maxObstacles];
        lighting = new Lighting();

        // Generating random obstacles
        generateRectangle();
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        //setting camera and perspective
        setCamera(gl);

        //setting lighting
        lighting.applyLighting(gl);

        //Rendering the track
        track.draw(gl);

        // Rendering the dino
        models.draw(gl);

        //Rendering obstacles

        for (Rectangle rectangle : rectangles) {
            rectangle.draw(gl);
        }

        //update everythings position (tack and obstacles)
        update();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    private void setCamera(GL2 gl) {
        GLU glu = new GLU();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60, 1, 0.1, 100);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(0, 5, 10, 0, 0, -5, 0, 1, 0);
    }

    private void generateRectangle() {
        for (int i = 0; i < maxObstacles; i++) {
            float zPosition = (i + 1) * -10;
            float cubeSize = 2.5f;
            rectangles[i] = new Rectangle(randomXPosition(), zPosition, cubeSize);
        }
    }

    private void update() {

        track.move();
        for (Rectangle rectangle : rectangles) {
            rectangle.move();

            //CREATE THE PROCEDURAL LOGIC
            //ADDING NEW OBSTACLES WHILE THE
        }
    }

    private float randomXPosition() {

        int sign = Math.random() > 0.5 ? 1 : -1;
        return (float) (Math.floor(Math.random() * 5 * sign));
    }

}





