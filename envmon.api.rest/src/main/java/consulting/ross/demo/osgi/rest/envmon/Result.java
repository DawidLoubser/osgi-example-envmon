package consulting.ross.demo.osgi.rest.envmon;

import consulting.ross.demo.osgi.envmon.SurveyResult;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Web Service messages require at least one JAX-RS
 * annotation. For simplicity, we simply embed the
 * existing result her.
 */
@XmlRootElement
public class Result
{
  private SurveyResult result;
  
  public Result( SurveyResult result )
  {
    setResult( result );
  }

  public Result(){}
    
  public SurveyResult getResult()
  {
    return result;
  }

  public void setResult(SurveyResult result)
  {
    this.result = result;
  }
}
