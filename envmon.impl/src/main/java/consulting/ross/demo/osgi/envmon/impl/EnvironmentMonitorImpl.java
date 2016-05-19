package consulting.ross.demo.osgi.envmon.impl;

import consulting.ross.demo.osgi.envmon.*;

/**
 * A deliberately-simplistic implementation, with
 * one sensor / alarm only.
 */
public class EnvironmentMonitorImpl implements EnvironmentMonitor
{
  double maxTemperature = 30.0; 
  volatile Sensor sensor;
  volatile Alarm alarm;
  
  
  @Override
  public SurveyResult performSurvey()
  {
    return null;
  }

  @Override
  public void startMonitoring()
  {

  }

  @Override
  public void stopMonitoring()
  {

  }

  @Override
  public void resetAlarms()
  {

  }
}
