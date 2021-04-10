package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {
    @Test
    public void  appTest(){
        System.out.println(homePage.getPageTitle());
        Assert.assertEquals(homePage.getPageTitle(),"Trello","its app not Trello");
    }
}
