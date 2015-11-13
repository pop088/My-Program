package guava;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Example  {
    public static void main(String[] args) {
    	testCase1();
    	testCase2();
    	testCase3();
    	testCase4();
    	testCase5();
    	 testCase6();
    	 testCase7();
    }
    

	
    public static void testCase1(){
    	System.out.println("TEST valid users");
    	WebDriver driver1 = new HtmlUnitDriver();
    	driver1.get("http://apt-public.appspot.com/testing-lab-login.html");
        WebElement username = driver1.findElement(By.name("userId"));
        WebElement pswd = driver1.findElement(By.name("userPassword"));
        username.clear();
        pswd.clear();
        username.sendKeys("andy");
        pswd.sendKeys("apple");
        username.submit();
        
        if (driver1.getTitle().equals("Online temperature conversion calculator")){
        	System.out.println("Login successful andy");
        }
        else{
        	System.out.println("Login failed andy");
        }
        
        WebDriver driver2 = driver1;
    	driver2.get("http://apt-public.appspot.com/testing-lab-login.html");
        WebElement username2 = driver2.findElement(By.name("userId"));
        WebElement pswd2 = driver2.findElement(By.name("userPassword"));
        username2.clear();
        pswd2.clear();
        username2.sendKeys("bob");
        pswd2.sendKeys("bathtub");
        username2.submit();
        
        if (driver2.getTitle().equals("Online temperature conversion calculator")){
        	System.out.println("Login successful bob");
        }
        else{
        	System.out.println("Login failed bob");
        }
        
        WebDriver driver3 = driver2;
    	driver3.get("http://apt-public.appspot.com/testing-lab-login.html");
        WebElement username3 = driver3.findElement(By.name("userId"));
        WebElement pswd3 = driver3.findElement(By.name("userPassword"));
        username3.clear();
        pswd3.clear();
        username3.sendKeys("charley");
        pswd3.sendKeys("china");
        username3.submit();
        
        if (driver3.getTitle().equals("Online temperature conversion calculator")){
        	System.out.println("Login successful charley");
        }
        else{
        	System.out.println("Login failed charley");
        }
        
        WebDriver driver4 = driver3;
    	driver4.get("http://apt-public.appspot.com/testing-lab-login.html");
        WebElement username4 = driver4.findElement(By.name("userId"));
        WebElement pswd4 = driver4.findElement(By.name("userPassword"));
        username4.clear();
        pswd4.clear();
        username4.sendKeys("gagag");
        pswd4.sendKeys("lululu");
        username4.submit();
        
        if (driver4.getTitle().equals("Bad Login")){
        	System.out.println("Login failed gagag");
        }
        else{
        	System.out.println("Login successful gagag");
        }
        driver4.close();
    }  
    
    public static void testCase2(){
    	System.out.println("\n\nTEST case-sensitivity");
    	WebDriver driver1 = new HtmlUnitDriver();
    	driver1.get("http://apt-public.appspot.com/testing-lab-login.html");
        WebElement uname = driver1.findElement(By.name("userId"));
        WebElement pswd = driver1.findElement(By.name("userPassword"));
        uname.clear();
        pswd.clear();
        uname.sendKeys("ANDY");
        pswd.sendKeys("apple");
        uname.submit();
        
        if (driver1.getTitle().equals("Online temperature conversion calculator")){
        	System.out.println("Login successful for AnDy");
        }
        else{
        	System.out.println("Login failed for andy");
        }
        
        WebDriver driver2 = driver1;
    	driver2.get("http://apt-public.appspot.com/testing-lab-login.html");
        WebElement uname2 = driver2.findElement(By.name("userId"));
        WebElement pswd2 = driver2.findElement(By.name("userPassword"));
        uname2.clear();
        pswd2.clear();
        uname2.sendKeys(" bob");
        pswd2.sendKeys("bathtub  ");
        uname2.submit();
        
        if (driver2.getTitle().equals("Online temperature conversion calculator")){
        	System.out.println("Login successful for bob bathtub with space");
        }
        else{
        	System.out.println("Login failed for bob");
        }
        
        WebDriver driver3 = driver2;
    	driver3.get("http://apt-public.appspot.com/testing-lab-login.html");
        WebElement uname3 = driver3.findElement(By.name("userId"));
        WebElement pswd3 = driver3.findElement(By.name("userPassword"));
        uname3.clear();
        pswd3.clear();
        uname3.sendKeys("charley");
        pswd3.sendKeys("CHINA");
        uname3.submit();
        
        if (driver3.getTitle().equals("Bad Login")){
        	System.out.println("Login failed for charley;check password");
        }
        else{
        	System.out.println("Login successful for charley ");
        }
        driver3.close();
     }
    
    public static void testCase3(){
    	
    	System.out.println("\n\nTEST lockout");
    	WebDriver driver1 = new HtmlUnitDriver();
    	int count = 0;
    	long end = System.currentTimeMillis() + 10000;
    	driver1.get("http://apt-public.appspot.com/testing-lab-login.html");
    	WebElement uname = driver1.findElement(By.name("userId"));
    	WebElement pswd = driver1.findElement(By.name("userPassword"));
        uname.clear();
        pswd.clear();
        uname.sendKeys("tatat");
        pswd.sendKeys("tatat");
        uname.submit();
        
        if (driver1.getTitle().equals("Bad Login")){
        	count = count +1;
        	System.out.println("bad login1");
        }
        
        WebDriver driver2 = driver1;
    	driver2.get("http://apt-public.appspot.com/testing-lab-login.html");
        WebElement uname2 = driver2.findElement(By.name("userId"));
        WebElement pswd2 = driver2.findElement(By.name("userPassword"));
        uname2.clear();
        pswd2.clear();
        uname2.sendKeys("arara");
        pswd2.sendKeys("arara");
        uname2.submit();
        
        if (driver1.getTitle().equals("Bad Login")){
        	count = count +1;
        	System.out.println("bad login2");
        }
        
        WebDriver driver3 = driver2;
    	driver3.get("http://apt-public.appspot.com/testing-lab-login.html");
        WebElement uname3 = driver3.findElement(By.name("userId"));
        WebElement pswd3 = driver3.findElement(By.name("userPassword"));
        uname3.clear();
        pswd3.clear();
        uname3.sendKeys("ararar");
        pswd3.sendKeys("aeaeae");
        uname3.submit();
        
        if (driver1.getTitle().equals("Bad Login")){
        	count = count +1;
        	System.out.println("bad login3");
        }
        
        if (count==3 && System.currentTimeMillis() < end){
        	System.out.println("LOCKOUT TEST");
        }
        
        WebDriver driver4 = driver3;
    	driver4.get("http://apt-public.appspot.com/testing-lab-login.html");
        WebElement uname4 = driver4.findElement(By.name("userId"));
        WebElement pswd4 = driver4.findElement(By.name("userPassword"));
        uname4.clear();
        pswd4.clear();
        uname4.sendKeys("user4");
        pswd4.sendKeys("user4");
        uname4.submit();
        
        if (driver4.getTitle().equals("Bad Login")){
        	count = count +1;
        	System.out.println("Test failed No lockout");
        }
        driver4.close();
    }
    
    public static void testCase4(){
    	System.out.println("\n\nTEST results precision");
    	WebDriver driver1 = new HtmlUnitDriver();
    	driver1.get("http://apt-public.appspot.com/testing-lab-calculator.html");
    	WebElement temp = driver1.findElement(By.name("farenheitTemperature"));
    	temp.clear();
    	temp.sendKeys("119");
    	temp.submit();
    	WebElement celtemp = driver1.findElement(By.tagName("h2"));
    	String[] ctemp = celtemp.getText().split("\\=");
    	String[] cval = ctemp[1].split("\\s+");
    	String[] finalcval = cval[1].split("\\.");
    	if (finalcval.length==2){
    		System.out.println("Test passed for double precision");
    	}
    	
    	WebDriver driver2 = driver1;
    	driver2.get("http://apt-public.appspot.com/testing-lab-calculator.html");
    	WebElement temp2 = driver2.findElement(By.name("farenheitTemperature"));
    	
    	temp2.clear();
    	temp2.sendKeys("467");
    	temp2.submit();
    	WebElement celtemp2 = driver2.findElement(By.tagName("h2"));
    	String[] ctemp2 = celtemp2.getText().split("\\=");
    	String[] cval2 = ctemp2[1].split("\\s+");
    	String[] finalval = cval2[1].split("\\.");
    	if (finalval.length==2){
    		System.out.println("Test failed Converted Temperatures out of [0-212] range have double precision");
    	}
    	driver2.close();
   }
   
    public static void testCase5(){
    	System.out.println("\n\nTEST input as floating point numbers");
    	WebDriver driver1 = new HtmlUnitDriver();
    	driver1.get("http://apt-public.appspot.com/testing-lab-calculator.html");
    	WebElement temp = driver1.findElement(By.name("farenheitTemperature"));
    	temp.clear();
    	temp.sendKeys("97");
    	temp.submit();
    	if (driver1.getTitle().equals("Temperature Converter Result")){
    		System.out.println("Test passed: input is floating point numbers");
    	}
    	
    	WebDriver driver3 = driver1;
    	driver3.get("http://apt-public.appspot.com/testing-lab-calculator.html");
    	WebElement temp2 = driver3.findElement(By.name("farenheitTemperature"));
    	temp2.clear();
    	temp2.sendKeys("9.73E2");
    	temp2.submit();
    	if (driver3.getTitle().equals("Temperature Converter Result")){
    		System.out.println("Test failed 9.73e2 works");
    	}
    	driver3.close();
    }
    
    public static void testCase6(){
    	System.out.println("\n\nTEST invalid input temperatures");
    	WebDriver driver1 = new HtmlUnitDriver();
    	
    	driver1.get("http://apt-public.appspot.com/testing-lab-calculator.html");
    	WebElement temp1 = driver1.findElement(By.name("farenheitTemperature"));
    	temp1.clear();
    	temp1.sendKeys("gagaga");
    	temp1.submit();
    	if (driver1.getTitle().equals("Bad Temperature")){
    		System.out.println("Test passed for invalid input");
    	}
    	driver1.close();
    }
    
    public static void testCase7(){
    	System.out.println("\n\nTEST case insensitivity of “farenheitTemperature\"");
    	 WebDriver driver1 = new HtmlUnitDriver();
    	 driver1.get("http://apt-public.appspot.com/testing-lab-conversion?FARENHEITTEMPERATURE=200");
    	 if (driver1.getTitle().equals("No Temperature")){
    		 System.out.println("Test failed:farenheitTemperature is case sensitive");
    	 }
    	 driver1.close();
    }   
}
