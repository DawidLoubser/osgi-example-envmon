package consulting.ross.demo.osgi.impl.sensor;

import consulting.ross.demo.osgi.envmon.Alarm;
import consulting.ross.demo.osgi.envmon.Sensor;
import org.apache.felix.dm.DependencyManager;
import org.apache.felix.dm.lambda.DependencyManagerActivator;
import org.osgi.framework.BundleContext;

/**
 * Publishes our basic sensor. Could be done via various
 * other mechanisms (e.g. config file), but type-safe
 * configuration with Java is least error-prone.
 */
public class BasicSensorActivator extends DependencyManagerActivator
{
  @Override
  protected void init(BundleContext ctx, DependencyManager dm) throws Exception
  {
    // Publish alarm
    component( c -> c
      .provides( Sensor.class,
        // We can publish metadata (properties)
        // that further describe this service
        "service.pid", "sensor.basic.random",
        "vendor", "Ross Consulting"
      )
      .impl( BasicSensor.class ));
  }
}
