import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 {
	WebDriver driver;
	public void test() throws IOException {
		
			driver=new ChromeDriver();
			driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//summary[contains(text(),'Table Data')]")).click();
			JsonNode jsonArray = null;

	            ObjectMapper objectMapper = new ObjectMapper();
	            File file = new File("C:\\Users\\GANGA-PC\\Desktop\\Data.json");
	            jsonArray = objectMapper.readTree(file);
	            String jsonValue;
			for(int i=0;i<jsonArray.size();i++) {
				JsonNode arrayItem = jsonArray.get(i);
	            jsonValue = "Name: " + arrayItem.get("name").asText() +
	                          ", Age: " + arrayItem.get("age").asText() +
	                          ", Gender: " + arrayItem.get("gender").asText() + "\n";
	            driver.findElement(By.xpath("//textarea[@id='jsondata']")).clear(); //Clear the data from textbox if you want to pass data newly
				driver.findElement(By.xpath("//textarea[@id='jsondata']")).sendKeys(arrayItem.asText());
				  driver.findElement(By.name("refreshtable")).click();
				  WebElement element = driver.findElement(By.xpath("//table//tr//td[contains(text(),'"+arrayItem.get
				  ("name").asText() +"')]"));
				  Assert.assertEquals(element.getText(),arrayItem.get
						  ("name").asText()); // We need to use testng assertion but As because of less space in disk unable to install testng library so please consider in real time will definitely work.
				  WebElement element1 = driver.findElement(By.xpath("//table//tr//td[contains(text(),'"+arrayItem.get
				  ("age").asText()+"')]"));
				  Assert.assertEquals(element.getText(),arrayItem.get("age").asText());
				  WebElement element2 = driver.findElement(By.xpath("//table//tr//td[contains(text(),'"+arrayItem.get
				  ("gender").asText()+"')]"));
				  Assert.assertEquals(element.getText(),arrayItem.get("gender").asText());
				 
			}
			
	}

	public static void main(String[] args) throws IOException {
		Test1 t=new Test1();
		t.test();
		
	}

}
