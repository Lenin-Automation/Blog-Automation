
import java.awt.AWTException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class roughWork {

	private WebDriver driver;
	int k=0;
	int i;
	int j;
	int n;
	String BlogTitle;
	ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");

	ExtentReports extent = new ExtentReports();


	ExtentTest test1 = extent.createTest("Blog Automation");
	boolean ImpWait1;
	boolean ImpWait2;


	@BeforeTest
	public void setUp() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath+"//D//Chrome//chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();


		// Open Blog in browser
		// driver.get("https://blog.quantinsti.com/algorithmic-trading-different-geographies-video/");	
		// driver.get("https://blog.quantinsti.com/python-trading/");	
		//driver.get("https://blog.quantinsti.com/workshop-quantitative-trading-strategy/");
		//driver.get("https://blog.quantinsti.com/data-insights-trading-strategy-python-project-lokesh-kumar/");
		//driver.get("https://blog.quantinsti.com/pair-trading-strategy-backtesting-using-quantstrat/");
		 //driver.get("https://blog.quantinsti.com/will-blockchain-change-stock-markets/");
		 driver.get("https://blog.quantinsti.com/twap/");
		//driver.get("https://blog.quantinsti.com/page/66/");



		driver.manage().window().maximize();
	}

	@Test
	public void validateInvalidImages() throws InterruptedException, AWTException //, IOException, MalformedURLException 
	{

		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        List <WebElement>  allImages = driver.findElements(By.tagName("img"));

			Thread.sleep(4000);

        for(WebElement img :allImages){

            String imgSrc = img.getAttribute("naturalWidth");
            String imgSrcName = img.getAttribute("src");
            if(imgSrc.equals("0"))
            {
            	System.out.println(imgSrcName+ " is broken.");
            }
            else
            {
            	System.out.println(imgSrcName+ " is not broken.");
            }
        }


        // Broken Links


       List <WebElement>  allLinks = driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='single']/div[1]/div[2]")).findElements(By.tagName("a"));
       // List <WebElement>  allLinks = driver.findElements(By.tagName("a"));

      try
      {
            for(WebElement a:allLinks) 
            {
         String link = a.getAttribute("href");
         try
         {
         URL url = new URL(link);
         System.out.println(url);
         //Now we will be creating url connection and getting the response code
         HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
        // httpURLConnect.setRequestMethod("HEAD");
         httpURLConnect.setConnectTimeout(7000);
         httpURLConnect.setReadTimeout(7000);
         httpURLConnect.connect();

         if(httpURLConnect.getResponseCode()>=400 && httpURLConnect.getResponseCode()!=999)
         {
        	 //driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        	 driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
         	System.out.println(link+" - "+httpURLConnect.getResponseMessage()+" is a broken link"+" "+httpURLConnect.getResponseCode());
         }    

         //Fetching and Printing the response code obtained
         else{
             System.out.println(link+" - "+httpURLConnect.getResponseMessage());

         }
     }
         catch (Exception e) 
         {


         }

            }
       
      }
    catch (Exception e)
      {
    	
      }
      


            // Broken Video
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
         //   List <WebElement>  allVideos = driver.findElements(By.tagName("div"));           
           // List <WebElement>  allVideos = driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='single']/div[1]/div[2]")).findElements(By.tagName("iframe"));
            List <WebElement>  allVideos = driver.findElements(By.tagName("iframe"));
          try
            {
            for(WebElement v:allVideos) 
            	{
            		String vid = v.getAttribute("src");
            		String allow = v.getAttribute("allow");
            		String afs = v.getAttribute("allowfullscreen");
            		//try
            		//{

            		if( afs.equals("allowfullscreen") || afs.equals("true") || allow.equals("accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture")) 
            		//if(!allow.isEmpty()) 
            		{

            		//	URL nurl = new URL(vid);
            			try
            			{
            				Set<String> handles=driver.getWindowHandles();
            				for(String actual: handles) 
            				{

            					System.out.println("Actual : "+actual);
            					if(!actual.equalsIgnoreCase("NA"))
            					{
            						//Switch to the opened tab
            						driver.switchTo().window(actual); 
            						//opening the URL saved.
            						driver.get(vid);
            						Thread.sleep(3000);
            						List <WebElement>  allDiv = driver.findElements(By.tagName("div"));
            						{
            							for(WebElement d:allDiv) 
            							{
            								String cls = d.getAttribute("class");
            								if(cls.equals("ytp-error"))
            								{
            									System.out.println(vid+"Link is broken");
            									break;
            								}
            							}
            						}         			
            						Thread.sleep(2000);
            						driver.navigate().back();
            						Thread.sleep(2000);

            					}

            				}
            			}

            			catch (Exception e) 
            			{
            				e.printStackTrace();

            			}


            		}
            	}  	
            }
      	catch (Exception f) 
          {
      		
          }

		//driver.get("https://blog.quantinsti.com/page/60/");
		//driver.get("https://blog.quantinsti.com/");
		Thread.sleep(1500);





		// Capture the data of Sitemap
		/*driver.get("https://blog.quantinsti.com/sitemap-posts.xml");

		driver.manage().window().maximize();
		Thread.sleep(3000);
		boolean isFound = false;
		String SiteLink = driver.findElement(By.xpath("//body[1]/div[1]/table[1]")).getText();
		driver.navigate().back();






		//	String BlogTitle;
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
		for(j=1;j<100;j++)
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
					for (n=5;n<8;n++)
					{
						if(!driver.findElements(By.xpath("//body[1]/div["+n+"]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).isEmpty())
						{
							break;
						}
					}
					BlogTitle = driver.findElement(By.xpath("//body[1]/div["+n+"]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).getText();
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
					//test1.pass("H1 Tag exists in Blog");
				}
				else
				{
					System.out.println("H1 Tag is missing");
					test1.fail("H1 Tag is missing in Blog : "+BlogTitle);
				}


				if(h2present == true)
				{
					System.out.println("H2 Tag is present");
					//test1.pass("H2 Tag exists in Blog");
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
					//test1.pass("Rel Tag is Canonical in Blog");
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
					//test1.pass("Meta Description Tag Exists in Blog");
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
					//test1.pass("OG Image Tag Exists in Blog");
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
					//test1.pass("Twitter Card Tag Exists in Blog");
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
					//test1.pass("Twitter Title Tag Exists in Blog");
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
					//test1.pass("Twitter Description Tag Exists in Blog");
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
					//test1.pass("Twitter URL Tag Exists in Blog");

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
					//test1.pass("Twitter Image Tag Exists in Blog");
				}
				else
				{
					System.out.println("The Twitter Image Tag doesnt Exists");
					test1.fail("Twitter Image Tag is missing in Blog : "+BlogTitle);
				}




				// Broken Image
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

				List <WebElement>  allImages = driver.findElements(By.tagName("img"));

				Thread.sleep(4000);

				for(WebElement img :allImages){

					String imgSrc = img.getAttribute("naturalWidth");
					String imgSrcName = img.getAttribute("src");
					if(imgSrc.equals("0"))
					{

						test1.fail("The image is broken  : "+imgSrcName);
					}
					else
					{
						System.out.println(imgSrcName+ " is not broken.");
					}
				}


				// Broken Links

				List <WebElement>  allLinks = driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='single']/div[1]/div[2]")).findElements(By.tagName("a"));
				// List <WebElement>  allLinks = driver.findElements(By.tagName("a"));

				for(WebElement a:allLinks) 
				{
					String link = a.getAttribute("href");
					try
					{
						URL url = new URL(link);
						//Now we will be creating url connection and getting the response code
						HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
						httpURLConnect.setConnectTimeout(5000);
						httpURLConnect.connect();
						if(httpURLConnect.getResponseCode()>=400 && httpURLConnect.getResponseCode()!=999)
						{
							System.out.println(link+" - "+httpURLConnect.getResponseMessage()+"is a broken link");
							test1.fail("The Link is broken  : "+link+" "+httpURLConnect.getResponseCode());
						}    
						else		    
							//Fetching and Printing the response code obtained
						{
							System.out.println(link+" - "+httpURLConnect.getResponseMessage());

						}
					}catch (Exception e) 
					{
					}
				}



					// Broken Video
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);          
					List <WebElement>  allVideos = driver.findElements(By.tagName("iframe"));
					try 
					{
						
					
					for(WebElement v:allVideos) 
					{
						String vid = v.getAttribute("src");
						String allow = v.getAttribute("allow");
						String afs = v.getAttribute("allowfullscreen");
						try
						{
							if(allow.equals("accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture") || afs.equals("allowfullscreen") || afs.equals("true")) 
							{

								Set<String> handles=driver.getWindowHandles();
								for(String actual: handles) 
								{

									if(!actual.equalsIgnoreCase("NA"))
									{
										//Switch to the opened tab
										driver.switchTo().window(actual); 
										//opening the URL saved.
										driver.get(vid);
										Thread.sleep(3000);
										List <WebElement>  allDiv = driver.findElements(By.tagName("div"));
										{
											for(WebElement d:allDiv) 
											{
												String cls = d.getAttribute("class");
												if(cls.equals("ytp-error"))
												{
													test1.fail(vid+" : Video is broken");
													break;
												}
											}
										}         			
										Thread.sleep(2000);
										driver.navigate().back();
										Thread.sleep(2000);
									}
								}
							}
							Thread.sleep(2000);
						}
						catch (Exception e) 
						{


						}

					}
					}
					catch (Exception f) 
					{
						
					}

				



				//			Check in sitemap.xml

				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);    
				//        String Link = driver.getCurrentUrl();
				System.out.println(Link);
				if(SiteLink.contains(Link))
				{
					isFound = true;          		
				}        			


				if(isFound == true)
				{            		 
					System.out.println("The Blog "+BlogTitle+"  exists in Site Map"+"\n\n");
					//test1.pass("Blog Found in Site Map");
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

			je.executeScript("scroll(0, 250)");







			//   For Blog Number 4 , 5  &  6

			for(i=4; i<7; i++)
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

				boolean h3present = true;
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
				}

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
					//	test1.pass("H1 Tag exists in Blog");
				}
				else
				{
					System.out.println("H1 Tag is missing");
					test1.fail("H1 Tag is missing in Blog : "+BlogTitle);
				}


				if(h2present == true)
				{
					System.out.println("H2 Tag is present");
					//	test1.pass("H2 Tag exists in Blog");
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
					//	test1.pass("Rel Tag is Canonical in Blog");
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
					//test1.pass("Meta Description Tag Exists in Blog");
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
					//test1.pass("OG Image Tag Exists in Blog");
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
					//test1.pass("Twitter Card Tag Exists in Blog");
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
					//test1.pass("Twitter Title Tag Exists in Blog");
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
					//test1.pass("Twitter Description Tag Exists in Blog");
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
					//test1.pass("Twitter URL Tag Exists in Blog");

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
					//test1.pass("Twitter Image Tag Exists in Blog");
				}
				else
				{
					System.out.println("The Twitter Image Tag doesnt Exists");
					test1.fail("Twitter Image Tag is missing in Blog : "+BlogTitle);
				}




				// Broken Image
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

				List <WebElement>  allImages = driver.findElements(By.tagName("img"));
				Thread.sleep(4000);

				for(WebElement img :allImages){

					String imgSrc = img.getAttribute("naturalWidth");
					String imgSrcName = img.getAttribute("src");
					if(imgSrc.equals("0"))
					{

						test1.fail("The image is broken  : "+imgSrcName);
					}
					else
					{
						System.out.println(imgSrcName+ " is not broken.");
					}
				}




				// Broken Links

				List <WebElement>  allLinks = driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='single']/div[1]/div[2]")).findElements(By.tagName("a"));
				//List <WebElement>  allLinks = driver.findElements(By.tagName("a"));

				for(WebElement a:allLinks) 
				{
					String link = a.getAttribute("href");
					try
					{
						URL url = new URL(link);
						//Now we will be creating url connection and getting the response code
						HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
						httpURLConnect.setConnectTimeout(5000);
						httpURLConnect.connect();
						if(httpURLConnect.getResponseCode()>=400 && httpURLConnect.getResponseCode()!=999)
						{
							System.out.println(link+" - "+httpURLConnect.getResponseMessage()+"is a broken link");
							test1.fail("The Link is broken  : "+link+" "+httpURLConnect.getResponseCode());
						}    

						//Fetching and Printing the response code obtained
						else{
							System.out.println(link+" - "+httpURLConnect.getResponseMessage());

						}
					}
					catch (Exception e) 
					{
					}
				}




					// Broken Video
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);          
					List <WebElement>  allVideos = driver.findElements(By.tagName("iframe"));
					try
					{
					for(WebElement v:allVideos) 
					{
						int m=0;
						String vid = v.getAttribute("src");
						System.out.println("vid value is : "+vid);
						String allow = v.getAttribute("allow");
						String afs = v.getAttribute("allowfullscreen");
						try
						{
							if(allow.equals("accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture") || afs.equals("allowfullscreen") || afs.equals("true") && !vid.startsWith("//www.slideshare.net"))
							{

								Set<String> handles=driver.getWindowHandles();
								for(String actual: handles) 
								{
									System.out.println("Vid is = "+vid);

									if(!actual.equalsIgnoreCase("NA"))
									{
										//Switch to the opened tab
										driver.switchTo().window(actual); 
										//opening the URL saved.
										driver.get(vid);
								
										Thread.sleep(3000);
										List <WebElement>  allDiv = driver.findElements(By.tagName("div"));
										{
											for(WebElement d:allDiv) 
											{
												String cls = d.getAttribute("class");
											
												if(cls.equals("ytp-error"))
												{
													test1.fail(vid+" : Video is broken");
													break;
												}
											}
										}         			
										Thread.sleep(2000);
										driver.navigate().back();
										Thread.sleep(2000);
									}
									System.out.println(m++);
								}
							}
							Thread.sleep(2000);
						}catch (Exception e) 

						{


						}

					}
					}
					catch (Exception f) 
					{
						
					}

				





				//			Check in sitemap.xml


				if(SiteLink.contains(Link))
				{
					isFound = true;          		
				}        			


				if(isFound == true)
				{            		 
					System.out.println("The Blog "+BlogTitle+"  exists in Site Map"+"\n\n");
					//test1.pass("Blog Found in Site Map");
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
					for (n=5;n<8;n++)
					{
						if(!driver.findElements(By.xpath("//body[1]/div["+n+"]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).isEmpty())
						{
							break;
						}
					}
					BlogTitle = driver.findElement(By.xpath("//body[1]/div["+n+"]/main[1]/div[1]/div[2]/article[1]/header[1]/div[1]/h1[1]")).getText();
				}



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
					//test1.pass("H1 Tag exists in Blog");
				}
				else
				{
					System.out.println("H1 Tag is missing");
					test1.fail("H1 Tag is missing in Blog : "+BlogTitle);
				}


				if(h2present == true)
				{
					System.out.println("H2 Tag is present");
					//test1.pass("H2 Tag exists in Blog");
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
					//test1.pass("Rel Tag is Canonical in Blog");
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
					//test1.pass("Meta Description Tag Exists in Blog");
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
					//test1.pass("OG Image Tag Exists in Blog");
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
					//test1.pass("Twitter Card Tag Exists in Blog");
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
					//test1.pass("Twitter Title Tag Exists in Blog");
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
					//test1.pass("Twitter Description Tag Exists in Blog");
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
					//test1.pass("Twitter URL Tag Exists in Blog");

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
					//test1.pass("Twitter Image Tag Exists in Blog");
				}
				else
				{
					System.out.println("The Twitter Image Tag doesnt Exists");
					test1.fail("Twitter Image Tag is missing in Blog : "+BlogTitle);
				}



				// Broken Image
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

				List <WebElement>  allImages = driver.findElements(By.tagName("img"));
				Thread.sleep(4000);

				for(WebElement img :allImages){

					String imgSrc = img.getAttribute("naturalWidth");
					String imgSrcName = img.getAttribute("src");
					if(imgSrc.equals("0"))
					{

						test1.fail("The image is broken  : "+imgSrcName);
					}
					else
					{
						System.out.println(imgSrcName+ " is not broken.");
					}
				}



				// Broken Links

				List <WebElement>  allLinks = driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='single']/div[1]/div[2]")).findElements(By.tagName("a"));
				// List <WebElement>  allLinks = driver.findElements(By.tagName("a"));

				for(WebElement a:allLinks) 
				{
					String link = a.getAttribute("href");
					try
					{
						URL url = new URL(link);
						//Now we will be creating url connection and getting the response code
						HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
						httpURLConnect.setConnectTimeout(5000);
						httpURLConnect.connect();
						if(httpURLConnect.getResponseCode()>=400 && httpURLConnect.getResponseCode()!=999)
						{
							System.out.println(link+" - "+httpURLConnect.getResponseMessage()+"is a broken link");
							test1.fail("The Link is broken  : "+link+" "+httpURLConnect.getResponseCode());
						}    

						//Fetching and Printing the response code obtained
						else{
							System.out.println(link+" - "+httpURLConnect.getResponseMessage());

						}
					}catch (Exception e) 
					{
					}
				}



					// Broken Video
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);          
					List <WebElement>  allVideos = driver.findElements(By.tagName("iframe"));
					try
					{
					for(WebElement v:allVideos) 
					{
						String vid = v.getAttribute("src");

						String allow = v.getAttribute("allow");
						String afs = v.getAttribute("allowfullscreen");
						try
						{
							if(allow.equals("accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture") || afs.equals("allowfullscreen") || afs.equals("true") && !vid.contains("slideshow"))
							{

								Set<String> handles=driver.getWindowHandles();
								for(String actual: handles) 
								{

									if(!actual.equalsIgnoreCase("NA"))
									{
										//Switch to the opened tab
										driver.switchTo().window(actual); 
										//opening the URL saved.

										driver.get(vid);
										Thread.sleep(3000);
										List <WebElement>  allDiv = driver.findElements(By.tagName("div"));
										{
											for(WebElement d:allDiv) 
											{
												String cls = d.getAttribute("class");
												if(cls.equals("ytp-error"))
												{             			
													test1.fail(vid+" : Video is broken");
													break;
												}
											}
										}         			
										Thread.sleep(2000);
										driver.navigate().back();
										Thread.sleep(2000);
									}
								}
							}
					
						}catch (Exception e) 
						{



						}
					}
					
				
					}
					catch (Exception f) 
					{
						
					}



				



				//			Check in sitemap.xml

				
				if(SiteLink.contains(Link))
				{
					isFound = true;        
				
				}        			


				if(isFound == true)
				{            		 
					System.out.println("The Blog "+BlogTitle+"  exists in Site Map"+"\n\n");
					//test1.pass("Blog Found in Site Map");
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
		}*/
	}

	

	@AfterTest
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}



}
