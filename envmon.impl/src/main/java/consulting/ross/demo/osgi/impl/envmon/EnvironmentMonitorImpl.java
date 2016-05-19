package consulting.ross.demo.osgi.impl.envmon;

import consulting.ross.demo.osgi.envmon.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.*;
import java.util.logging.Logger;

/**
 * A deliberately-simplistic implementation, with
 * one sensor / alarm only.
 */
public class EnvironmentMonitorImpl implements EnvironmentMonitor
{
  private static Logger logger = Logger.getLogger(EnvironmentMonitorImpl.class.getName());
  ScheduledExecutorService es;
  Long pollDelayMs = 2000L;
  Double maxTemperature = 30.0; 
  volatile Sensor sensor;
  volatile Alarm alarm;
  
  
  @Override
  public SurveyResult performSurvey()
  {
    // Check technical requirements (system error if not met)
    if (sensor == null) 
      throw new RuntimeException("Not connected to a Sensor");
    if (alarm == null)
      throw new RuntimeException("Not connected to an alarm");

    SurveyResult r = new SurveyResult();
    r.setMaxAllowed( maxTemperature );
    
    Double measuredAvg = sensor.measureTemperature();
    r.getMeasurements().put("sensor", measuredAvg);
    r.setMeasuredAverage( measuredAvg );
    
    if (measuredAvg > maxTemperature) 
    {
      alarm.activate();
      r.setAlarmWasRaised( true );
    }
    else 
    {
      r.setAlarmWasRaised( false );
    }
    
    logger.info("Completed environment survey: " + r);
    return r;
  }

  @Override
  public void startMonitoring()
  {
    if (es == null)
    {
      es = Executors.newScheduledThreadPool(2);
      logger.info("Scheduling survey for every " 
        + pollDelayMs + "ms");
      
      es.scheduleWithFixedDelay( () ->
          performSurvey(),
        0, pollDelayMs, MILLISECONDS);
    }
    else
    {
      // This is an idempotent service
      logger.warning("Auto-monitoring already started (every " 
        + pollDelayMs + "ms)");
    }
  }

  @Override
  public void stopMonitoring()
  {
    // This is an idempotent service
    if (es == null || es.isShutdown())
    {
      logger.warning("Auto-monitoring already stopped");
      return;
    }
    es.shutdownNow();
    es = null;
    resetAlarms();
  }

  @Override
  public void resetAlarms()
  {
    if (alarm != null)
      alarm.deActivate();
  }
}
