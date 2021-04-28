package org.example.tests;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePageTests extends TestBase {
    @Test
    public void  appTest() throws Exception {
        log4j.startTestCase("HomePageappTest");
        System.out.println(homePage.getPageTitle());
 //       takeSnapShot(driver);
        Assert.assertEquals(homePage.getPageTitle(),"Trello","its app not Trello");
        log4j.endTestCase2();
    }

    public static void takeSnapShot(WebDriver driver) throws Exception{

        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Create new file name
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String curDate = formatter.format(date);
        String filePath = ("Screen/screen-" + curDate +".png");

        //Move image file to new destination
        File destFile=new File(filePath);
        File screen = new File("Screen/screen-"+System.currentTimeMillis()+".png");

        //Copy file at destination
      FileUtils.copyFile(SrcFile, destFile);

    }
}
