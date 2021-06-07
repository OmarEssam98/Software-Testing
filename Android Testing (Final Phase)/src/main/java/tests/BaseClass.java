package tests;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class BaseClass
{
    static AppiumDriver<MobileElement> driver;
    static WebDriverWait wait;
    static Actions action;
    static TouchAction touch;
    public static void main(String[]args)
    {
        try
        {
            Login();   //First Log In

            /////////////  Omar's Tasks  /////////////

            ViewProfile();   //Then View Profile
            EditDescriptionAndShowcase();   //Then Edit Description and Showcase
            ViewCameraRoll();   //Then View Camera Roll
            EditCoverPhoto();   //Then Edit Cover Photo
            UploadPhoto();   //Then Upload Photo
            DeletePhoto();   //Then delete Uploaded Photo

            /////////////  Galal's and Davinci's Tasks  /////////////

            ViewPhotoStream();   //Then View Photo Stream
            ViewComments();   //Then View Comments
            WriteComment();  //Then Write Comment
            //DeleteComment()   //Then Delete Comment   => (Feature is not implemented)
            FollowUser();   //Then Follow User
            UnFollowUser();   //Then Unfollow User
            ViewFavorites();   //Then View Favorites
            Favorite();   //Then Favorite a Photo
            UnFavorite();   //Then Unfavorite the Same Photo
        }
        catch(Exception Exp)
        {
            System.out.println(Exp.getCause());
            System.out.println(Exp.getMessage());
            Exp.printStackTrace();
        }
    }

    public static void Login() throws MalformedURLException, InterruptedException
    {
        //////////////////////    Setup    //////////////////////

        DesiredCapabilities caps= new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "10");
        //caps.setCapability("appPackage","com.flickr.android");   //For Flickr
        //caps.setCapability("appActivity","com.yahoo.mobile.client.android.flickr.activity.MainActivity");   //For Flickr
        caps.setCapability("appPackage","com.example.flickr");   //For Test App
        caps.setCapability("appActivity","com.example.flickr.MainActivity");   //For Test App
        caps.setCapability("deviceName", "FlickrTest");
        caps.setCapability("UDID", "R9AR20ERRAJ");
        //caps.setCapability("systemPort", "8210");
        //caps.setCapability(CapabilityType.NEW_COMMAND_TIMEOUT, 60);
        URL url =new URL("http://127.0.0.1:4723/wd/hub");
        driver=new AppiumDriver<MobileElement>(url,caps);
        action = new Actions(driver);
        touch= new TouchAction(driver);
        System.out.println("Application Started Successfully!");

        //////////////////////    Login    //////////////////////

        wait=new WebDriverWait(driver, 30);   //Initialize WebDriver to Wait 30 seconds for Elements to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"GetStarted\"]")));   //Wait for Get Started Button to appear
        MobileElement GetStarted=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"GetStarted\"]"));
        GetStarted.click();
        System.out.println("Get Started Button Clicked Successfully!");
        System.out.println("Waiting for Login Page to load...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText")));  //Execute WebDriver to Wait 30 seconds for Page to load and Login Page to appear
        MobileElement Email=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText"));
        System.out.println("Login Page Loaded Successfully!");
        Email.click();
        System.out.println("Email Address Text Clicked Successfully!");
        action.sendKeys("test@test.com").perform();
        //Email.sendKeys(Keys.chord("test@test.com"));
        System.out.println("Email Address Typed In Successfully!");
        MobileElement LoginNext=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Next\"]"));
        LoginNext.click();
        System.out.println("Next Button Pressed Successfully!");
        System.out.println("Waiting for Password Text to load...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]")));  //Execute WebDriver to Wait 30 seconds for Password Text to appear
        System.out.println("Password Text Loaded Successfully!");
        MobileElement Password=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]"));
        Password.click();
        System.out.println("Password Text Clicked Successfully!");
        action.sendKeys("0123456789aaa").perform();
        System.out.println("Password Typed In Successfully!");
        MobileElement SignIn=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Login\"]"));
        SignIn.click();
        System.out.println("Sign In Button Clicked Successfully!");
        System.out.println("Signing In...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Tab 1 of 5\"]")));
        Thread.sleep(1000); //Wait for page to load
        System.out.println("Signed In Successfully!");
    }

    public static void ViewProfile()
    {
        System.out.println("Testing Going to SubProfile...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Tab 3 of 5\"]")));
        MobileElement SubProfile=driver.findElement(By.xpath("//android.view.View[@content-desc=\"Tab 3 of 5\"]"));
        System.out.println("Clicking On SubProfile Tab...");
        SubProfile.click();
        System.out.println("Clicked On SubProfile Successfully!");
    }

    public static void EditDescriptionAndShowcase() throws InterruptedException
    {
        System.out.println("Testing Editing Showcase...");
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc=\"About Tab 4 of 4\"]")));
        //MobileElement About=driver.findElement(By.xpath("//android.view.View[@content-desc=\"About Tab 4 of 4\"]"));
        System.out.println("Clicking on About...");
        //About.click();
        Thread.sleep(1000); //Wait for page to load
        touch.tap(PointOption.point(593, 672)).perform();   //XPath not working so used tap using coordinates instead
        System.out.println("Clicked On About Successfully!");
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Description: \"]")));
        System.out.println("Clicking on Description...");
        //MobileElement Description=driver.findElement(By.xpath("//android.view.View[@content-desc=\"Description: \"]"));
        //Description.click();
        Thread.sleep(1000); //Wait for page to load
        touch.tap(PointOption.point(110, 880)).perform();   //XPath not working so used tap using coordinates instead
        System.out.println("Clicked On Description Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText")));
        MobileElement InnerDescription = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText"));
        System.out.println("Clicking On Inner Description...");
        InnerDescription.click();
        System.out.println("Clicked On Inner Description Successfully!");
        action.sendKeys("Testing Account").perform();
        System.out.println("Edited Description Successfully!");
        MobileElement Done = driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Done\"]"));
        System.out.println("Clicking On Done...");
        Done.click();
        System.out.println("Clicked On Done Successfully!");
    }

    public static void ViewCameraRoll()
    {
        System.out.println("Testing viewing Camera Roll...");
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Camera Roll Tab 1 of 4\"]")));
        //MobileElement CameraRoll=driver.findElement(By.xpath("//android.view.View[@content-desc=\"Camera Roll Tab 1 of 4\"]"));
        System.out.println("Clicking On Camera Roll Tab...");
        //CameraRoll.click();
        touch.tap(PointOption.point(110, 672)).perform();   //XPath not working so used tap using coordinates instead
        System.out.println("Clicked On Camera Roll Tab Successfully!");
    }

    public static void EditCoverPhoto() throws InterruptedException
    {
        System.out.println("Testing Editing Cover Photo...");
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView[@content-desc=\"test test\"]/android.view.View[1]")));
        System.out.println("Clicking On Cover Photo...");
        //MobileElement CoverPhoto=driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"test test\"]/android.view.View[1]"));
        //CoverPhoto.click();
        Thread.sleep(1000); //Wait for page to load
        touch.tap(PointOption.point(393, 431)).perform();   //XPath changes for every account so used tap using coordinates instead
        System.out.println("Clicked On Cover Photo Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button[2]")));
        MobileElement UploadImage=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button[2]"));
        System.out.println("Clicking On Uploading Photo From Gallery...");
        UploadImage.click();
        System.out.println("Clicked On Uploading Photo From Gallery Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.Button[1]")));
        System.out.println("Clicking On Allow Access to Media...");
        MobileElement MediaAccess=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.Button[1]"));
        MediaAccess.click();
        System.out.println("Clicked On Allow Access to Media Successfully!");
        Thread.sleep(1000);   //Wait for Gallery to Load
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[@content-desc=\"Software Testing.jpg, 39.44 kB, 10:27 PM\"]/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.ImageView[1]")));
        System.out.println("Clicking On Photo...");
        //MobileElement Photo=driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Software Testing.jpg, 39.44 kB, 10:27 PM\"]/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.ImageView[1]"));
        //Photo.click();
        touch.tap(PointOption.point(138, 610)).perform();   //XPath not working so used tap using coordinates instead
        System.out.println("Clicked On Photo Successfully!");
    }

    public static void UploadPhoto() throws InterruptedException
    {
        System.out.println("Testing Uploading Photo...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Tab 5 of 5\"]/android.widget.Button")));
        System.out.println("Clicking On Upload Tab...");
        MobileElement UploadPhoto=driver.findElement(By.xpath("//android.view.View[@content-desc=\"Tab 5 of 5\"]/android.widget.Button"));
        UploadPhoto.click();
        System.out.println("Clicked On Upload Tab Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button[2]")));
        MobileElement UploadImage=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button[2]"));
        System.out.println("Clicking On Uploading Photo From Gallery...");
        UploadImage.click();
        System.out.println("Clicked On Uploading Photo From Gallery Successfully!");
        Thread.sleep(1000);   //Wait for Gallery to Load
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[@content-desc=\"20210508_224349_mfnr.jpg, 525 kB, May 8\"]/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.ImageView[1]")));
        System.out.println("Clicking On Photo...");
        //MobileElement Photo=driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Software Testing.jpg, 39.44 kB, 10:27 PM\"]/android.widget.RelativeLayout/android.widget.FrameLayout[1]/android.widget.ImageView[1]"));
        //Photo.click();
        touch.tap(PointOption.point(138, 610)).perform();   //XPath not working so used tap using coordinates instead
        System.out.println("Clicked On Photo Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")));
        System.out.println("Clicking On Title...");
        MobileElement Title=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]"));
        Title.click();
        System.out.println("Clicked On Title Successfully!");
        System.out.println("Sending Keys...");
        action.sendKeys("Testing Photo").perform();
        System.out.println("Sent Keys Successfully!");
        System.out.println("Clicking On Title...");
        MobileElement Description=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]"));
        Description.click();
        System.out.println("Clicked On Description Successfully!");
        System.out.println("Sending Keys...");
        action.sendKeys("This is a testing photo").perform();
        System.out.println("Sent Keys Successfully!");
        //MobileElement Tag=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Tags \"]"));
        System.out.println("Clicking On Tag...");
        //Tag.click();
        touch.tap(PointOption.point(110, 462)).perform();   //XPath not working so used tap using coordinates instead
        System.out.println("Clicked On Tag Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText")));
        System.out.println("Clicking On Inner Tag...");
        MobileElement InnerTag=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText"));
        InnerTag.click();
        System.out.println("Clicked On Inner Tag Successfully!");
        System.out.println("Sending Keys...");
        action.sendKeys("#Testing"+"\n").perform();
        System.out.println("Sent Keys Successfully!");
        MobileElement AddTag=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText/android.widget.Button"));
        System.out.println("Clicking On Add Tag...");
        AddTag.click();
        System.out.println("Clicked On Add Tag Successfully!");
        //driver.sendKeyEvent(Keys.ENTER);
        MobileElement Back=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button"));
        System.out.println("Clicking On Back Button...");
        Back.click();
        System.out.println("Clicked On Back Button Successfully!");
        MobileElement Done=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button"));
        System.out.println("Clicking On Done Button...");
        Done.click();
        System.out.println("Clicked On Done Button Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"OK\"]")));
        System.out.println("Clicking On OK Button...");
        MobileElement OK=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"OK\"]"));
        OK.click();
        System.out.println("Clicked On OK Button Successfully!");
        driver.hideKeyboard();
        Thread.sleep(5000);
    }

    public static void DeletePhoto() throws InterruptedException
    {
        System.out.println("Going to SubProfile...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Tab 3 of 5\"]")));
        MobileElement SubProfile=driver.findElement(By.xpath("//android.view.View[@content-desc=\"Tab 3 of 5\"]"));
        System.out.println("Clicking On SubProfile Tab...");
        SubProfile.click();
        System.out.println("Clicked On SubProfile Successfully!");
        System.out.println("Testing Deleting Photo...");
        Thread.sleep(1000);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.widget.ImageView[1]")));
        System.out.println("Clicking On Photo...");
        //MobileElement Photo= driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.widget.ImageView[1]"));
        //Photo.click();
        touch.tap(PointOption.point(165, 872)).perform();   //XPath not working so used tap using coordinates instead
        System.out.println("Clicked On Photo Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"info about this image\"]")));
        System.out.println("Clicking On Info...");
        MobileElement Info= driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"info about this image\"]"));
        Info.click();
        System.out.println("Clicked On Info Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button[6]")));
        System.out.println("Clicking On Delete...");
        MobileElement Delete= driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button[6]"));
        Delete.click();
        System.out.println("Clicked On Delete Successfully!");
        System.out.println("Going Back to Photo...");
        driver.navigate().back();   //To go back to Camera Roll after pressing delete
        System.out.println("Went Back to Photo Successfully!");
        System.out.println("Going Back to Camera Roll...");
        System.out.println("Went Back to Camera Roll Successfully!");
        Thread.sleep(1000);   //Wait before refreshing
        System.out.println("Refreshing Tab...");
        touch.tap(PointOption.point(317, 672)).perform();
        Thread.sleep(1000);   //Wait before refreshing
        touch.tap(PointOption.point(152, 675)).perform();
        driver.hideKeyboard();
        System.out.println("Photo Deleted Successfully!");
    }

    public static void ViewPhotoStream()
    {
        System.out.println("Testing Viewing Photo Stream...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Tab 1 of 5\"]")));
        MobileElement PhotoStream=driver.findElement(By.xpath("//android.view.View[@content-desc=\"Tab 1 of 5\"]"));
        System.out.println("Clicking On PhotoStream Tab...");
        PhotoStream.click();
        System.out.println("Clicked On PhotoStream Tab Successfully!");

    }

    public static void ViewComments()
    {
        System.out.println("Testing Viewing Comments...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Open comment Section\"]")));
        MobileElement CommentsTaB=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Open comment Section\"]"));
        System.out.println("Clicking On Comments Button...");
        CommentsTaB.click();
        System.out.println("Clicked On Comments Button Successfully!");
    }

    public static void WriteComment() throws InterruptedException
    {
        System.out.println("Testing Writing Comments...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText")));
        System.out.println("Clicking On Write Bar...");
        MobileElement Write=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText"));
        Write.click();
        System.out.println("Clicked On Write Tab Successfully!");
        action.sendKeys("Just Testing Comments").perform();
        MobileElement Done=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ScrollView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button"));
        System.out.println("Clicking On Send...");
        Done.click();
        driver.hideKeyboard();
        System.out.println("Refreshing Comments...");
        //MobileElement Favorites=driver.findElement(By.xpath("//android.view.View[@content-desc=\"4 Faves Tab 1 of 2\"]"));
        //Favorites.click();
        touch.tap(PointOption.point(170, 200)).perform();   //XPath not working so used tap using coordinates instead
        Thread.sleep(1000);   //Wait for comments to refresh
        touch.tap(PointOption.point(503, 203)).perform();   //XPath not working so used tap using coordinates instead
        System.out.println("Refreshed Comments Successfully!");
        driver.navigate().back();  //Go back to Photo Stream
        driver.hideKeyboard();
    }


    public static void FollowUser() throws InterruptedException
    {
        System.out.println("Testing Following Users...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Open comment Section\"]")));
        MobileElement CommentsTaB=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Open comment Section\"]"));
        System.out.println("Clicking On Comments Button...");
        CommentsTaB.click();
        System.out.println("Clicked On Comments Button Successfully!");
        Thread.sleep(1000);
        //MobileElement Favorites=driver.findElement(By.xpath("//android.view.View[@content-desc=\"4 Faves Tab 1 of 2\"]"));
        //Favorites.click();
        System.out.println("Clicking On Favorites Tab...");
        touch.tap(PointOption.point(170, 200)).perform();   //XPath not working so used tap using coordinates instead
        System.out.println("Clicked On Favorites Tab Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//android.widget.Button[@content-desc=\"Follow\"])[1]")));
        System.out.println("Clicking On Follow Button...");
        MobileElement Follow=driver.findElement(By.xpath("(//android.widget.Button[@content-desc=\"Follow\"])[1]"));
        Follow.click();
        System.out.println("Clicked On Follow Button Successfully!");
        System.out.println("Refreshing Favorites Tab...");
        touch.tap(PointOption.point(517, 207)).perform();   //XPath not working so used tap using coordinates instead
        Thread.sleep(1000);
        touch.tap(PointOption.point(170, 200)).perform();   //XPath not working so used tap using coordinates instead
        Thread.sleep(1000);
        System.out.println("Refreshed Favorites Tab Successfully!");
    }

    public static void UnFollowUser() throws InterruptedException
    {
        System.out.println("Testing Unfollowing Users...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Follwing\"]")));
        System.out.println("Clicking On Unfollow Button...");
        MobileElement Unfollow=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Follwing\"]"));
        Unfollow.click();
        System.out.println("Clicked On Unfollow Button Successfully!");
        System.out.println("Refreshing Favorites Tab...");
        touch.tap(PointOption.point(517, 207)).perform();   //XPath not working so used tap using coordinates instead
        Thread.sleep(1000);
        touch.tap(PointOption.point(170, 200)).perform();   //XPath not working so used tap using coordinates instead
        Thread.sleep(1000);
        System.out.println("Refreshed Favorites Tab Successfully!");
        driver.navigate().back();  //Go back to Photo Stream
        driver.hideKeyboard();
    }

    public static void ViewFavorites()
    {
        System.out.println("Testing Viewing Favorites...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Open comment Section\"]")));
        MobileElement CommentsTaB=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Open comment Section\"]"));
        System.out.println("Clicking On Comments Button...");
        CommentsTaB.click();
        System.out.println("Clicked On Comments Button Successfully!");
        System.out.println("Clicking On Favorites Tab...");
        touch.tap(PointOption.point(170, 200)).perform();   //XPath not working so used tap using coordinates instead
        System.out.println("Clicked On Favorites Tab Successfully!");
        driver.navigate().back();  //Go back to Photo Stream
        driver.hideKeyboard();
    }

    public static void Favorite() throws InterruptedException
    {
        System.out.println("Testing Adding Photo to Favorites...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Press Favorite\"]")));
        MobileElement Like=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Press Favorite\"]"));
        System.out.println("Clicking On Favorite Button...");
        Like.click();
        System.out.println("Clicked On Favorite Button Successfully!");
        System.out.println("Viewing Comments...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Open comment Section\"]")));
        MobileElement CommentsTaB=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Open comment Section\"]"));
        System.out.println("Clicking On Comments Button...");
        CommentsTaB.click();
        Thread.sleep(1000);   //Wait for Comments to Load
        System.out.println("Clicked On Comments Button Successfully!");
        System.out.println("Clicking On Favorites Tab...");
        touch.tap(PointOption.point(170, 200)).perform();   //XPath not working so used tap using coordinates instead
        System.out.println("Clicked On Favorites Tab Successfully!");
        driver.navigate().back();  //Go back to Photo Stream
    }

    public static void UnFavorite() throws InterruptedException
    {
        System.out.println("Testing Removing Photo From Favorites...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Press Favorite\"]")));
        MobileElement Like=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Press Favorite\"]"));
        System.out.println("Clicking On Favorite Button...");
        Like.click();
        System.out.println("Clicked On Favorite Button Successfully!");
        System.out.println("Viewing Comments...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Open comment Section\"]")));
        MobileElement CommentsTaB=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Open comment Section\"]"));
        System.out.println("Clicking On Comments Button...");
        CommentsTaB.click();
        Thread.sleep(1000);   //Wait for Comments to Load
        System.out.println("Clicked On Comments Button Successfully!");
        System.out.println("Clicking On Favorites Tab...");
        touch.tap(PointOption.point(170, 200)).perform();   //XPath not working so used tap using coordinates instead
        System.out.println("Clicked On Favorites Tab Successfully!");
        driver.navigate().back();  //Go back to Photo Stream
    }








public void Teardown()
{

}
}
