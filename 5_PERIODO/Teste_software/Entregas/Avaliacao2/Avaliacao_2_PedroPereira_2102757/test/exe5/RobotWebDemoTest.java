package exe5;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;

class RobotWebDemoTest {

	private static WebDriver d;
	
	@BeforeAll
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver","C:\\\\Users\\\\user\\\\Documents\\\\Teste_software\\\\Aula_23_04\\\\lib\\\\chromedriver.exe");
		d = new ChromeDriver();
		d.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		d.manage().window().maximize();
	}
	
	
	@BeforeEach
	public void beforeEach() {
		d.get("https://andreendo.github.io/robot-webdemo/");
	}
	
	@AfterAll
	public static void afterAll() {
		d.close();
	}
	
	@Test
	void testCaseA() {
		//Realizar o login com o user name ‘demo’ e password ‘mode’
		
		d.findElement(By.xpath("//*[@id=\"username_field\"]")).sendKeys("demo");
		d.findElement(By.xpath("//*[@id=\"password_field\"]")).sendKeys("mode");
		d.findElement(By.xpath("//*[@id=\"login_button\"]")).click();
		
		//verificar se a página	seguinte é a “welcome page”
		
		var h1 = d.findElement(By.xpath("//*[@id=\"container\"]/h1"));
		
		assertEquals("Welcome Page", h1.getText());
		
		//clicar no logout 
		
		d.findElement(By.xpath("//*[@id=\"container\"]/p/a")).click();
		
		//verificar se voltou para a página inicial.
		
		h1 = d.findElement(By.xpath("//*[@id=\"container\"]/h1"));
		
		assertEquals("Login Page", h1.getText());
	}
	
	@Test
	void testCaseB() {
		//Tentar realizar o login com dados inválidos
		
		d.findElement(By.xpath("//*[@id=\"username_field\"]")).sendKeys("test");
		d.findElement(By.xpath("//*[@id=\"password_field\"]")).sendKeys("test");
		d.findElement(By.xpath("//*[@id=\"login_button\"]")).click();
		
		//verificar se a página seguinte é uma página de erro
		
		var h1 = d.findElement(By.xpath("//*[@id=\"container\"]/h1"));
		
		assertEquals("Error Page", h1.getText());
	}
	
	@Test
	void testCaseC() {
		//Clicar no link para o GitHub na parte inferior da tela. 
		
		d.findElement(By.xpath("/html/body/div[2]/center/a")).click();
		
		//Na tela seguinte, verifique se a barra lateral direita (abaixo do “About”) 
		//contém a frase “Robot Framework web testing demo using SeleniumLibrary”. 
		
		var p = d.findElement(By.xpath("//*[@id=\"repo-content-pjax-container\"]/div/div[2]/div[2]/div/div[1]/div/p"));
		
		assertEquals("Robot Framework web testing demo using SeleniumLibrary", p.getText());
	}

}
