package core;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class CommonWeb {

    public static WebDriver driver;

    public static int timeout = 10;

    public static Logger log = LogManager.getLogger();




    public static void openBrowser(String browser, String url) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/java/driver/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            driver = new InternetExplorerDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("chrome-headless")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
        } else {
            throw new RuntimeException("Browser is not correct");
        }
        driver.get(url);
        log.info("Open browser: " + url);

        maxWindows();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public static WebElement getElement(String xpath) {
        if (!xpath.contains("=")) {
            throw new RuntimeException("Xpath lack of '='");
        }
        String by = xpath.split("=")[0];
        String value = xpath.split("=")[1];
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        if (by.equalsIgnoreCase("id")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
            return driver.findElement(By.id(value));
        } else if (by.equalsIgnoreCase("name")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));
            return driver.findElement(By.name(value));
        } else if (by.equalsIgnoreCase("class") || by.equalsIgnoreCase("classname")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className(value)));
            return driver.findElement(By.className(value));
        } else if (by.equalsIgnoreCase("tag")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(value)));
            return driver.findElement(By.tagName(value));
        } else if (by.equalsIgnoreCase("link")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(value)));
            return driver.findElement(By.linkText(value));
        } else if (by.equalsIgnoreCase("partiallink")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(value)));
            return driver.findElement(By.partialLinkText(value));
        } else if (by.equalsIgnoreCase("css")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(value)));
            return driver.findElement(By.cssSelector(value));
        } else if (by.equalsIgnoreCase("xpath")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(value)));
            return driver.findElement(By.xpath(value));
        } else {
            throw new RuntimeException("Can't find the element");
        }


    }

    public static void click(String xpath) {
        getElement(xpath).click();
    }

    public static void sendKeys(String xpath, String value) {
        getElement(xpath).sendKeys(value);
    }

    public static void clear(String xpath) {
        getElement(xpath).clear();
    }

    public static void quit() {
        driver.quit();
        log.info("Quit browser");
    }

    public static void close() {
        driver.close();
    }

    public static void maxWindows() {
        driver.manage().window().maximize();
    }

    public static void refresh() {
        driver.navigate().refresh();
    }

    public static void back() {
        driver.navigate().back();
    }

    public static void forward() {
        driver.navigate().forward();
    }

    public static void switchToFrame(String xpath) {
        driver.switchTo().frame(getElement(xpath));
    }

    public static void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public static void switchToWindow(String windowName) {
        driver.switchTo().window(windowName);
    }

    public static void rightClick(String xpath) {
        Actions action = new Actions(driver);
        action.contextClick(getElement(xpath)).perform();
    }

    public static void doubleClick(String xpath) {
        Actions action = new Actions(driver);
        action.doubleClick(getElement(xpath)).perform();
    }

    public static void dragAndDrop(String xpath1, String xpath2) {
        Actions action = new Actions(driver);
        action.dragAndDrop(getElement(xpath1), getElement(xpath2)).perform();
    }

    public static void moveToElement(String xpath) {
        Actions action = new Actions(driver);
        action.moveToElement(getElement(xpath)).perform();
    }

    public static void moveToElementAndClick(String xpath) {
        Actions action = new Actions(driver);
        action.moveToElement(getElement(xpath)).click().perform();
    }

    public static void selectValue(String xpath, String value) {
        Select select = new Select(getElement(xpath));
        select.selectByValue(value);
    }

    public static String getText(String xpath) {
        return getElement(xpath).getText();
    }

    public static String getAttribute(String xpath, String attribute) {
        return getElement(xpath).getAttribute(attribute);
    }

    public static String getTitle() {
        return driver.getTitle();
    }

    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public static void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public static void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public static boolean isDisplayed(String xpath) {
        return getElement(xpath).isDisplayed();
    }

    public static void enter() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
    }

    public static void sendEmail() throws IOException, MessagingException {
        Yaml yaml = new Yaml();
        File yamlFile = new File("src/main/java/config/email.yaml");
        Map<String, Object> data = yaml.load(Files.newInputStream(yamlFile.toPath()));
        String receives = (String) data.get("receives");
        String sender = (String) data.get("sender");
        String smtpserver = (String) data.get("smtpserver");
        String email = (String) data.get("email");
        String password = (String) data.get("password");
        String subject = (String) data.get("subject");
        String content = (String) data.get("content");
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", smtpserver);
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receives));
        message.setSubject(subject, "UTF-8");
        message.setContent(content, "text/html;charset=UTF-8");
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(content);
        MimeBodyPart messageBodyPart1 = new MimeBodyPart();
        String filename = "src/main/test/java/reports/report.html";
        DataSource source = new FileDataSource(filename);
        messageBodyPart1.setDataHandler(new DataHandler(source));
        messageBodyPart1.setFileName(filename);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(messageBodyPart1);
        message.setContent(multipart);
        Transport transport = session.getTransport();
        transport.connect(email, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static byte[] takeScreenshotAsBytes() throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return FileUtils.readFileToByteArray(src);
    }

    public static void takeScreenshotAsFile(String imageName) throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(currentPath() + "test\\java\\screenshots\\" + cuurentTime() + "_" + imageName + ".png"));
    }

    public static String currentPath() {
        return System.getProperty("user.dir") + "\\src\\";
    }

    public static String cuurentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
        return df.format(new Date());
    }
}

