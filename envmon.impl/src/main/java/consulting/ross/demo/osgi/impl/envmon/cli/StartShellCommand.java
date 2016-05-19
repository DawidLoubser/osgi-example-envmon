package consulting.ross.demo.osgi.impl.envmon.cli;

import consulting.ross.demo.osgi.envmon.EnvironmentMonitor;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;

/**
 * Simplistic karaf shell command to call
 * {@linkplain EnvironmentMonitor#startMonitoring()}
 */
@Command(scope = "envmon", name = "start", 
  description = "Start automatic monitoring")
@Service
public class StartShellCommand implements Action
{
  @Reference
  EnvironmentMonitor monitor;
 
  @Override
  public Object execute() throws Exception
  {
    if (monitor == null)
      throw new Exception("No EnvironmentMonitor available");
    
    monitor.startMonitoring();
    return null;
  }
}
