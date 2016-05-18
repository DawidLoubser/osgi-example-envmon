package consulting.ross.demo.osgi.envmon;

/**
 * Provides information about the measurable physical 
 * phenomena of an environment.
 */
public interface Sensor
{
  /** Indicates the current temperature in the
   * environment which the sensor is placed. 
   */
  Double measureTemperature();
}
