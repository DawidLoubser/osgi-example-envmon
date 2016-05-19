package consulting.ross.demo.osgi.impl.alarm;

import consulting.ross.demo.osgi.envmon.Alarm;
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
 * Simplistic karaf shell command to control the alarm
 */
@Command(scope = "envmon", name = "alarm", 
  description = "Interacts with the alarm")
@Service
public class AlarmShellCommand implements Action
{
  @Reference
  Alarm alarm;
  
  @Argument(
    index = 0, 
    name = "action", 
    description = "Action: on, off")
  @Completion(ActionCompleter.class)  
  String action = null;
  
  @Override
  public Object execute() throws Exception
  {
    if (alarm == null)
      throw new Exception("No alarm available");
    
    if ( "on".equalsIgnoreCase(action) )
      alarm.activate();
    
    else if ( "off".equalsIgnoreCase(action) )
      alarm.deActivate();
    
    else
      throw new Exception("Unknown action: " + action);
      
    return null;
  }

  /** Auto-completion support for action */
  public static class ActionCompleter implements Completer
  {
    @Override
    public int complete(Session s, CommandLine cl, List<String> candidates)
    {
      // Use a helper
      StringsCompleter comp= new StringsCompleter();
      comp.getStrings().add("on");
      comp.getStrings().add("off");
      return comp.complete(s, cl, candidates);
    }
  }
}
