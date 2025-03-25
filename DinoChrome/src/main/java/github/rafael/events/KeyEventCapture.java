package github.rafael.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventCapture implements KeyListener {
    public boolean spaceKey, escKey, down;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_SPACE || code == KeyEvent.VK_W ||  code == KeyEvent.VK_UP){
            spaceKey = true;
        }
        else if(code == KeyEvent.VK_ESCAPE){
            escKey = true;
        }
        else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN ){
            down = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_SPACE || code == KeyEvent.VK_W ||  code == KeyEvent.VK_UP){
            spaceKey = false;
        }
        else if(code == KeyEvent.VK_ESCAPE){
            escKey = false;
        }
        else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN ){
            down = false;
        }
    }
}
