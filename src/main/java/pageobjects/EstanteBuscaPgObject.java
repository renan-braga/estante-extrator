package main.java.pageobjects;

import java.io.IOException;
import main.java.utils.Resizer;
import main.java.webdriver.DriverExtrator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class EstanteBuscaPgObject{
	
	private static final String PATH_IMG = "(//div[@class='livros']//img)[1]";
	private static final String URL_BUSCA = "https://www.estantevirtual.com.br/busca?utf8=%E2%9C%93&q=";
	private DriverExtrator extrator;
	private WebDriver driver;

	public EstanteBuscaPgObject() {
		try {
			this.extrator = new DriverExtrator(false, false);
			this.driver = this.extrator.getDriver();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public String retornaImagemBusca(String busca) {
		this.driver.get("https://www.estantevirtual.com.br/busca?utf8=%E2%9C%93&q=" + busca);
		this.extrator.waitForLoad();
		String img = this.driver.findElement(By.xpath("(//div[@class='capa'])[1]/div[2]/img")).getAttribute("src");
		img = Resizer.resize("anbdrnlnfq", "350", img);
		return img.contains("sem_capa") ? "" : img;
	}

	public void finalizar() {
		this.driver.quit();
		this.driver.close();
	}
}


