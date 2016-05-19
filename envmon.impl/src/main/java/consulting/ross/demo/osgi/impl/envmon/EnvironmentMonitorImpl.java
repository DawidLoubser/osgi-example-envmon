package consulting.ross.demo.osgi.impl.envmon;

import consulting.ross.demo.osgi.envmon.*;

/**
 * A deliberately-simplistic implementation, with
 * one sensor / alarm only.
 */
public class EnvironmentMonitorImpl implements EnvironmentMonitor
{
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
    
    return r;
  }

  @Override
  public void startMonitoring()
  {
    // TODO: Implement
  }

  @Override
  public void stopMonitoring()
  {
    // TODO: Implement
  }

  @Override
  public void resetAlarms()
  {
    // TODO: Implement
  }
}
