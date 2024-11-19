package org.example;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import org.example.Interface.GameInterface;

public class Game implements GLEventListener {

    private Track track;
    private Models model;
    private Rectangle[] rectangles;
    private Lighting lighting;
    private Controls controls;
    private int maxObstacles = 10;
    private float posX = 0.0f;
    private float posY = 0.0f;
    private boolean isJumping = false;
    private float jumpMaxHeight = 3.0f;
    private float jumpVelocity = 0.2f;
    private float gravity = 0.2f;
    private Boolean canJump;
    private long jumpCooldown = 1000;
    private long lastJumpTime = 0;
    public enum gameState{MENU,INGAME,GAMEOVER};
    private gameState currentGameState = gameState.MENU;
    private GameInterface gameInterface;

    public Game() {
        controls = new Controls();
    }
    public void setGameInterface(GameInterface gameInterface) {
        this.gameInterface = gameInterface;
    }

    public void setGameState(gameState state){
        this.currentGameState = state;
    }

    public void initControls(GLCanvas canvas) {
        controls.initKeyListeners(canvas);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        GLU glu = new GLU();

        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glClearColor(0, 0, 0, 1);

        track = new Track();
        model = new Models(1.0f);
        rectangles = new Rectangle[maxObstacles];
        lighting = new Lighting();

        generateRectangle();
    }


    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        currentGameState = gameState.INGAME;
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        // Atualizar posição com base nas teclas pressionadas
        float moveSpeed = 0.1f;
        if (controls.isMoveLeft()) {
            posX -= moveSpeed;
            if (posX < -5.0f){posX = - 5.0f;}

        }
        if (controls.isMoveRight()) {
            posX += moveSpeed;
            if (posX > 5.0f){posX = 5.0f;}
        }

        handleJump();

        for (Rectangle rectangle : rectangles) {
            if (rectangle.checkCollisionAndEndGame(posX, posY, model.getCubeSize())) {
                if (gameInterface != null) {
                    currentGameState = gameState.GAMEOVER;
                    gameInterface.showGameOver();
                }
                return;
            }
        }

        //setting camera and perspective
        setCamera(gl);

        //setting lighting
        lighting.applyLighting(gl);

        //Rendering the track
        track.draw(gl);

        // Rendering the cube
        model.draw(gl,posX,posY);

        //Rendering obstacles

        for (Rectangle rectangle : rectangles) {
            rectangle.draw(gl);
        }

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
            rectangle.move(randomXPosition());
        }
    }

    public static float randomXPosition() {

        int sign = Math.random() > 0.5 ? 1 : -1;
        return (float) (Math.floor(Math.random() * 5 * sign));
    }

    private void handleJump() {
        if (controls.isJump() && !isJumping && canJump()) {
            isJumping = true;
            lastJumpTime = System.currentTimeMillis();
        }

        if (isJumping) {
            posY += jumpVelocity;
            if (posY >= jumpMaxHeight) {
                isJumping = false;
            }
        } else if (posY > 0) {
            posY -= gravity;
            if (posY < 0) posY = 0;
        }
    }

    private Boolean canJump() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastJumpTime) >= jumpCooldown;
    }

    public void resetGame() {
        // Resetando a posição do jogador
        posX = 0.0f;
        posY = 0.0f;
        isJumping = false;
        canJump = true;

        // Resetando os obstáculos
        generateRectangle();

        // Reiniciando o estado do jogo
        currentGameState = gameState.INGAME;
    }


}





