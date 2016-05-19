package consulting.ross.demo.osgi.adapter.rest.envmon;

import consulting.ross.demo.osgi.envmon.EnvironmentMonitor;
import consulting.ross.demo.osgi.rest.envmon.EnvironmentMonitorResource;
import org.apache.felix.dm.DependencyManager;
import org.apache.felix.dm.lambda.DependencyManagerActivator;
import org.osgi.framework.BundleContext;

/**
 * Publishes the REST adapter as a service, with the
 * correct metadata such that CXF DOSGi or similar
 * will publish it.
 */
public class Activator extends DependencyManagerActivator
{
  @Override
  protected void init(BundleContext ctx, DependencyManager dm) throws Exception
  {
    adapter( EnvironmentMonitor.class, 
      adapt -> adapt 
        .impl( EnvironmentMonitorResourceAdapter.class)
        .provides(EnvironmentMonitorResource.class,
          "service.exported.interfaces", "*",
          "service.exported.configs", "org.apache.cxf.rs",
          "service.exported.intents", "HTTP",
          "org.apache.cxf.rs.httpservice.context", "/rest-adaptor")
    );
  }
}
