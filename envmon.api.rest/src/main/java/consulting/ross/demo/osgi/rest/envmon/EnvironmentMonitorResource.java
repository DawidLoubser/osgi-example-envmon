package consulting.ross.demo.osgi.rest.envmon;

import consulting.ross.demo.osgi.envmon.SurveyResult;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Contract for a RESTful environment monitor.
 * Adapts resource-centric concepts to the
 * existing EnvironmentMonitor contract, and
 * re-uses data types as far as possible.
 */
@Path("monitor")
@Produces(MediaType.APPLICATION_JSON)
public interface EnvironmentMonitorResource
{
  @POST
  @Path("survey-request")
  Result performSurvey();
  
  @POST
  @Path("alarm-reset")
  void resetAlarms();
}
