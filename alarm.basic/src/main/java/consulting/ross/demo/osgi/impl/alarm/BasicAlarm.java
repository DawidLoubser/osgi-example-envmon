package consulting.ross.demo.osgi.impl.alarm;

import consulting.ross.demo.osgi.envmon.Alarm;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** The simplest alarm. Alarms are stateful, repetitive
 * attention-grabbers.
 */
public class BasicAlarm implements Alarm
{
  volatile ScheduledExecutorService es;
  
  @Override
  public void activate()
  {
    if ( es == null )
      es = Executors.newScheduledThreadPool(2);
    
    es.scheduleWithFixedDelay( 
      () -> System.err.println("ALARM!!!!!!"), 
      1, 1, TimeUnit.SECONDS );
  }

  @Override
  public void deActivate()
  {
    es.shutdownNow();
    es = null;
  }
}
