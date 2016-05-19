package consulting.ross.demo.osgi.impl.envmon.cli;

import consulting.ross.demo.osgi.envmon.Alarm;
import consulting.ross.demo.osgi.envmon.EnvironmentMonitor;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Completion;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.apache.karaf.shell.api.console.CommandLine;
import org.apache.karaf.shell.api.console.Completer;
import org.apache.karaf.shell.api.console.Session;
import org.apache.karaf.shell.support.completers.StringsCompleter;

import java.util.List;

/**
 * Simplistic karaf shell command to perform a 
 * {@linkplain EnvironmentMonitor#performSurvey()}
 */
@Command(scope = "envmon", name = "survey", 
  description = "Survey the environment (once-off)")
@Service
public class PerformSurveyShellCommand implements Action
{
  @Reference
  EnvironmentMonitor monitor;
 
  @Override
  public Object execute() throws Exception
  {
    if (monitor == null)
      throw new Exception("No EnvironmentMonitor available");
    
    return monitor.performSurvey();
  }
}
