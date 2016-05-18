package consulting.ross.demo.osgi.envmon;

/**
 * Provides services to automatically, or on-demand, survey an 
 * environment. Raises alarms on problematic or 
 * indeterminate conditions.
 */
public interface EnvironmentMonitor
{
  /** Take sensor readings (temperature only for simplicity) 
   *  and raise alarm(s) if necessary. */
  SurveyResult performSurvey();

  /** Start surveying the environment on a timely 
   * basis, until stopped. */
  void startMonitoring();
  
  /** Stop automatic surveillance. */
  void stopMonitoring();
  
  /** De-activate any activated alarms. */
  void resetAlarms();
}
