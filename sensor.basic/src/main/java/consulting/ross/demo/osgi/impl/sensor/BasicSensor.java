package consulting.ross.demo.osgi.impl.sensor;

import consulting.ross.demo.osgi.envmon.Sensor;
import java.util.Random;

/**
 * A simplistic sensor that returns a randomly-varied
 * temperature (hard-coded for now).
 */
public class BasicSensor implements Sensor
{
  double baseTemp = 25.0;
  double variance = 15.0;
  Random random = new Random();
  
  @Override
  public Double measureTemperature()
  {
    return baseTemp 
      - (variance / 2) 
      + ( random.nextDouble() * variance  );
  }
}
