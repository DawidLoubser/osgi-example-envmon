package consulting.ross.demo.osgi.impl.envmon;

import consulting.ross.demo.osgi.envmon.Alarm;
import consulting.ross.demo.osgi.envmon.EnvironmentMonitor;
import consulting.ross.demo.osgi.envmon.Sensor;
import org.apache.felix.dm.DependencyManager;
import org.apache.felix.dm.lambda.DependencyManagerActivator;
import org.osgi.framework.BundleContext;

/**
 * Upon bundle activation, this class publishes and wires
 * together services. Can be done in various ways, but
 * type-safe wiring using Java is least error-prone.
 */
public class EnvironmentMonitorActivator extends DependencyManagerActivator
{
  @Override
  protected void init(BundleContext ctx, DependencyManager dm) throws Exception
  {
    // Publish our monitor as an implementation
    component( c -> c
      .provides( EnvironmentMonitor.class )
      .impl(     EnvironmentMonitorImpl.class )
      .withSvc( 
        true,            // Required
        Alarm.class,     // Injects into field
        Sensor.class     // Injects into field
        )
    );
  }
}
