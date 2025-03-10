package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGTodo1 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        String buildname = System.getenv("LT_BUILD_NAME") == null ? "Your LT AccessKey"
                : System.getenv("LT_BUILD_NAME");
        // String buildname = System.getenv("LT_BUILD_NAME");

        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "MacOS Catalina");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("browserVersion", "80");
        caps.setCapability("version", "latest");
        caps.setCapability("build", buildname);
        caps.setCapability("fixedIP", "10.253.33.109");
        caps.setCapability("name", m.getName() + " - " + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
        caps.setCapability("tags", Tags);

        System.out.println("========================================");
        System.out.println("---------------u----" + username);
        System.out.println("---------aaa----" + authkey);
        System.out.println("========================================");
        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

    }

    @Test
    public void basicTest() throws InterruptedException {
        String spanText;
        System.out.println("Loading Url");
        System.out.println("Loading Url");

        driver.get("https://lambdatest.github.io/sample-todo-app/");

        System.out.println("Checking Box");
        driver.findElement(By.name("li1")).click();
        Thread.sleep(30000);

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li2")).click();
        Thread.sleep(30000);

        System.out.println("Checking Box");
        driver.findElement(By.name("li3")).click();
        Thread.sleep(30000);

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li4")).click();
        Thread.sleep(30000);

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 6");
        driver.findElement(By.id("addbutton")).click();
        Thread.sleep(30000);

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 7");
        driver.findElement(By.id("addbutton")).click();
        Thread.sleep(30000);

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 8");
        driver.findElement(By.id("addbutton")).click();
        Thread.sleep(30000);

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li1")).click();
        Thread.sleep(30000);

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li3")).click();
        Thread.sleep(30000);

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li7")).click();
        Thread.sleep(30000);

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li8")).click();
        Thread.sleep(30000);

        System.out.println("Entering Text");
        driver.findElement(By.id("sampletodotext")).sendKeys("Get Taste of Lambda and Stick to It");
        Thread.sleep(30000);

        driver.findElement(By.id("addbutton")).click();
        Thread.sleep(30000);

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li9")).click();
        Thread.sleep(30000);

        // Let's also assert that the todo we added is present in the list.

        spanText = driver.findElementByXPath("/html/body/div/div/div/ul/li[9]/span").getText();
        Assert.assertEquals("Get Taste of Lambda and Stick to It", spanText);
        Status = "passed";
        Thread.sleep(30000);

        System.out.println("TestFinished");

    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}