import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import java.util.Timer;
import java.util.ArrayList;

public class BasicSnake
{
   public static JFrame frame = new JFrame(" BasicSnake   -   Controls: W A S D");
   static ArrayList<Block> theSnake;
   static int length;
   static boolean n, w, s, e, eaten, over, vertical, horizontal;
   static int[] apple = {0, 0};
   
   public static void draw(Graphics g)
   {
      g.drawString("Emin Bahadýr Tülüce  -  November 2016", 394, 423);
      final int k = 20;
      g.setColor(Color.RED);
      g.fillRect(apple[0]*k + 10, apple[1]*k + 10, 18, 18);
      g.setColor(Color.BLACK);
      g.drawRect(10, 10, 600, 400);
      for (Block theBlock : theSnake)
         g.fillRect(theBlock.x*k + 10, theBlock.y*k + 10, 18, 18);
      
   }
   
   public static void tick()
   {
      Ticker myTask = new Ticker();
      Timer myTimer = new Timer();
      myTimer.schedule(myTask, 70);
      
      if (eaten)
      {
         boolean findAnother;
         do{
            apple[0] = (int) (Math.random() * 30);
            apple[1] = (int) (Math.random() * 20);
            findAnother = false;
            for (Block theBlock : theSnake)
               if(apple[0] == theBlock.x && apple[1] == theBlock.y)
                  findAnother = true;
         }while(findAnother);
         eaten = false;
      }
      for (int i = length-1 ; i > 0; i--)
      {
         Block theBlock = theSnake.get(i);
         theBlock.x = theSnake.get(i-1).x;
         theBlock.y = theSnake.get(i-1).y;
      }
      Block head = theSnake.get(0);
      if (n){
         head.y--;
         horizontal = false;
         vertical = true;
      }
      else if (w){
         head.x--;
         horizontal = true;
         vertical = false;
      }
         
      else if (s){
         head.y++;
         horizontal = false;
         vertical = true;
      }
      else if (e){
         head.x++;
      horizontal = true;
      vertical = false;
      }
      
      for (int i = length-1 ; i > 0; i--)
      {
         Block theBlock = theSnake.get(i);
         if (theBlock.x == head.x && theBlock.y == head.y)
            over = true;
         if (head.x > 29 || head.x < 0 || head.y < 0 || head.y > 19)
            over = true;
      }
      
      if (over)
      {
         JOptionPane.showMessageDialog(null, "Score: " + (length - 4), "Game over.",
                                       JOptionPane.ERROR_MESSAGE);
         String[] arg = {"", ""};
         main(arg);
         return;
      }
      
      if (head.x == apple[0] && head.y == apple[1])
      {
         eaten = true;
         length++;
         theSnake.add(new Block(-100, -100));
      }
      
      frame.repaint();
   }
   
   public static void north() {
      if (horizontal)
      {
         n = true;
         w = s = e = false;
      }
   }
   public static void west() {
      if (vertical)
      {
         w = true;
         n = s = e = false;
      }
   }
   public static void south() {
      if (horizontal)
      {
         s = true;
         n = w = e = false;
      }
   }
   public static void east() {
      if (vertical)
      {
         e = true;
         n = w = s = false;
      }
   }
   
   public static void main(String[] args)
   {
      theSnake = new ArrayList<Block>();
      length = 4;
      n = w = s = false;
      e = true;   
      apple[0] = (int) (Math.random() * 30);
      apple[1] = (int) (Math.random() * 20);
      eaten = false;
      over = false;
      frame.setSize(635, 465);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JComponent component = new JComponent()
      {
         public void paintComponent(Graphics graph)
         {
            draw(graph);
         }
      };
      frame.add(component);
      
      theSnake.add(new Block(7, 5));
      theSnake.add(new Block(6, 5));
      theSnake.add(new Block(5, 5));
      theSnake.add(new Block(4, 5));
      horizontal = true;
      vertical = false;
      
      tick();
      frame.addKeyListener(new Listener());
      
   }
   
}
