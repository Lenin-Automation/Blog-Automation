import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class multipleBlogs {


	public static void main(String[] args) throws InterruptedException {
		boolean ImpWait1;
		String BlogTitle;
		int n;
		// //body/div[@id='wrapper']/main[@id='home']/div[3]/div[1]/div[1]/article[1]
		
		ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		
		ExtentTest test1 = extent.createTest("Blog Automation");
		  
		
		test1.generateLog(Status.INFO, "Open the Chrome Broswer");
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		
		System.setProperty("webdriver.chrome.driver", projectPath+"/D/Chrome/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		
		// Open Blog in browser
		//driver.get("https://blog.quantinsti.com/");
		//driver.get("https://blog.quantinsti.com/page/66/");
		driver.get("https://blog.quantinsti.com/");
		
		
		// Maximize the Window
		driver.manage().window().maximize();
		Thread.sleep(3000);
		test1.pass("Opened the browser successfully");
		
		// Cancel the Popup
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		try
		{

			driver.findElement(By.id("onesignal-slidedown-cancel-button"));
			ImpWait1 = true;
		}
		catch(NoSuchElementException e)
		{
			ImpWait1 = false;
			test1.fail("Couldn't find the header notification element");
		}

		if(ImpWait1==true)
		{
			driver.findElement(By.id("onesignal-slidedown-cancel-button")).click();
			test1.pass("Clicked on the popup");
		}
		Thread.sleep(1500);
		int i;
		int j;
		int k=0;
		
		 
		
		// Cancel the header notification if it exists
	/*	WebElement header = driver.findElement(By.id("close"));
		if(header.isEnabled())
		{
			header.click();
		}*/
		
		
		// Capture the data of Sitemap
		 driver.get("https://blog.quantinsti.com/sitemap-posts.xml");
		 driver.manage().window().maximize();
		 Thread.sleep(3000);
		 boolean isFound = false;
		 String SiteLink = driver.findElement(By.xpath("//body[1]/div[1]/table[1]")).getText();
	//	 System.out.println(SiteLink);
		 driver.navigate().back();
		
		 // Try Accessing Robots.txt
		 Thread.sleep(1500);
		 driver.get("https://blog.quantinsti.com/robots.txt");
		 Thread.sleep(2000);
		 boolean ro1 = true;
		 try
		 {
			 driver.findElement(By.xpath("//body[1]/pre[1]")).getAttribute("style");
			 ro1 = true;
		 }
		 catch (NoSuchElementException e) 
		 {
			 ro1 = false;
		 }
		 
		 if(ro1 == true)
		 {
		 String robotTitle = driver.findElement(By.xpath("//body[1]/pre[1]")).getAttribute("style");
		 if (robotTitle.equals("overflow-wrap: break-word; white-space: pre-wrap;"))
		 {
			 test1.pass("Successuffy Accessed Robot.txt link");
		 }
		 else
		 {
			 test1.fail("Faled to Accessed Robot.txt link");
		 }
		 }
		 else
		 {
			 System.out.println("The Link Doesn't Exsist");
			 test1.fail("Faled to Accessed Robot.txt link");
		 }
		 driver.navigate().back();
		 Thread.sleep(1500);
		 
		 
		 test1.info("Start Testing Each Blog");
		// Start with Blog Testing
		JavascriptExecutor je = (JavascriptExecutor) driver;
		for(j=1;j<3;j++)
		{
			
			
			
		//   For Blog Number 1 , 2  &  3
					for(i=1; i<4; i++)
					{

						String s = "//body/div[@id='wrapper']/main[@id='home']/div[3]/div[1]/div[1]/article["+i+"]/header[1]/h4[1]/a[1]";
						List<WebElement> myLink = driver.findElements(By.xpath(s));
						if (myLink.size()==0)
						{
							break;
						}
						else
						{
							driver.findElement(By.xpath(s)).click();
						}
						
						if(!driver.findElements(By.xpath("//body[1]/div[4]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).isEmpty())
						{
							BlogTitle = driver.findElement(By.xpath("//body[1]/div[4]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).getText();
						}
						else
						{
							BlogTitle = driver.findElement(By.xpath("//body[1]/div[5]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).getText();
						}
						
						//BlogTitle = driver.findElement(By.xpath("//body[1]/div[4]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).getText();		
						test1.info(BlogTitle);
						boolean h1present;
						boolean h2present;
						String Link = driver.getCurrentUrl();

						//				 Check if H1 & H2 Tags are Present or not
						try {
							driver.findElement(By.tagName("h1"));
							h1present = true;
						} catch (NoSuchElementException e) {
							h1present = false;
						}


						try {
							driver.findElement(By.tagName("h2"));
							h2present = true;
						} catch (NoSuchElementException e) {
							h2present = false;
						}

						if(h1present == true)
						{
							System.out.println("H1 Tag is present");
							test1.pass("H1 Tag exists in Blog");
						}
						else
						{
							System.out.println("H1 Tag is missing");
							test1.fail("H1 Tag is missing in Blog : "+BlogTitle);
						}


						if(h2present == true)
						{
							System.out.println("H2 Tag is present");
							test1.pass("H2 Tag exists in Blog");
						}
						else
						{
							System.out.println("H2 Tag is missing");
							test1.fail("H2 Tag is missing in Blog : "+BlogTitle);
						}




						//			Check rel is Canonical
						try
						{
							driver.findElement(By.tagName("link")).getAttribute("rel").equals("canonical");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}

						if(h1present == true)
						{

							System.out.println("rel Tag is present and it is canonical");
							test1.pass("Rel Tag is Canonical in Blog");
						}
						else
						{
							System.out.println("The canonical rel Tag is missing");
							test1.fail("Rel Tag is Not Canonical in Blog : "+BlogTitle);
						}




						//			Check the Meta Description
						try
						{
							driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}

						if(h1present == true)
						{


							System.out.println("The Meta Description Tags exists and its content is "+ driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content"));
							test1.pass("Meta Description Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Meta Description or Meta Content Tags are missing");
							test1.fail("Meta Description Tag is missing in Blog : "+BlogTitle);
						}


						//			Check the OG Image
						try
						{
							driver.findElement(By.xpath("//meta[@property='og:image']"));
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}

						if(h1present == true)
						{
							System.out.println("The OG image Exists and its content is "+ driver.findElement(By.xpath("//meta[@property='og:image']")).getAttribute("content"));
							test1.pass("OG Image Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Blog Does not have OG Image and the OG Image Content tags");
							test1.fail("OG Image Tag is missing in Blog : "+BlogTitle);
						}





						//			Check the Twitter Card Meta Tags
						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:card']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter Card Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:card']")).getAttribute("content"));
							test1.pass("Twitter Card Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Twitter Card Tag doesnt Exists");
							test1.fail("Twitter Card Tag is missing in Blog : "+BlogTitle);
						}




						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:title']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter Title Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:title']")).getAttribute("content"));
							test1.pass("Twitter Title Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Twitter Title Tag doesnt Exists");
							test1.fail("Twitter Title Tag is missing in Blog : "+BlogTitle);
						}




						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:description']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter Description Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:description']")).getAttribute("content"));
							test1.pass("Twitter Description Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Twitter Description Tag doesnt Exists");
							test1.fail("Twitter Description Tag is missing in Blog : "+BlogTitle);
						}



						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:url']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter URL Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:url']")).getAttribute("content"));
							test1.pass("Twitter URL Tag Exists in Blog");

						}
						else
						{
							System.out.println("The Twitter URL Tag doesnt Exists");
							test1.fail("Twitter URL Tag is missing in Blog : "+BlogTitle);
						}




						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:image']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter Image Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:image']")).getAttribute("content"));
							test1.pass("Twitter Image Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Twitter Image Tag doesnt Exists");
							test1.fail("Twitter Image Tag is missing in Blog : "+BlogTitle);
						}


						
						
						
						

						//			Check in sitemap.xml


						if(SiteLink.contains(Link))
						{
							isFound = true;          		
						}        			


						if(isFound == true)
						{            		 
							System.out.println("The Blog "+BlogTitle+"  exists in Site Map"+"\n\n");
							test1.pass("Blog Found in Site Map");
						}
						else
						{
							System.out.println("The Blog " +BlogTitle+" is missing in Site Map"+"\n\n");   
							test1.fail("Blog Missing in Site Map : "+BlogTitle);
						}


						k++;
						Thread.sleep(3000);
						driver.navigate().back();
						Thread.sleep(2000);
					}
					
					Thread.sleep(2000);
				//	JavascriptExecutor je = (JavascriptExecutor) driver;
				//	je.executeScript("scroll(0, 500)");








					//   For Blog Number 4 , 5  &  6
					
					for(i=4; i<7; i++)
					{
						JavascriptExecutor jes = (JavascriptExecutor) driver;
						jes.executeScript("scroll(0, 500)");
						
						String s = "//body/div[@id='wrapper']/main[@id='home']/div[3]/div[1]/div[1]/article["+i+"]/header[1]/h4[1]/a[1]";;
						List<WebElement> myLink = driver.findElements(By.xpath(s));
						if (myLink.size()==0)
						{
							break;
						}
						else
						{
							driver.findElement(By.xpath(s)).click();
						}
				
						/*boolean h3present = true;
						try {
							driver.findElement(By.xpath("//body[1]/div[4]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]"));
							h3present = true;
						} catch (NoSuchElementException e) {
							h3present = false;
						}
						
						if(h3present == true)
						{

						BlogTitle = driver.findElement(By.xpath("//body[1]/div[4]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).getText();	
						test1.info(BlogTitle);
						}
						else
						{
							BlogTitle = "not found";
						}*/
						
						if(!driver.findElements(By.xpath("//body[1]/div[4]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).isEmpty())
						{
							BlogTitle = driver.findElement(By.xpath("//body[1]/div[4]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).getText();
						}
						else
						{
							for (n=5;n<8;n++)
							{
								if(!driver.findElements(By.xpath("//body[1]/div["+n+"]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).isEmpty())
								{
								 break;
								}
							}
							BlogTitle = driver.findElement(By.xpath("//body[1]/div["+n+"]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).getText();
						}
						

						boolean h1present;
						boolean h2present;
						String Link = driver.getCurrentUrl();
						
						
						

						//				 Check if H1 & H2 Tags are Present or not
						try {
							driver.findElement(By.tagName("h1"));
							h1present = true;
						} catch (NoSuchElementException e) {
							h1present = false;
						}


						try {
							driver.findElement(By.tagName("h2"));
							h2present = true;
						} catch (NoSuchElementException e) {
							h2present = false;
						}

						if(h1present == true)
						{
							System.out.println("H1 Tag is present");
							test1.pass("H1 Tag exists in Blog");
						}
						else
						{
							System.out.println("H1 Tag is missing");
							test1.fail("H1 Tag is missing in Blog : "+BlogTitle);
						}


						if(h2present == true)
						{
							System.out.println("H2 Tag is present");
							test1.pass("H2 Tag exists in Blog");
						}
						else
						{
							System.out.println("H2 Tag is missing");
							test1.fail("H2 Tag is missing in Blog : "+BlogTitle);
						}




						//			Check rel is Canonical
						try
						{
							driver.findElement(By.tagName("link")).getAttribute("rel").equals("canonical");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}

						if(h1present == true)
						{

							System.out.println("rel Tag is present and it is canonical");
							test1.pass("Rel Tag is Canonical in Blog");
						}
						else
						{
							System.out.println("The canonical rel Tag is missing");
							test1.fail("Rel Tag is Not Canonical in Blog : "+BlogTitle);
						}



						

						//			Check the Meta Description
						try
						{
							driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}

						if(h1present == true)
						{


							System.out.println("The Meta Description Tags exists and its content is "+ driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content"));
							test1.pass("Meta Description Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Meta Description or Meta Content Tags are missing");
							test1.fail("Meta Description Tag is missing in Blog : "+BlogTitle);
						}

						
						
						

						//			Check the OG Image
						try
						{
							driver.findElement(By.xpath("//meta[@property='og:image']"));
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}

						if(h1present == true)
						{
							System.out.println("The OG image Exists and its content is "+ driver.findElement(By.xpath("//meta[@property='og:image']")).getAttribute("content"));
							test1.pass("OG Image Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Blog Does not have OG Image and the OG Image Content tags");
							test1.fail("OG Image Tag is missing in Blog : "+BlogTitle);
						}





						//			Check the Twitter Card Meta Tags
						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:card']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter Card Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:card']")).getAttribute("content"));
							test1.pass("Twitter Card Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Twitter Card Tag doesnt Exists");
							test1.fail("Twitter Card Tag is missing in Blog : "+BlogTitle);
						}




						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:title']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter Title Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:title']")).getAttribute("content"));
							test1.pass("Twitter Title Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Twitter Title Tag doesnt Exists");
							test1.fail("Twitter Title Tag is missing in Blog : "+BlogTitle);
						}




						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:description']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter Description Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:description']")).getAttribute("content"));
							test1.pass("Twitter Description Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Twitter Description Tag doesnt Exists");
							test1.fail("Twitter Description Tag is missing in Blog : "+BlogTitle);
						}



						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:url']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter URL Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:url']")).getAttribute("content"));
							test1.pass("Twitter URL Tag Exists in Blog");

						}
						else
						{
							System.out.println("The Twitter URL Tag doesnt Exists");
							test1.fail("Twitter URL Tag is missing in Blog : "+BlogTitle);
						}




						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:image']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter Image Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:image']")).getAttribute("content"));
							test1.pass("Twitter Image Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Twitter Image Tag doesnt Exists");
							test1.fail("Twitter Image Tag is missing in Blog : "+BlogTitle);
						}

						
						


						//			Check in sitemap.xml


						if(SiteLink.contains(Link))
						{
							isFound = true;          		
						}        			


						if(isFound == true)
						{            		 
							System.out.println("The Blog "+BlogTitle+"  exists in Site Map"+"\n\n");
							test1.pass("Blog Found in Site Map");
						}
						else
						{
							System.out.println("The Blog " +BlogTitle+" is missing in Site Map"+"\n\n");   
							test1.fail("Blog Missing in Site Map : "+BlogTitle);
						}


						k++;
						Thread.sleep(3000);
						driver.navigate().back();
						Thread.sleep(2000);
					}
					je.executeScript("scroll(0, 700)");







					//   For Blog Number 7 , 8  &  9
					
					for(i=7; i<10; i++)
					{
						String s = "//body/div[@id='wrapper']/main[@id='home']/div[3]/div[1]/div[1]/article["+i+"]/header[1]/h4[1]/a[1]";
						//boolean a1;
						//boolean a2;
						List<WebElement> myLink = driver.findElements(By.xpath(s));
						if (myLink.size()==0)
						{
							break;
						}
						else
						{
							driver.findElement(By.xpath(s)).click();
						}
						
						if(!driver.findElements(By.xpath("//body[1]/div[4]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).isEmpty())
						{
							BlogTitle = driver.findElement(By.xpath("//body[1]/div[4]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).getText();
						}
						else
						{
							BlogTitle = driver.findElement(By.xpath("//body[1]/div[5]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).getText();
						}
						
						
						/*

						try
						{
						driver.findElement(By.xpath("//body[1]/div[4]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]"));
						a1 = true;
						}
						catch(NoSuchElementException e)
						{
							a1 = false;
						}
						try
						{
						driver.findElement(By.xpath("//body[1]/div[5]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]"));	
						a2 = true;
						}
						catch(NoSuchElementException e)
						{
							a2 = false;
						}
						if(a1 == true)
						{
							BlogTitle = driver.findElement(By.xpath("//body[1]/div[4]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).getText();
						}
						else if (a2 == true)
						{
							BlogTitle = driver.findElement(By.xpath("//body[1]/div[5]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).getText();
						}
						*/
						
						test1.info(BlogTitle);
						boolean h1present;
						boolean h2present;
						String Link = driver.getCurrentUrl();
						
						
						
						

						//				 Check if H1 & H2 Tags are Present or not
						try {
							driver.findElement(By.tagName("h1"));
							h1present = true;
						} catch (NoSuchElementException e) {
							h1present = false;
						}


						try {
							driver.findElement(By.tagName("h2"));
							h2present = true;
						} catch (NoSuchElementException e) {
							h2present = false;
						}

						if(h1present == true)
						{
							System.out.println("H1 Tag is present");
							test1.pass("H1 Tag exists in Blog");
						}
						else
						{
							System.out.println("H1 Tag is missing");
							test1.fail("H1 Tag is missing in Blog : "+BlogTitle);
						}


						if(h2present == true)
						{
							System.out.println("H2 Tag is present");
							test1.pass("H2 Tag exists in Blog");
						}
						else
						{
							System.out.println("H2 Tag is missing");
							test1.fail("H2 Tag is missing in Blog : "+BlogTitle);
						}


						


						//			Check rel is Canonical
									
						try
						{
							driver.findElement(By.tagName("link")).getAttribute("rel").equals("canonical");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}

						if(h1present == true)
						{

							System.out.println("rel Tag is present and it is canonical");
							test1.pass("Rel Tag is Canonical in Blog");
						}
						else
						{
							System.out.println("The canonical rel Tag is missing");
							test1.fail("Rel Tag is Not Canonical in Blog : "+BlogTitle);
						}



						
						
						

						//			Check the Meta Description
						
						try
						{
							driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}

						if(h1present == true)
						{


							System.out.println("The Meta Description Tags exists and its content is "+ driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content"));
							test1.pass("Meta Description Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Meta Description or Meta Content Tags are missing");
							test1.fail("Meta Description Tag is missing in Blog : "+BlogTitle);
						}


						
						
						
						//			Check the OG Image
						
						try
						{
							driver.findElement(By.xpath("//meta[@property='og:image']"));
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}

						if(h1present == true)
						{
							System.out.println("The OG image Exists and its content is "+ driver.findElement(By.xpath("//meta[@property='og:image']")).getAttribute("content"));
							test1.pass("OG Image Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Blog Does not have OG Image and the OG Image Content tags");
							test1.fail("OG Image Tag is missing in Blog : "+BlogTitle);
						}


						
						
						



						//			Check the Twitter Card Meta Tags
						
						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:card']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter Card Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:card']")).getAttribute("content"));
							test1.pass("Twitter Card Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Twitter Card Tag doesnt Exists");
							test1.fail("Twitter Card Tag is missing in Blog : "+BlogTitle);
						}




						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:title']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter Title Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:title']")).getAttribute("content"));
							test1.pass("Twitter Title Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Twitter Title Tag doesnt Exists");
							test1.fail("Twitter Title Tag is missing in Blog : "+BlogTitle);
						}




						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:description']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter Description Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:description']")).getAttribute("content"));
							test1.pass("Twitter Description Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Twitter Description Tag doesnt Exists");
							test1.fail("Twitter Description Tag is missing in Blog : "+BlogTitle);
						}



						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:url']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter URL Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:url']")).getAttribute("content"));
							test1.pass("Twitter URL Tag Exists in Blog");

						}
						else
						{
							System.out.println("The Twitter URL Tag doesnt Exists");
							test1.fail("Twitter URL Tag is missing in Blog : "+BlogTitle);
						}




						try
						{
							driver.findElement(By.xpath("//meta[@name='twitter:image']")).getAttribute("content");
							h1present = true;
						}
						catch (NoSuchElementException e) {
							h1present = false;
						}
						if(h1present == true)
						{
							System.out.println("Twitter Image Exists in the Blog and its content is "+ driver.findElement(By.xpath("//meta[@name='twitter:image']")).getAttribute("content"));
							test1.pass("Twitter Image Tag Exists in Blog");
						}
						else
						{
							System.out.println("The Twitter Image Tag doesnt Exists");
							test1.fail("Twitter Image Tag is missing in Blog : "+BlogTitle);
						}


						
						
						

						//			Check in sitemap.xml

						if(SiteLink.contains(Link))
						{
							isFound = true;          		
						}        			


						if(isFound == true)
						{            		 
							System.out.println("The Blog "+BlogTitle+"  exists in Site Map"+"\n\n");
							test1.pass("Blog Found in Site Map");
						}
						else
						{
							System.out.println("The Blog " +BlogTitle+" is missing in Site Map"+"\n\n");   
							test1.fail("Blog Missing in Site Map : "+BlogTitle);
						}

						k++;
						Thread.sleep(3000);
						driver.navigate().back();
						Thread.sleep(2000);
					}
			je.executeScript("scroll(0, 1000)");
			List<WebElement> Next = driver.findElements(By.xpath("//a[contains(text(),'Next')]"));
			if (Next.size()==0)
			{
				break;
			}
			else
			{
				driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
			}
			Thread.sleep(3000);
			}
		System.out.println("Each and every blog has been visited");
		System.out.println("Total Number of blogs are " + k);
		test1.info("Test Completed");
		extent.flush();
			driver.close();
		}

	}