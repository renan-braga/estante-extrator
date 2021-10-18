 package main.java.webdriver;
 
 import io.github.bonigarcia.wdm.WebDriverManager;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.concurrent.TimeUnit;
 import java.util.function.Function;
 import org.openqa.selenium.By;
 import org.openqa.selenium.JavascriptExecutor;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.chrome.ChromeDriver;
 import org.openqa.selenium.chrome.ChromeOptions;
 import org.openqa.selenium.support.ui.WebDriverWait;
 
 
 
 
 
 public class DriverExtrator
 {
   private WebDriver driver;
   
   public DriverExtrator(boolean headless, boolean disableImages) throws IOException {
     WebDriverManager.chromedriver().setup();
     
     ChromeOptions options = new ChromeOptions();
     if (headless) {
       options.addArguments(new String[] { "--headless" });
     } else {
       options.addArguments(new String[] { "--disable-notifications" });
     } 
     if (disableImages) {
       options.addArguments(new String[] { "--disable-gpu" });
       options.addArguments(new String[] { "--blink-settings=imagesEnabled=false" });
     } 
     this.driver = (WebDriver)new ChromeDriver(options);
     this.driver.manage().timeouts().pageLoadTimeout(5L, TimeUnit.MINUTES);
     this.driver.manage().window().maximize();
   }
   
   public void waitForLoad() {
     (new WebDriverWait(this.driver, 60L)).until((Function)(wd -> Boolean.valueOf(((JavascriptExecutor)wd).executeScript("return document.readyState", new Object[0]).equals("complete"))));
   }
 
   
   public boolean existeElemento(String path) {
     return (this.driver.findElements(By.xpath(path)).size() > 0);
   }
   
   public int retornaNumeroElementos(String path) {
     return this.driver.findElements(By.xpath(path)).size();
   }
   
   public void hoverMouseJavaScript(WebElement webElement) {
     String javaScript = "var evObj = document.createEvent('MouseEvents');evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);arguments[0].dispatchEvent(evObj);";
 
     
     ((JavascriptExecutor)this.driver).executeScript(javaScript, new Object[] { webElement });
   }
   
   public WebDriver getDriver() {
     return this.driver;
   }
   
   public void abrirNovaAba() {
     ((JavascriptExecutor)this.driver).executeScript("window.open()", new Object[0]);
   }
   
   public void envioLentoCaracteres(String xpath, String mensagem) {
     WebElement element = this.driver.findElement(By.xpath(xpath));
     for (int i = 0; i < (mensagem.toCharArray()).length; i++) {
       element.sendKeys(new CharSequence[] { (new StringBuilder(String.valueOf(mensagem.charAt(i)))).toString() });
       try {
         TimeUnit.MILLISECONDS.sleep(15L);
       } catch (InterruptedException e) {
         e.printStackTrace();
       } 
     } 
   }
   
   public void mudarParaAba(int aba) {
     ArrayList<String> tabs = new ArrayList<>(this.driver.getWindowHandles());
     this.driver.switchTo().window(tabs.get(aba));
   }
   
   public void clicarSeExiste(String proximaPagina) {
     if (existeElemento(proximaPagina)) {
       this.driver.findElement(By.xpath(proximaPagina)).click();
     }
   }
   
   public boolean elementoVisivel(String path) {
     return this.driver.findElement(By.xpath(path)).isDisplayed();
   }
   
   public boolean elementoComTexto(String path) {
     return (this.driver.findElement(By.xpath(path)).getText() != "" && this.driver.findElement(By.xpath(path)).getText() != null);
   }
 }


