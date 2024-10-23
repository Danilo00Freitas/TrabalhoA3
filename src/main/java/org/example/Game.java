package org.example;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class Game implements GLEventListener {

    private Track track;
    private Models models;
    private Obstacle[] obstacles;
    private Lighting lighting;
    private int maxObstacles = 10;


    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        GLU glu = new GLU();

        //Setting render
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glClearColor(0,0,0,1);

        //Initializing objects
        track = new Track();
        models = new Models();
        obstacles = new Obstacle[maxObstacles];
        lighting = new Lighting();

        // Generating random obstacles
        generateObstacles();
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

        for (Obstacle obstacle: obstacles){
            obstacle.draw();
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
    public void dispose(GLAutoDrawable drawable) {}

    private void setCamera(GL2 gl) {
        GLU glu = new GLU();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60, 1, 0.1, 100);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(0, 5, 10, 0, 0, -5, 0, 1, 0);
    }

    private void generateObstacles(){
        for (int i = 0; i < maxObstacles; i++){
            obstacles[i] = new Obstacle(randomPosition(), randomPosition(), randomSize());
        }
    }

    private void update() {

        track.move();
        for (Obstacle obstacle : obstacles) {
            obstacle.move();

            //CREATE THE PROCEDURAL LOGIC
            //ADDING NEW OBSTACLES WHILE THE
        }
    }

    private float randomPosition() {
        return (float) (Math.random() * 10 - 5); // Gera posições aleatórias na pista
    }

    private float randomSize() {
        return (float) (Math.random() * 1.5 + 0.5); // Tamanhos variáveis dos obstáculos
    }
}


