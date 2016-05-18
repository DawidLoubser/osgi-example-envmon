package consulting.ross.demo.osgi.envmon;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides information about a sucessfully-performed
 * environment survey. Note: Success means that the
 * survey process itself did not fail or was not refused -
 * the alarm may have been raised due to, for example,
 * sensor failure.
 */
public class SurveyResult
{
  private Boolean alarmWasRaised;
  private Double measuredAverage;
  private Double maxAllowed;
  private Map<String,Double> measurements = new HashMap<>();

  
  /** True if the alarm was raised */
  public Boolean getAlarmWasRaised()
  {
    return alarmWasRaised;
  }

  public void setAlarmWasRaised(Boolean alarmWasRaised)
  {
    this.alarmWasRaised = alarmWasRaised;
  }

  /** Average temperature as read from all functioning sensors */
  public Double getMeasuredAverage()
  {
    return measuredAverage;
  }

  public void setMeasuredAverage(Double measuredAverage)
  {
    this.measuredAverage = measuredAverage;
  }

  /** Configured maximum temperature */
  public Double getMaxAllowed()
  {
    return maxAllowed;
  }

  public void setMaxAllowed(Double maxAllowed)
  {
    this.maxAllowed = maxAllowed;
  }

  /** The values of all the successful measurements that were made,
   *  keyed by the identifier of the Sensor that produced it.
   */
  public Map<String, Double> getMeasurements()
  {
    return measurements;
  }
}
