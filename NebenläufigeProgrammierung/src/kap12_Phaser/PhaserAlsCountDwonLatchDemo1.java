package kap12_Phaser;

/**
 * Codebeispiel: Phaser als CountDownLatch mit dem Startsignal vom main-Thread
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class PhaserAlsCountDwonLatchDemo1
{
  static class Athlet implements Runnable
  {
    private final String name;
    private final Phaser phaser;

    public Athlet(String name, Phaser phaser)
    {
      this.name  = name;
      this.phaser = phaser;
    }

    @Override
    public void run()
    {
      System.out.println(name + " ist bereit ....");
      
      // Warte auf das Startsignal
      phaser.awaitAdvance(0);
      
      randomDelay(1000);
      System.out.println(name + " ist am Ziel ");
    }
    
    private void randomDelay(int ms)
    {
      try
      {
        TimeUnit.MILLISECONDS.sleep( ThreadLocalRandom.current().nextInt(ms));
      }
      catch (InterruptedException e)
      {
        Thread.currentThread().interrupt();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException
  {
    ExecutorService executor = Executors.newFixedThreadPool(3);
    
    Phaser startlinie = new Phaser(1);
    Athlet a1 = new Athlet("Card Lewis", startlinie);
    Athlet a2 = new Athlet("Maurice Greene", startlinie);
    Athlet a3 = new Athlet("Usain Bolt", startlinie);
    
    executor.execute(a1);
    executor.execute(a2);
    executor.execute(a3);
    

    TimeUnit.MILLISECONDS.sleep(500);
    
    System.out.println("Los!");
    startlinie.arrive();
    
    executor.shutdown();
  }

}
