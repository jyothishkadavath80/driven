package dvn1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

public class data1 {
	WebDriver driver;
	@Test
	public void cs() throws InterruptedException {
    String excelFilePath = "C:\\Users\\Admin\\Desktop\\data.xlsx";
    String URL="https://www.saucedemo.com/v1/";
   System.setProperty("webdriver.edge.driver","C:\\Users\\Admin\\Downloads\\edgedriver_win64\\msedgedriver.exe");
    EdgeOptions option=new EdgeOptions();
 //  option.addArguments("--disable-web-security");
 //  option.addArguments("--allow-running-insecure-content");
    driver = new EdgeDriver(option);
    //System.setProperty("webdrver.chrome.driver","C:\\Users\\Admin\\Desktop\\chromedriver-win64\\chromedriver.exe");
		//WebDriver driver=new ChromeDriver();
    
    try {
    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

    Workbook workbook = new XSSFWorkbook(inputStream);

    Sheet sheet = workbook.getSheetAt(0);
    
   for (int i = 1; i <= sheet.getLastRowNum(); i++) { 
	   
	   Row row = sheet.getRow(i);

       String user= row.getCell(0).getStringCellValue();

       String pass =row.getCell(1).getStringCellValue();

      driver.get(URL);
      // driver.get("https://www.saucedemo.com/v1/");
      
      driver.manage().window().maximize();

      driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(user);

  	driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(pass);

  	driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();


    Thread.sleep(3000);

    }

    workbook.close();

     inputStream.close();

   } catch (IOException e) {

     e.printStackTrace();

      }

driver.quit();

}}



