package consulting.ross.demo.osgi.adapter.rest.envmon;

import consulting.ross.demo.osgi.envmon.EnvironmentMonitor;
import consulting.ross.demo.osgi.rest.envmon.EnvironmentMonitorResource;
//import consulting.ross.demo.osgi.envmon.SurveyResult;
import consulting.ross.demo.osgi.rest.envmon.Result;

/**
 * Implementation of a REST resource that exposes some
 * {@linkplain EnvironmentMonitor} functionality via
 * JSON/HTTP.
 */
public class EnvironmentMonitorResourceAdapter implements EnvironmentMonitorResource
{
  // We adapt to the real service
  volatile EnvironmentMonitor delegate;
  
  @Override
  public Result performSurvey()
  {
    return new Result( 
      delegate.performSurvey() );
  }

  @Override
  public void resetAlarms()
  {
    delegate.resetAlarms();
  }
}
