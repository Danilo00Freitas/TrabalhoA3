package org.example;

import com.jogamp.opengl.awt.GLCanvas;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controls {

    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean jump = false;

    public void initKeyListeners(GLCanvas canvas) {
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {

                    case KeyEvent.VK_LEFT:
                        moveLeft = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRight = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        if (!jump) {
                            jump = true;
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {

                    case KeyEvent.VK_LEFT:
                        moveLeft = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRight = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        jump = false;
                        break;
                }
            }
        });
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public boolean isJump() {return jump;}
}
