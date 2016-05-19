package consulting.ross.demo.osgi.envmon.impl;

import consulting.ross.demo.osgi.envmon.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the logic of {@linkplain EnvironmentMonitorImpl}
 */
public class EnvironmentMonitorImplTest
{
  @Test
  public void performSurvey_1workingalarm_1workingsensor_tempOk() throws Exception
  {
    final Mockery m = new Mockery();
    final Alarm alarm = m.mock(Alarm.class);
    final Sensor sensor = m.mock(Sensor.class);
    
    // Configure our test subject for this scenario
    EnvironmentMonitorImpl em = new EnvironmentMonitorImpl();
    em.maxTemperature = 30.0;
    em.alarm = alarm;
    em.sensor = sensor;
    
    // Our expectations
    m.checking( new Expectations(){{
      
      oneOf(sensor).measureTemperature();
      will( returnValue( 25.0 ));
      
      never(alarm);
      
    }});

    SurveyResult result = em.performSurvey();
    
    // Are we happy with the process that transpired?
    m.assertIsSatisfied();
    
    // Are we happy with the output(s)?
    assertFalse(  result.getAlarmWasRaised() );
    assertEquals( 25.0, result.getMeasuredAverage(), PREC );
    assertEquals( 30.0, result.getMaxAllowed(), PREC );
    assertEquals( 1,  result.getMeasurements().size() );
  }
  
  @Test
  public void performSurvey_1workingAlarm_1workingSensor_tempTooHigh() throws Exception
  {
    final Mockery m = new Mockery();
    final Alarm alarm = m.mock(Alarm.class);
    final Sensor sensor = m.mock(Sensor.class);
    
    // Configure our test subject for this scenario
    EnvironmentMonitorImpl em = new EnvironmentMonitorImpl();
    em.maxTemperature = 20.0;
    em.alarm = alarm;
    em.sensor = sensor;
    
    // Our expectations
    m.checking( new Expectations(){{
      
      oneOf(sensor).measureTemperature();
      will( returnValue( 25.0 ));
      
      oneOf(alarm).activate();
    }});

    SurveyResult result = em.performSurvey();
    
    // Are we happy with the process that transpired?
    m.assertIsSatisfied();
    
    // Are we happy with the output(s)?
    assertTrue(  result.getAlarmWasRaised() );
    assertEquals( 25.0, result.getMeasuredAverage(), PREC );
    assertEquals( 20.0, result.getMaxAllowed(), PREC );
    assertEquals( 1,  result.getMeasurements().size() );
  }

  
  private static final double PREC = 0.00001;
}
