import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {

   @Override 
   public void keyPressed(KeyEvent e)
   {
      if (e.getKeyCode() == KeyEvent.VK_W) 
         BasicSnake.north();
      if (e.getKeyCode() == KeyEvent.VK_A) 
         BasicSnake.west();
      if (e.getKeyCode() == KeyEvent.VK_S) 
         BasicSnake.south();
      if (e.getKeyCode() == KeyEvent.VK_D) 
         BasicSnake.east();
   }
   
   public void keyReleased(KeyEvent e) {}
   public void keyTyped(KeyEvent e) {}
}