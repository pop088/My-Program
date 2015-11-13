import junit.framework.*;
import com.mockobjects.servlet.*;
import org.json.JSONObject;
import java.net.URL;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class TestConverterServlet extends TestCase {

	public TestConverterServlet (String name){
		super(name);
	}

  public void test_bad_parameter() throws Exception {
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    request.setupAddParameter("farenheitTemperature", "boo!");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
	assertEquals("<html><head><title>Bad Temperature</title>"
	+ "</head><body><h2>Need to enter a valid temperature!"
	+ "Got a NumberFormatException on boo!"
	+ "</h2></body></html>",response.getOutputStreamContents().trim());
  }
  
 
  public void test_null_parameter() throws Exception {
	TestingLabConverterServlet s = new TestingLabConverterServlet();
	MockHttpServletRequest request =
		new MockHttpServletRequest();
	MockHttpServletResponse response =
		new MockHttpServletResponse();
	String value = null;
	request.setupAddParameter("farenheitTemperature", value);
	response.setExpectedContentType("text/html");
	s.doGet(request,response);
	response.verify();
	assertEquals("<html><head><title>No Temperature</title></head><body><h2>Need to enter a temperature!</h2></body></html>",response.getOutputStreamContents().trim());
  }

  
  public void test_boil_mockon() throws Exception {
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
	CityTemperatureServiceProvider.turnOnMockMode();
    request.setupAddParameter("farenheitTemperature", "212");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
	String expected = "<html><head><title>Temperature Converter Result</title>"
	+ "</head><body><h2>212 Farenheit = 100 Celsius "
	+ "</h2>\n" 
	+ "<p><h3>The temperature in Austin is 451 degrees Farenheit</h3>\n"          + "</body></html>";
	assertEquals(expected,response.getOutputStreamContents().trim());
  }
 
    public static void main(String args[]) {	
		String[] testCaseName =
			{ TestConverterServlet.class.getName() };
		junit.textui.TestRunner.main(testCaseName);
	}

}
