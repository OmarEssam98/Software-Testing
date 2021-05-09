package tests;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseClass
{
    static AppiumDriver<MobileElement> driver;
    static AndroidDriver androidDriver = (AndroidDriver) driver;
    static WebDriverWait wait;
    public static void main(String[]args)
    {
        try
        {
            Login();   //First Log In
            ViewPhoto();   //Then View Photo from Photo stream
            ViewCommentsAndFavoritesFromInside();   //Then view Comments and Favorites from inside then go back to photo
            ClosePhoto();   //Then close currently Viewed Photo
            ViewCommentsAndFavoritesFromOutside();   //Then view Comments and Favorites from outside then go back to photo stream
            ViewMyProfile();   //Then View User's Own Profile
            ViewMyAbout();   //Then view User's Own About Info from inside profile
            UploadPhoto();   //Then Upload Photo
            ViewSearch();   //Then Go to Search Tab
            Search();   //Then Search for "Mazen Ayman"
            ViewPhotoStream();  //Then View Photo Stream
        }
        catch(Exception Exp)
        {
            System.out.println(Exp.getCause());
            System.out.println(Exp.getMessage());
            Exp.printStackTrace();
        }
    }

    public static void Login() throws MalformedURLException
    {
        //////////////////////    Setup    //////////////////////

        DesiredCapabilities caps= new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "10");
        caps.setCapability("appPackage","com.flickr.android");
        caps.setCapability("appActivity","com.yahoo.mobile.client.android.flickr.activity.MainActivity");
        caps.setCapability("deviceName", "FlickrTest");
        caps.setCapability("UDID", "R9AR20ERRAJ");
        //caps.setCapability("systemPort", "8210");
        //caps.setCapability(CapabilityType.NEW_COMMAND_TIMEOUT, 60);
        URL url =new URL("http://127.0.0.1:4723/wd/hub");
        driver=new AppiumDriver<MobileElement>(url,caps);
        androidDriver=new AndroidDriver(caps);
        System.out.println("Application Started Successfully!");

        //////////////////////    Login    //////////////////////

        MobileElement GetStarted=driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Get Started\"]"));
        GetStarted.click();
        System.out.println("Get Started Button Clicked Successfully!");
        wait=new WebDriverWait(driver, 30);   //Initialize WebDriver to Wait 30 seconds for Elements to load
        System.out.println("Waiting for Login Page to load...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.widget.EditText")));  //Execute WebDriver to Wait 30 seconds for Page to load and Login Page to appear
        MobileElement Email=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.widget.EditText"));
        System.out.println("Login Page Loaded Successfully!");
        Email.click();
        System.out.println("Email Address Text Clicked Successfully!");
        Email.setValue("FlickrTestG4@gmail.com");
        System.out.println("Email Address Typed In Successfully!");
        MobileElement LoginNext=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.Button"));
        LoginNext.click();
        System.out.println("Next Button Pressed Successfully!");
        System.out.println("Waiting for Password Text to load...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.view.View/android.widget.EditText")));  //Execute WebDriver to Wait 30 seconds for Password Text to appear
        System.out.println("Password Text Loaded Successfully!");
        MobileElement Password=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View[2]/android.view.View/android.widget.EditText"));
        Password.click();
        System.out.println("Password Text Clicked Successfully!");
        Password.setValue("flickrtest123456");
        System.out.println("Password Typed In Successfully!");
        MobileElement SignIn=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.Button"));
        SignIn.click();
        System.out.println("Sign In Button Clicked Successfully!");
        System.out.println("Signing In...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageButton[@content-desc=\"Activity\"]")));
        System.out.println("Signed In Successfully!");
    }
    public static void ViewPhoto()
    {
        System.out.println("Testing Viewing Photos...");
        MobileElement ViewPhoto=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.view.ViewGroup"));
        ViewPhoto.click();
        System.out.println("Photo Clicked Successfully!");
    }
    public static void ClosePhoto()
    {
        System.out.println("Testing Closing Photo...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ImageButton")));
        MobileElement ClosePhoto=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ImageButton"));
        ClosePhoto.click();
        System.out.println("Photo Closed Successfully!");
    }
    public static void ViewPhotoStream()
    {
        System.out.println("Testing Viewing Photo Stream...");
        MobileElement ViewStream=driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Activity\"]"));
        ViewStream.click();
        System.out.println("Photo Stream Viewed Successfully!");
    }
    public static void ViewCommentsAndFavoritesFromOutside()
    {
        System.out.println("Testing Viewing Comments From Outside...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.FrameLayout/android.view.View")));
        MobileElement ViewComments=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.FrameLayout/android.view.View"));
        ViewComments.click();
        System.out.println("Comments Viewed Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView[1]")));  //Execute WebDriver to Wait 30 seconds for Page to load and Comments and Favorites to appear
        System.out.println("Testing Viewing Favorites From Outside...");
        MobileElement ViewFavorites=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView[1]"));
        ViewFavorites.click();
        System.out.println("Favorites Viewed Successfully!");
        System.out.println("Going Back To Photo Stream...");
        MobileElement GoBackToPhotoStream=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ImageButton"));
        GoBackToPhotoStream.click();
        System.out.println("Went Back To Photo Successfully!");
    }
    public static void ViewCommentsAndFavoritesFromInside()
    {
        System.out.println("Testing Viewing Comments From Inside...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.view.View")));
        MobileElement ViewComments=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.view.View"));
        ViewComments.click();
        System.out.println("Comments Viewed Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView[1]")));  //Execute WebDriver to Wait 30 seconds for Page to load and Comments and Favorites to appear
        System.out.println("Testing Viewing Favorites From Inside...");
        MobileElement ViewFavorites=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView[1]"));
        ViewFavorites.click();
        System.out.println("Favorites Viewed Successfully!");
        System.out.println("Going Back To Photo...");
        MobileElement GoBackToPhoto=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ImageButton"));
        GoBackToPhoto.click();
        System.out.println("Went Back To Photo Successfully!");
    }
    public static void ViewMyProfile()
    {
        System.out.println("Testing Viewing User's Own Profile...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageButton[@content-desc=\"Profile\"]")));
        MobileElement ViewMyProfile=driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Profile\"]"));
        ViewMyProfile.click();
        System.out.println("Profile Viewed Successfully!");
    }
    public static void ViewMyAbout()
    {
        System.out.println("Testing Viewing User's Own About Info...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView[1]")));
        MobileElement ViewMyAbout=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView[1]"));
        ViewMyAbout.click();
        System.out.println("About Viewed Successfully!");
    }
    public static void UploadPhoto()
    {
        System.out.println("Testing uploading photo from camera roll...");
        System.out.println("Going to camera roll tab...");
        MobileElement CameraRoll=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView[3]"));
        CameraRoll.click();
        System.out.println("Clicked on Camera Roll tab Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button")));
        MobileElement UploadNow=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button"));
        UploadNow.click();
        System.out.println("Clicked on Upload Now Successfully!");
        System.out.println("Allowing Storage Access...");
        System.out.println("Allowing Taking Pictures And Recording Videos...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout")));
        MobileElement Allow1=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.Button[1]"));
        Allow1.click();
        System.out.println("Allowed Taking Pictures And Recording Videos Successfully!");
        System.out.println("Allowing Access to Photos, Media and Files On Device...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout")));
        MobileElement Allow2=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.Button[1]"));
        Allow2.click();
        System.out.println("Allowed Access to Photos, Media and Files Successfully!");
        System.out.println("Allowing Recording Audio...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout")));
        MobileElement Allow3=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.Button[1]"));
        Allow3.click();
        System.out.println("Allowed Recording Audio Successfully!");
        System.out.println("Clicking on Camera Section...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout")));
        MobileElement CameraPhotos=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout"));
        CameraPhotos.click();
        System.out.println("Opened Photos taken by camera Successfully!");
        System.out.println("Selecting First Photo...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.GridView/android.widget.FrameLayout/android.widget.ImageView")));
        MobileElement FirstPhoto=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.GridView/android.widget.FrameLayout/android.widget.ImageView"));
        FirstPhoto.click();
        System.out.println("Clicked on first photo Successfully!");
        System.out.println("Clicking on Done...");
        MobileElement Done=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[2]"));
        Done.click();
        System.out.println("Clicked on Done Successfully!");
        System.out.println("Clicking on Next...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.Button")));
        MobileElement Next=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.Button"));
        Next.click();
        System.out.println("Clicked on Next Successfully!");
        System.out.println("Clicking on Post...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.Button")));
        MobileElement Post=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.Button"));
        Post.click();
        System.out.println("Photo Uploaded Successfully!");
    }
    public static void ViewSearch()
    {
        System.out.println("Testing going to Search tab...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageButton[@content-desc=\"Search\"]")));
        MobileElement Search=driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Search\"]"));
        Search.click();
        System.out.println("Clicked on Search tab Successfully!");
    }
    public static void Search()
    {
        System.out.println("Testing Searching...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]")));
        MobileElement SearchBar=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.EditText"));
        SearchBar.click();
        System.out.println("Clicked on Search bar Successfully!");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView[2]")));
        MobileElement People=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView[2]"));
        System.out.println("Clicking on People tab...");
        People.click();
        System.out.println("Clicked on People tab Successfully!");
        MobileElement SearchBarClicked=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.EditText"));
        SearchBarClicked.setValue("Mazen Ayman");
        System.out.println("Set Name to search for Successfully!");
        //androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
        //System.out.println("Pressed Enter Key");
        //System.out.println("Searching...");
    }






public void Teardown()
{

}
}
