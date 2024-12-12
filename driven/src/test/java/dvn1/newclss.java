package dvn1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.io.File;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class newclss {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.edge.driver","C:\\\\Users\\\\Admin\\\\Downloads\\\\edgedriver_win64\\\\msedgedriver.exe");
        driver = new EdgeDriver();
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String user, String pass) {
        driver.get("https://www.saucedemo.com/v1/");

        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(user);

      	driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(pass);

      	driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();



        // Add assertions here
        System.out.println("Username: " + user);
        System.out.println("Password: " + pass);
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        return getExcelData("C:\\Users\\Admin\\Desktop\\data.xlsx", "Sheet1");
    }

    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row

        int i = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            for (int j = 0; j < colCount; j++) {
                data[i][j] = row.getCell(j).toString();
            }
            i++;
        }

        workbook.close();
        file.close();
        return data;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


