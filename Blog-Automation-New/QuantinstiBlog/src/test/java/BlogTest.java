import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class BlogTest {

	public static void main(String[] args) throws InterruptedException {
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		boolean ImpWait1 = true;
		
		System.setProperty("webdriver.chrome.driver", projectPath+"//Driver//Chrome//chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		
		// Open Blog in browser
		driver.get("https://blog-staging.quantinsti.com/");
		
		
		// Maximize the Window
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
		
		// Cancel the Popup
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
		try
		{
		driver.findElement(By.xpath("onesignal-slidedown-cancel-button"));
		ImpWait1 = true;
		}
		catch(NoSuchElementException e)
		{
			ImpWait1 = false;
			//test1.fail("Couldn't find the popup close element");
		}
		if(ImpWait1==true)
		{
			WebElement pop = driver.findElement(By.id("onesignal-slidedown-cancel-button"));
			if(pop.isEnabled())
			{
				pop.click();
			}
		}
		
		Thread.sleep(3000);
		
		// Cancel the header notification if it exists
				driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS); 
				try
				{
					driver.findElement(By.xpath("//div[@id='top-notification']"));
					ImpWait1 = true;
				}
				catch(NoSuchElementException e)
				{
					ImpWait1 = false;
					//test1.fail("Couldn't find the popup close element");
				}

				if(ImpWait1==true)
				{
					WebElement header = driver.findElement(By.id("close"));
					if(header.isEnabled())
					{
						header.click();
					}
				}
		
		
		// Verify the Page Title
        String Actual = driver.getTitle();
        String Expected = "QuantInsti";

        if (Actual.equals(Expected)) {
                   System.out.println("Test Passed - Homepage Title is "+Expected+"\n");
        } else {
                   System.out.println("Test Failed");
        }
        
        
        // Verify the Blog Name inside webpage
        String bodyText = driver.findElement(By.xpath("//h2[contains(text(),'QuantInsti’s Blog on Algo Trading and Quantitative')]")).getText();
        String A = "QuantInsti’s Blog on Algo Trading and Quantitative Finance";
        if (bodyText.equals(A))
        {
        	System.out.println("Test Passed - Blog Name is "+bodyText+"\n");
        }
        else {
        	System.out.println("Test Failed");
        }
        
        
        // Verify Header Text
        String bodyText1 = driver.findElement(By.xpath("//ul[@id='menu-header']")).getText();
       // System.out.println(bodyText1);
 
        if (bodyText1.contains("Home") && bodyText1.contains("Courses") && bodyText1.contains("Trading Platform") &&bodyText1.contains("Contact Us") &&bodyText1.contains("Login/Sign Up"))
        {
        	System.out.println("Test Passed - The Header of the Hompage Contains the Following Options : \n"+bodyText1+"\n");
        }
        else {
        	System.out.println("Test Failed");
        }
        
        
        // Click on all Header Options (Functionality Check)
        
        //					Home
        
        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
        Thread.sleep(2000);
        String actualHomeTitle = driver.getTitle();
        String expectedHomeTitle = "QuantInsti";

        if (actualHomeTitle.equals(expectedHomeTitle)) {
                   System.out.println("Test Passed - Homepage Title is "+actualHomeTitle+"\n");
        } else {
                   System.out.println("Test Failed");
        }
        
        
        //					Courses 1
        
        driver.findElement(By.className("dropbtn")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Executive Programme in Algorithmic Trading')]")).click();
        Thread.sleep(3000);
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        
        
        String actualCourse1Title = driver.getTitle();
        String expectedCourse1Title = "Algorithmic Trading Course - Training for Traders, Quants & Programmers";
       // System.out.println(actualCourse1Title);
        Thread.sleep(1000);
        driver.findElement(By.id("onesignal-slidedown-cancel-button")).click();
        Thread.sleep(2000);
        
        if (actualCourse1Title.equals(expectedCourse1Title)) {
            System.out.println("Test Passed - Title is "+actualCourse1Title+"\n");
 } else {
            System.out.println("Test Failed");
 }
		Thread.sleep(3000);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(tabs2.get(0));
        
        //					Courses 2    
        
         driver.findElement(By.className("dropbtn")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Algorithmic Trading for Quants')]")).click();
        Thread.sleep(20000);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(3000);
        
        
        String actualCourse2Title = driver.getTitle();
        String expectedCourse2Title = "Online course: Advanced Algorithmic Trading Strategies";
       // System.out.println(actualCourse2Title);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//body/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]")).click();
        Thread.sleep(2000);
        
        if (actualCourse2Title.equals(expectedCourse2Title)) {
            System.out.println("Test Passed - Title is "+actualCourse2Title+"\n");
 } else {
            System.out.println("Test Failed");
 }
		Thread.sleep(3000);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(tabs.get(0));
        
         
        //					Courses 3
         
        
        driver.findElement(By.className("dropbtn")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Options Trading Strategies by NSE Academy')]")).click();
        Thread.sleep(2000);
        ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs3.get(1));
        Thread.sleep(2000);
        
        
        String actualCourse3Title = driver.getTitle();
        String expectedCourse3Title = "Online course: Options Trading Strategies in Python";
       // System.out.println(actualCourse3Title);
        Thread.sleep(2000);       
        
        if (actualCourse3Title.equals(expectedCourse3Title)) {
            System.out.println("Test Passed - Title is "+actualCourse3Title+"\n");
 } else {
            System.out.println("Test Failed");
 }
		Thread.sleep(3000);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(tabs3.get(0));
         
          
               
        //					Courses 4
        
         driver.findElement(By.className("dropbtn")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Mean Reversion Strategies by Ernest Chan')]")).click();
        Thread.sleep(2000);
        ArrayList<String> tabs4 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs4.get(1));
        Thread.sleep(2000);
        
        
        String actualCourse4Title = driver.getTitle();
        String expectedCourse4Title = "Mean Reversion Trading Strategy Course by Ernest Chan";
       // System.out.println(actualCourse4Title);
        Thread.sleep(2000);       
        
        if (actualCourse4Title.equals(expectedCourse4Title)) {
            System.out.println("Test Passed - Title is "+actualCourse4Title+"\n");
 } else {
            System.out.println("Test Failed");
 }
		Thread.sleep(3000);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(tabs4.get(0));
         
         
        
        //					Trading Platform
        
        
        driver.findElement(By.xpath("//a[contains(text(),'Trading Platform')]")).click();
        Thread.sleep(3000);
        ArrayList<String> tabs5 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs5.get(1));
        Thread.sleep(2000);
        
        
        String actualTradingPlatform = driver.getTitle();
        String expectedTradingPlatform = "Blueshift";
       // System.out.println(actualTradingPlatform);
        Thread.sleep(2000);       
        
        if (actualTradingPlatform.equals(expectedTradingPlatform)) {
            System.out.println("Test Passed - Title is "+actualTradingPlatform+"\n");
 } else {
            System.out.println("Test Failed");
 }
		Thread.sleep(2000);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(tabs5.get(0));
         
        
        //					Contact Us
        
        
        driver.findElement(By.className("nav-contact-us")).click();
        Thread.sleep(3000);
        ArrayList<String> tabs6 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs6.get(1));
        Thread.sleep(2000);
        
        
        String actualContactUs = driver.getTitle();
        String expectedContactUs = "Contact Us";
       // System.out.println(actualContactUs);
        Thread.sleep(2000);       
        
        if (actualContactUs.equals(expectedContactUs)) {
            System.out.println("Test Passed - Title is "+actualContactUs+"\n");
 } else {
            System.out.println("Test Failed");
 }
		Thread.sleep(2000);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(tabs6.get(0));
         
                
        
        
        // Verify Tutorials & Career Growth Filter Text
        String bodyText3 = driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='home']/div[2]")).getText();
        // System.out.println(bodyText3);
 
        if (bodyText3.contains("Career Growth") && bodyText3.contains("Tutorials"))
        {
        	System.out.println("Test Passed - text in the filter are :\n"+bodyText3+"\n");
        }
        else {
        	System.out.println("Test Failed");
        }
        
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='home']/div[2]/div[2]/div[2]")).click();
        String filterText1 = driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='home']/div[2]/div[2]/div[2]/ul[1]")).getText();
        
        if (filterText1.contains("Tutorials") && filterText1.contains("Automated Trading") && filterText1.contains("Excel & R For Trading") && filterText1.contains("Forex & Crypto Trading") && filterText1.contains("Machine Learning") && filterText1.contains("Maths and Econometrics") && filterText1.contains("Mean Reversion & Statistical Arbitrage") && filterText1.contains("Momentum Trading") && filterText1.contains("More Trading Strategies") && filterText1.contains("Options Trading") && filterText1.contains("Portfolio & Risk Management") && filterText1.contains("Python For Trading") && filterText1.contains("Sentiment Trading") && filterText1.contains("Technical Indicators"))
        {
        	System.out.println("Test Passed - text in the Tutorials filter are :\n"+filterText1+"\n");
        }
        else {
        	System.out.println("Test Failed");
        }
        Thread.sleep(1000);
        
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='home']/div[2]/div[2]/div[1]")).click();
        String filterText2 = driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='home']/div[2]/div[2]/div[1]/ul[1]")).getText();
        Thread.sleep(2000);
        if (filterText2.contains("Career Growth") && filterText2.contains("Industry") && filterText2.contains("Jobs & Skills") && filterText2.contains("Trading Desk Setup") && filterText2.contains("Success Stories") && filterText2.contains("EPAT Trading Projects"))
        {
        	System.out.println("Test Passed - text in the Career Growth filter are :\n"+filterText2+"\n");
        }
        else {
        	System.out.println("Test Failed");
        }
        		
        
        
        // Upon Clicking on Tutorials / Career Growth options, the blog title should be verified for each options
        
        //								Tutorials - Automated Trading
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='home']/div[2]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'Automated Trading')]")).click();
        Thread.sleep(2000);
        String actualTutorialText1 = driver.findElement(By.xpath("//h1[contains(text(),'Automated Trading')]")).getText();
        String expectedTutorialText1 = "Automated Trading";
        String actualTutorialText2 = driver.findElement(By.xpath("//p[contains(text(),'Learn to automate your trades using different tool')]")).getText();
        String expectedTutorialText2 = "Learn to automate your trades using different tools and platforms: Python, R, Interactive Brokers, Alpaca, Zerodha, Blueshift and many others.";
        
        if (actualTutorialText1.equals(expectedTutorialText1) && actualTutorialText2.equals(expectedTutorialText2)) {
            System.out.println("Test Passed - Blog Title is changed to "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } else {
            System.out.println("Test Failed");
 }
        
        //								Tutorials - Excel & R For Trading
        
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'Excel & R For Trading')]")).click();
        Thread.sleep(2000);     
        actualTutorialText1 = driver.findElement(By.xpath("//h1[contains(text(),'Excel & R For Trading')]")).getText();
        expectedTutorialText1 = "Excel & R For Trading";
        actualTutorialText2 = driver.findElement(By.xpath("//p[contains(text(),'Use R and/or Spreadsheets to create algorithms for')]")).getText();
        expectedTutorialText2 = "Use R and/or Spreadsheets to create algorithms for trading. Ready to use templates and codes are available for downloads. Various quantitative strategies are shared as examples.";
        
        if (actualTutorialText1.equals(expectedTutorialText1) && actualTutorialText2.equals(expectedTutorialText2)) {
            System.out.println("Test Passed - Blog Title is changed to "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } else {
            System.out.println("Test Failed - "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 }
   
         
        
        
        //								Tutorials - Forex & Crypto Trading
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'Forex & Crypto Trading')]")).click();
        Thread.sleep(2000);     
        actualTutorialText1 = driver.findElement(By.xpath("//h1[contains(text(),'Forex & Crypto Trading')]")).getText();
        expectedTutorialText1 = "Forex & Crypto Trading";
        actualTutorialText2 = driver.findElement(By.xpath("//p[contains(text(),'Trade in Forex markets and Crypto Currencies using')]")).getText();
        expectedTutorialText2 = "Trade in Forex markets and Crypto Currencies using advanced techniques and fast computing. Implementable strategies in Python, R and Excel are shared with step by step guidance.";
        
        if (actualTutorialText1.equals(expectedTutorialText1) && actualTutorialText2.equals(expectedTutorialText2)) {
            System.out.println("Test Passed - Blog Title is changed to "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } else {
            System.out.println("Test Failed - "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 }
         
        
        
        
        //								Tutorials - Machine Learning
        
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'Machine Learning')]")).click();
        Thread.sleep(2000);     
        actualTutorialText1 = driver.findElement(By.xpath("//h1[contains(text(),'Machine Learning')]")).getText();
        expectedTutorialText1 = "Machine Learning";
        actualTutorialText2 = driver.findElement(By.xpath("//p[contains(text(),'Learn basics to advanced concepts in machine learn')]")).getText();
        expectedTutorialText2 = "Learn basics to advanced concepts in machine learning and its implementation in financial markets. Includes deep learning, tensor flows, installation guides, downloadable strategy codes along with real-market data.";
        
        if (actualTutorialText1.equals(expectedTutorialText1) && actualTutorialText2.equals(expectedTutorialText2)) {
            System.out.println("Test Passed - Blog Title is changed to "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } else {
            System.out.println("Test Failed - "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 }
     
         
        
        //								Tutorials - Maths and Econometrics
        
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'Maths and Econometrics')]")).click();
        Thread.sleep(2000);     
        actualTutorialText1 = driver.findElement(By.xpath("//h1[contains(text(),'Mathematics and Econometrics')]")).getText();
        expectedTutorialText1 = "Mathematics and Econometrics";
        actualTutorialText2 = driver.findElement(By.xpath("//p[contains(text(),'Learn the practical applications of mathematics an')]")).getText();
        expectedTutorialText2 = "Learn the practical applications of mathematics and econometrics in finance. This series of blogs covers time series analysis, portfolio management, probability distribution, econometrics and many mathematical models.";
        
        if (actualTutorialText1.equals(expectedTutorialText1) && actualTutorialText2.equals(expectedTutorialText2)) {
            System.out.println("Test Passed - Blog Title is changed to "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } else {
            System.out.println("Test Failed - "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 }
     
         
        
        //								Tutorials - Mean Reversion & Statistical Arbitrage
        
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'Mean Reversion & Statistical Arbitrage')]")).click();
        Thread.sleep(2000);     
        actualTutorialText1 = driver.findElement(By.xpath("//h1[contains(text(),'Mean Reversion & Statistical Arbitrage')]")).getText();
        expectedTutorialText1 = "Mean Reversion & Statistical Arbitrage";
        actualTutorialText2 = driver.findElement(By.xpath("//p[contains(text(),'Arbitrage Strategies are very popular among Quants')]")).getText();
        expectedTutorialText2 = "Arbitrage Strategies are very popular among Quants and HFT traders. Get detailed tutorials on implementation of Mean Reversion Theory in financial markets and the underlying mathematics.";
        
        if (actualTutorialText1.equals(expectedTutorialText1) && actualTutorialText2.equals(expectedTutorialText2)) {
            System.out.println("Test Passed - Blog Title is changed to "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } else {
            System.out.println("Test Failed - "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 }
   
         
        
        
        //								Tutorials - Momentum Trading
        
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'Momentum Trading')]")).click();
        Thread.sleep(2000);     
        actualTutorialText1 = driver.findElement(By.xpath("//h1[contains(text(),'Momentum Trading')]")).getText();
        expectedTutorialText1 = "Momentum Trading";
        actualTutorialText2 = driver.findElement(By.xpath("//p[contains(text(),'Momentum trading is a popular trading technique. L')]")).getText();
        expectedTutorialText2 = "Momentum trading is a popular trading technique. Learn about various momentum indicators and implementation across various asset classes.";
        
        if (actualTutorialText1.equals(expectedTutorialText1) && actualTutorialText2.equals(expectedTutorialText2)) {
            System.out.println("Test Passed - Blog Title is changed to "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } else {
            System.out.println("Test Failed - "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 }
   
         
        
        //								Tutorials - More Trading Strategies
        
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'More Trading Strategies')]")).click();
        Thread.sleep(2000);     
        actualTutorialText1 = driver.findElement(By.xpath("//h1[contains(text(),'More Trading Strategies')]")).getText();
        expectedTutorialText1 = "More Trading Strategies";
        actualTutorialText2 = driver.findElement(By.xpath("//p[contains(text(),'Learn advanced trading strategies such as market m')]")).getText();
        expectedTutorialText2 = "Learn advanced trading strategies such as market micro structure, market making, turtle trading approach, and high-frequency trading strategies.";
        
        if (actualTutorialText1.equals(expectedTutorialText1) && actualTutorialText2.equals(expectedTutorialText2)) {
            System.out.println("Test Passed - Blog Title is changed to "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } else {
            System.out.println("Test Failed - "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 }      
         
        
        //								Tutorials - Options Trading
        
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("scroll(0, 250)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'Options Trading')]")).click();
        Thread.sleep(2000);     
        actualTutorialText1 = driver.findElement(By.xpath("//h1[contains(text(),'Options Trading')]")).getText();
        expectedTutorialText1 = "Options Trading";
        actualTutorialText2 = driver.findElement(By.xpath("//p[contains(text(),'A series of tutorials on Options Trading including')]")).getText();
        expectedTutorialText2 = "A series of tutorials on Options Trading including definitions, Options Pricing Models, Option Greeks, different trading strategies with free downloadable codes and data such as Dispersion trading, Index Arbitrage and more.";
        
        if (actualTutorialText1.equals(expectedTutorialText1) && actualTutorialText2.equals(expectedTutorialText2)) {
            System.out.println("Test Passed - Blog Title is changed to "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } else {
            System.out.println("Test Failed - "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } 
         
        
        //								Tutorials - Portfolio & Risk Management
        
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        je = (JavascriptExecutor) driver;
        je.executeScript("scroll(0, 250)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'Portfolio & Risk Management')]")).click();                 
        Thread.sleep(2000);     
        actualTutorialText1 = driver.findElement(By.xpath("//h1[contains(text(),'Portfolio & Risk Management')]")).getText();
        expectedTutorialText1 = "Portfolio & Risk Management";
        actualTutorialText2 = driver.findElement(By.xpath("//p[contains(text(),'From portfolio construction, to analysis, optimiza')]")).getText();
        expectedTutorialText2 = "From portfolio construction, to analysis, optimization and risk management, learn from market practitioners who share their knowledge and downloadable files for free.";
        
        if (actualTutorialText1.equals(expectedTutorialText1) && actualTutorialText2.equals(expectedTutorialText2)) {
            System.out.println("Test Passed - Blog Title is changed to "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } else {
            System.out.println("Test Failed - "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 }          
         
        
        
        //								Tutorials - Python For Trading
        
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        je.executeScript("scroll(0, 250)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'Python For Trading')]")).click();
        Thread.sleep(2000);     
        actualTutorialText1 = driver.findElement(By.xpath("//h1[contains(text(),'Python For Trading')]")).getText();
        expectedTutorialText1 = "Python For Trading";
        actualTutorialText2 = driver.findElement(By.xpath("//p[contains(text(),'Get started with Python for trading. Learn about i')]")).getText();
        expectedTutorialText2 = "Get started with Python for trading. Learn about important libraries and their installation, how to de-bug your code and write simple to advance algorithms for trading.";
        
        if (actualTutorialText1.equals(expectedTutorialText1) && actualTutorialText2.equals(expectedTutorialText2)) {
            System.out.println("Test Passed - Blog Title is changed to "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } else {
            System.out.println("Test Failed - "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 }          
         
        
        //								Tutorials - Sentiment Trading
        
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        je.executeScript("scroll(0, 350)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'Sentiment Trading')]")).click();
        Thread.sleep(2000);     
        actualTutorialText1 = driver.findElement(By.xpath("//h1[contains(text(),'Sentiment Trading')]")).getText();
        expectedTutorialText1 = "Sentiment Trading";
        actualTutorialText2 = driver.findElement(By.xpath("//p[contains(text(),'There are two ways to automate sentiment trading: ')]")).getText();
        expectedTutorialText2 = "There are two ways to automate sentiment trading: using news and social media or using market actions. Get free downloadable codes and strategies to get started.";
        
        if (actualTutorialText1.equals(expectedTutorialText1) && actualTutorialText2.equals(expectedTutorialText2)) {
            System.out.println("Test Passed - Blog Title is changed to "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } else {
            System.out.println("Test Failed - "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 }        
         
        
        //								Tutorials - Technical Indicators

        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[2]")).click();
        Thread.sleep(1000);
        je.executeScript("scroll(0, 350)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'Technical Indicators')]")).click();
        Thread.sleep(2000);     
        actualTutorialText1 = driver.findElement(By.xpath("//h1[contains(text(),'Technical Indicators')]")).getText();
        expectedTutorialText1 = "Technical Indicators";
        actualTutorialText2 = driver.findElement(By.xpath("//p[contains(text(),'Come up with trading strategies using technical in')]")).getText();
        expectedTutorialText2 = "Come up with trading strategies using technical indicators and backtest your ideas on historical data. Learn from market practitioners who share their knowledge and downloadable files for free.";
        
        if (actualTutorialText1.equals(expectedTutorialText1) && actualTutorialText2.equals(expectedTutorialText2)) {
            System.out.println("Test Passed - Blog Title is changed to "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 } else {
            System.out.println("Test Failed - "+actualTutorialText1+"\n"+actualTutorialText2+"\n");
 }        
            
         
        
        
        //								Career Growth - Industry 
        Thread.sleep(1500);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[1]")).click(); 
        driver.findElement(By.xpath("//li[contains(text(),'Industry')]")).click();
        Thread.sleep(3000);
        
        String actualCareerText1 = driver.findElement(By.xpath("//h1[contains(text(),'Industry')]")).getText();
        String expectedCareerText1 = "Industry";
        String actualCareerText2 = driver.findElement(By.xpath("//p[contains(text(),'Read about Algorithmic and Quantitative Trading in')]")).getText();
        String expectedCareerText2 = "Read about Algorithmic and Quantitative Trading industry terminologies, news, growth, technological advancements, opinions and more.";
        
        if (actualCareerText1.equals(expectedCareerText1) && actualCareerText2.equals(expectedCareerText2)) 
        {
            System.out.println("Test Passed - Blog Title is changed to "+actualCareerText1+"\n"+actualCareerText2+"\n");
 } else {
            System.out.println("Test Failed");
        }
      
        
        //								Career Growth - Jobs & Skills 
        
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[1]")).click(); 
        driver.findElement(By.xpath("//li[contains(text(),'Jobs & Skills')]")).click();
        Thread.sleep(3000);
        
        actualCareerText1 = driver.findElement(By.xpath("//h1[contains(text(),'Jobs & Skills')]")).getText();
        expectedCareerText1 = "Jobs & Skills";
        actualCareerText2 = driver.findElement(By.xpath("//p[contains(text(),'To get hired in Prop desks, Hedge funds, Instituti')]")).getText();
        expectedCareerText2 = "To get hired in Prop desks, Hedge funds, Institutional trading desks, you need a strong skill set. Read about the skills required to get hired in roles and jobs available in the financial markets.";
        
        if (actualCareerText1.equals(expectedCareerText1) && actualCareerText2.equals(expectedCareerText2)) 
        {
            System.out.println("Test Passed - Blog Title is changed to "+actualCareerText1+"\n"+actualCareerText2+"\n");
 } else {
            System.out.println("Test Failed");
         
 }
        
        //								Career Growth - Trading Desk Setup 
        
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[1]")).click(); 
        driver.findElement(By.xpath("//li[contains(text(),'Trading Desk Setup')]")).click();
        Thread.sleep(3000);
        
        actualCareerText1 = driver.findElement(By.xpath("//h1[contains(text(),'Trading Desk Setup')]")).getText();
        expectedCareerText1 = "Trading Desk Setup";
        actualCareerText2 = driver.findElement(By.xpath("//p[contains(text(),'Build your own startup in financial markets using ')]")).getText();
        expectedCareerText2 = "Build your own startup in financial markets using technology and innovation. Upgrade your existing trading or technology business by entering into a niche field of Algorithmic Trading.";
        
        if (actualCareerText1.equals(expectedCareerText1) && actualCareerText2.equals(expectedCareerText2)) 
        {
            System.out.println("Test Passed - Blog Title is changed to "+actualCareerText1+"\n"+actualCareerText2+"\n");
 } else {
            System.out.println("Test Failed");
         
 }
         
        
        //								Career Growth - Success Stories 
        
        
                 
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[1]")).click(); 
        driver.findElement(By.xpath("//li[contains(text(),'Success Stories')]")).click();
        Thread.sleep(3000);
        
        actualCareerText1 = driver.findElement(By.xpath("//h1[contains(text(),'Success Stories')]")).getText();
        expectedCareerText1 = "Success Stories";
        actualCareerText2 = driver.findElement(By.xpath("//p[contains(text(),'Get Inspired. Read about entrepreneurs, traders, d')]")).getText();
        expectedCareerText2 = "Get Inspired. Read about entrepreneurs, traders, developers, analysts from around the globe, who changed their lives by gaining the must-have skills set.";
        
        if (actualCareerText1.equals(expectedCareerText1) && actualCareerText2.equals(expectedCareerText2)) 
        {
            System.out.println("Test Passed - Blog Title is changed to "+actualCareerText1+"\n"+actualCareerText2+"\n");
 } else {
            System.out.println("Test Failed");
         
 }
         
        
        //								Career Growth - EPAT Trading Projects
        
        
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[2]/div[1]")).click(); 
        driver.findElement(By.xpath("//li[contains(text(),'EPAT Trading Projects')]")).click();
        Thread.sleep(3000);
        
        actualCareerText1 = driver.findElement(By.xpath("//h1[contains(text(),'EPAT Trading Projects')]")).getText();
        expectedCareerText1 = "EPAT Trading Projects";
        actualCareerText2 = driver.findElement(By.xpath("//p[contains(text(),'Ready-to-implement project work done by EPATians o')]")).getText();
        expectedCareerText2 = "Ready-to-implement project work done by EPATians on real-markets data in Artificial Intelligence, Statistical Arbitrage, Sentiment Trading, Crypto Currency and more.";
        
        if (actualCareerText1.equals(expectedCareerText1) && actualCareerText2.equals(expectedCareerText2)) 
        {
            System.out.println("Test Passed - Blog Title is changed to "+actualCareerText1+"\n"+actualCareerText2+"\n");
 } else {
            System.out.println("Test Failed");
         
 }         
         
               
        
     // Verify in Header - Course's Options Text
        driver.findElement(By.className("dropbtn")).click();
        Thread.sleep(2000);
        String bodyText4 = driver.findElement(By.className("dropdown-content")).getText();
       // System.out.println(bodyText4);
 
       if (bodyText4.contains("Executive Programme in Algorithmic Trading") && bodyText4.contains("Algorithmic Trading for Quants") && bodyText4.contains("Options Trading Strategies by NSE Academy" ) && bodyText4.contains("Mean Reversion Strategies by Ernest Chan"))
        {
        	System.out.println("Test Passed - The Course Option in Header contains the following Options : \n"+bodyText4+"\n");
        }
        else {
        	System.out.println("Test Failed");
        }
       
       
       // Verify the text in search box
       String bodyText5 = driver.findElement(By.name("s")).getAttribute("placeholder");
       //System.out.println(bodyText5);

      if (bodyText5.contains("Search in the blog..."))
       {
       	System.out.println("Test Passed - The text in the searchbox is : "+bodyText5+"\n");
       }
       else {
       	System.out.println("Test Failed");
       }
      Thread.sleep(1000);
      
      
      
      // Verify the Search Functionality
      
      driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[1]/form[1]/input[1]")).sendKeys("Python");
      driver.findElement(By.xpath("//body/div[@id='wrapper']/main[@id='archives']/div[2]/div[1]/form[1]/button[1]/i[1]")).click();
      Thread.sleep(1000);
      driver.findElement(By.xpath("//a[contains(text(),'Python For Trading: An Introduction')]")).click();
      Thread.sleep(3000);
      String actualBlogText = driver.findElement(By.xpath("//h1[contains(text(),'Python For Trading: An Introduction')]")).getText();
      String expectedBlogText = "Python For Trading: An Introduction";
      
      if(actualBlogText.equals(expectedBlogText))
      {
    	  System.out.println("Test Passed - The text on the blog is : "+actualBlogText+"\n");
      }
      else
      {
    	  System.out.println("Test Failed");
      }
      driver.navigate().back();
      Thread.sleep(3000);
      
      
      // Verify that the Next button on the footer
      JavascriptExecutor jse = (JavascriptExecutor)driver;
      jse.executeScript("window.scrollBy(0,1500)");
      String bodyText6 = driver.findElement(By.xpath("//a[contains(text(),'Next')]")).getText();
      //System.out.println(bodyText6);

     if (bodyText6.contains("Next"))
      {
      	System.out.println("Test Passed - There is a "+bodyText6+" Button on the Footer "+"\n");
      }
      else {
      	System.out.println("Test Failed");
      }
      
     
     // Verify the Count of blogs in first page 
     List<WebElement> X = driver.findElements(By.tagName("article"));
     int xpathCount = X.size();
     System.out.println("Total Blogs in the page is : " + xpathCount +"\n");
     
     
     // Login Functionality 
     Thread.sleep(2000);
     jse.executeScript("window.scrollBy(0,0)");
     driver.findElement(By.xpath("//a[contains(text(),'Login/Sign Up')]")).click();
     driver.findElement(By.xpath("//a[contains(text(),'Already a member? Sign in')]")).click();
     driver.findElement(By.name("email")).sendKeys("lenin.d+1@quantinsti.com");
     driver.findElement(By.name("password")).sendKeys("Leonardo@1157358");
     driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
     Thread.sleep(3000);
     System.out.println("The User has Logged In \n");
     Thread.sleep(2000);
     
     
     // After Login Verify the dropdown for profile & logout
     driver.findElement(By.className("profile-info")).click();
     Thread.sleep(2000);
     String bodyText7 = driver.findElement(By.className("profile-info")).getText();
    // System.out.println(bodyText7);

    if (bodyText7.contains("Profile") && bodyText7.contains("Logout"))
     {
     	System.out.println("Test Passed - The Login Option in Header contains the following Options : \n"+bodyText7+"\n");
     }
     else {
     	System.out.println("Test Failed");
     }
     
      
    //					Profile
    
    driver.findElement(By.linkText("Profile")).click();
    Thread.sleep(2000);
    ArrayList<String> tabs7 = new ArrayList<String> (driver.getWindowHandles());
    driver.switchTo().window(tabs7.get(1));
    Thread.sleep(2000);
    
    
    String actualProfile = driver.getTitle();
    String expectedProfile = "QuantInsti SSO";
   // System.out.println(actualProfile);
    Thread.sleep(2000);       
    
    if (actualProfile.equals(expectedProfile)) {
        System.out.println("Test Passed - Title is "+actualProfile+"\n");
} else {
        System.out.println("Test Failed");
}
	//Thread.sleep(2000);
    driver.close();
    Thread.sleep(2000);
    driver.switchTo().window(tabs7.get(0));
    
    
    
    
    //					Logout
    
    driver.findElement(By.className("profile-info")).click();
    Thread.sleep(1000);
    driver.findElement(By.linkText("Logout")).click();
    System.out.println("User Logged out \n");
    Thread.sleep(2000);
    
    
    
        // Verify Footer Text
        String bodyText2 = driver.findElement(By.xpath("//body/div[@id='wrapper']/footer[@id='footer']/div[1]")).getText();
        //System.out.println(bodyText2);
 
        if (bodyText2.contains("About") && bodyText2.contains("QuantInsti") && bodyText2.contains("Quantra") && bodyText2.contains("Blueshift") && bodyText2.contains("Events & Announcements") && bodyText2.contains("Announcements") && bodyText2.contains("Webinars") && bodyText2.contains("Quick Links") && bodyText2.contains("Contact Us") && bodyText2.contains("Privacy Policy") && bodyText2.contains("Blog Contribution"))
        {
        	System.out.println("Test Passed - The Footer of the Hompage Contains the Following Text : \n"+bodyText2);
        }
        else {
        	System.out.println("Test Failed");
        }
        
        
        // Click on all Footer Options (Functionality Check)
        // 							About - Quantinsti
        
        jse.executeScript("window.scrollBy(0,1500)");
        Thread.sleep(2000);
        driver.findElement(By.linkText("QuantInsti")).click();
        ArrayList<String> tabs8 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs8.get(1));
        Thread.sleep(2000);
        
        
        String actualFooterAboutTitle1 = driver.getTitle();
        String expectedFooterAboutTitle1 = "QuantInsti - Learn Algorithmic Trading from Market Practitioners";
       // System.out.println(actualFooterAboutTitle1);
        Thread.sleep(2000);       
        
        if (actualFooterAboutTitle1.equals(expectedFooterAboutTitle1)) {
            System.out.println("\n Test Passed - Title is "+actualFooterAboutTitle1+"\n");
 } else {
            System.out.println("Test Failed");
 }
		Thread.sleep(2000);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(tabs8.get(0));
        
        
        
        //						  About - Quantra
        
         driver.findElement(By.linkText("Quantra")).click();
        ArrayList<String> tabs9 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs9.get(1));
        Thread.sleep(2000);
        
        
        String actualFooterAboutTitle2 = driver.getTitle();
        String expectedFooterAboutTitle2 = "Quantra by QuantInsti | Courses on Algorithmic & Quantitative Trading";
       // System.out.println(actualFooterAboutTitle2);
        Thread.sleep(2000);       
        
        if (actualFooterAboutTitle2.equals(expectedFooterAboutTitle2)) {
            System.out.println("Test Passed - Title is "+actualFooterAboutTitle2+"\n");
 } else {
            System.out.println("Test Failed");
 }
		Thread.sleep(2000);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(tabs9.get(0));
         
        
        //		  				  About - Blueshift
        
        
         driver.findElement(By.linkText("Blueshift")).click();
        ArrayList<String> tabs10 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs10.get(1));
        Thread.sleep(2000);
        
        
        String actualFooterAboutTitle3 = driver.getTitle();
        String expectedFooterAboutTitle3 = "Blueshift";
       // System.out.println(actualFooterAboutTitle3);
        Thread.sleep(2000);       
        
        if (actualFooterAboutTitle3.equals(expectedFooterAboutTitle3)) {
            System.out.println("Test Passed - Title is "+actualFooterAboutTitle3+"\n");
 } else {
            System.out.println("Test Failed");
 }
		Thread.sleep(2000);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(tabs10.get(0));
         
        

        //		  				  Events & Announcements - Announcements
        
        
          driver.findElement(By.linkText("Announcements")).click();
        Thread.sleep(2000);
        String actualAnnouncementText1 = driver.findElement(By.xpath("//h1[contains(text(),'Announcements')]")).getText();
        String expectedAnnouncementText1 = "Announcements";
        String actualAnnouncementText2 = driver.findElement(By.xpath("//p[contains(text(),'QuantInsti company news, product launches and anno')]")).getText();
        String expectedAnnouncementText2 = "QuantInsti company news, product launches and announcements are shared here.";
        
        if (actualAnnouncementText1.equals(expectedAnnouncementText1) && actualAnnouncementText2.equals(expectedAnnouncementText2)) {
            System.out.println("Test Passed - Title is "+actualAnnouncementText1+"\n"+actualAnnouncementText2+"\n");
 } else {
            System.out.println("Test Failed");
 }
        
        jse.executeScript("window.scrollBy(0,1500)");
        String actualEATitle1 = driver.getTitle();
        String expectedEATitle1 = "Announcements - QuantInsti";
       // System.out.println(actualEATitle1);
        Thread.sleep(2000);       
        
        if (actualEATitle1.equals(expectedEATitle1)) {
            System.out.println("Test Passed - Title is "+actualEATitle1+"\n");
 } else {
            System.out.println("Test Failed");
 }

         
        
        //		  				  Events & Announcements - Webinars
        
        
        driver.findElement(By.linkText("Webinars")).click();
        Thread.sleep(2000);
       
        
        String actualWebinarText1 = driver.findElement(By.xpath("//h1[contains(text(),'Webinars')]")).getText();
        String expectedWebinarText1 = "Webinars";
        String actualWebinarText2 = driver.findElement(By.xpath("//p[contains(text(),'Webinars, workshops and events related information')]")).getText();
        String expectedWebinarText2 = "Webinars, workshops and events related information and announcements are shared here.";
        
        if (actualWebinarText1.equals(expectedWebinarText1) && actualWebinarText2.equals(expectedWebinarText2)) {
            System.out.println("Test Passed - Title is "+actualWebinarText1+"\n"+actualWebinarText2+"\n");
 } else {
            System.out.println("Test Failed");
 }
         
        
        jse.executeScript("window.scrollBy(0,1500)");
        String actualEATitle2 = driver.getTitle();
        String expectedEATitle2 = "Webinars - QuantInsti";
       // System.out.println(actualEATitle2);
        Thread.sleep(2000);       
        
        if (actualEATitle2.equals(expectedEATitle2)) {
            System.out.println("Test Passed - Title is "+actualEATitle2+"\n");
 } else {
            System.out.println("Test Failed");
 }
         
        
        //		  				  Quick Links - Contact Us
        
        
        driver.findElement(By.xpath("//body[1]/div[4]/footer[1]/div[1]/div[4]/div[1]/ul[1]/li[1]/a[1]")).click();
        Thread.sleep(2000);
        ArrayList<String> tabs11 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs11.get(1));
        
        String actualQLTitle1 = driver.getTitle();
        String expectedQLTitle1 = "Contact Us";
       // System.out.println(actualQLTitle1);
        Thread.sleep(2000);       
        
        if (actualQLTitle1.equals(expectedQLTitle1)) {
            System.out.println("Test Passed - Title is "+actualQLTitle1+"\n");
 } else {
            System.out.println("Test Failed");
 }
 
 		Thread.sleep(2000);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(tabs11.get(0));
         
        
        //		  				  Quick Links - Privacy Policies
        
        
         driver.findElement(By.linkText("Privacy Policy")).click();
        Thread.sleep(2000);
        ArrayList<String> tabs12 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs12.get(1));
        
        String actualQLTitle2 = driver.getTitle();
        String expectedQLTitle2 = "Policies";
       // System.out.println(actualQLTitle2);
        Thread.sleep(2000);       
        
        if (actualQLTitle2.equals(expectedQLTitle2)) {
            System.out.println("Test Passed - Title is "+actualQLTitle2+"\n");
 } else {
            System.out.println("Test Failed");
 }
 
 		Thread.sleep(2000);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(tabs12.get(0));
         
        
        //		  				  Quick Links - Blog Contribution
        
        
          driver.findElement(By.linkText("Blog Contribution")).click();
        Thread.sleep(2000);
        ArrayList<String> tabs13 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs13.get(1));
        
        String actualQLTitle3 = driver.getTitle();
        String expectedQLTitle3 = "Blog Contribution Guidelines";
       // System.out.println(actualQLTitle3);
        Thread.sleep(2000);       
        
        if (actualQLTitle3.equals(expectedQLTitle3)) {
            System.out.println("Test Passed - Title is "+actualQLTitle3+"\n");
 } else {
            System.out.println("Test Failed");
 }
 
 		Thread.sleep(2000);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(tabs13.get(0));
         
        
        
        // Back to Top
        
        driver.findElement(By.xpath("//a[@id='back-to-top']")).click();
        Thread.sleep(1000);
        
        
        // Close Browser and End Session
        
		driver.close();
		System.out.println("Session End");
		

	}

}
