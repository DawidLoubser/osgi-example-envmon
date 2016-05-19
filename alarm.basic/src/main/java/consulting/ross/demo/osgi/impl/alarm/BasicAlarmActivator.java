package consulting.ross.demo.osgi.impl.alarm;

import consulting.ross.demo.osgi.envmon.Alarm;
import org.apache.felix.dm.DependencyManager;
import org.apache.felix.dm.lambda.DependencyManagerActivator;
import org.osgi.framework.BundleContext;

/**
 * Publishes our basic alarm. Could be done via various
 * other mechanisms (e.g. config file), but type-safe
 * configuration with Java is least error-prone.
 */
public class BasicAlarmActivator extends DependencyManagerActivator
{
  @Override
  protected void init(BundleContext ctx, DependencyManager dm) throws Exception
  {
    component( c -> c
      .provides( Alarm.class,
        // We can publish metadata (properties)
        // that further describe this service
        "mechanism", "text",
        "service.pid", "alarm.basic",
        "vendor", "Ross Consulting"
      )
      .impl( BasicAlarm.class )
      .properties());
  }
}
