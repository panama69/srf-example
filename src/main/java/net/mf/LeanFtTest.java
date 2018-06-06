package net.mf;

import static org.junit.Assert.*;

import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;
import org.junit.*;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.web.*;
import com.hp.lft.verifications.*;

import unittesting.*;

public class LeanFtTest extends UnitTestClassBase {

    public LeanFtTest() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new LeanFtTest();
        globalSetup(LeanFtTest.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws GeneralLeanFtException {
        Browser browser;
        String runRemote;
        String tags[] = {"flynn","corndog","remote"};

        // To execute the test as a remote SRF test, execute the maven project using the following:
        // clean test -DrunRemote=true
        runRemote = System.getProperty("runRemote");
        if (runRemote != null && runRemote.contentEquals("true")){
            BrowserDescription bd = new BrowserDescription();
            //bd.setType(BrowserType.INTERNET_EXPLORER); //or: bd.set("type", BrowserType.INTERNET_EXPLORER) or: bd.set("type", "INTERNET_EXPLORER")
            bd.setType(BrowserType.CHROME);
            bd.set("version", "latest");
            bd.set("osType", "Windows");
            bd.set("osVersion", "10");
            bd.set("testName", "SRF Remote Execution");
            bd.set("tags",tags);
            browser = SrfLab.launchBrowser(bd);

        }else {
            browser = BrowserFactory.launch(BrowserType.CHROME);
        }
        browser.navigate("http://www.advantageonlineshopping.com");
        Link tABLETSLink = browser.describe(Link.class, new LinkDescription.Builder()
                .innerText("TABLETS")
                .tagName("SPAN").build());
        Verify.areEqual("TABLETS", tABLETSLink.getInnerText());
        Assert.assertEquals("My tst", "TABLETS", tABLETSLink.getInnerText());
        browser.closeAllTabs();
    }

}