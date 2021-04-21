package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {
    @Test
    public void  appTest(){
        log4j.startTestCase("HomePageappTest");
        System.out.println(homePage.getPageTitle());
        Assert.assertEquals(homePage.getPageTitle(),"Trello","its app not Trello");
        log4j.endTestCase2();
    }
}
