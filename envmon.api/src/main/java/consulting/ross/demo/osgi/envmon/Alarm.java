package consulting.ross.demo.osgi.envmon;

/**
 * Attracts attention, such as through audio 
 * or visual means.
 */
public interface Alarm
{
  /** Starts attracting attention. */
  void activate();
  
  /** Stops attracting attention. */
  void deActivate();
}
