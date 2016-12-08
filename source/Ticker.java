import java.util.TimerTask;
public class Ticker extends TimerTask
{
   @Override
   public void run()
   {
      if (!BasicSnake.over)
         BasicSnake.tick();
   }
}