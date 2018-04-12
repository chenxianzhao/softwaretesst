package cn.tju.scs;

import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.RelationshipReferenceDocument;

public class CheckXlsx {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver","/Users/cxz/chromedriver");
    driver = new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void test123() throws Exception {
	List<Student> list = getStudentList();
	for(Student s:list){
		System.out.println(s.userId);
		System.out.println(s.gitUrl);
	    driver.get("https://psych.liebes.top/st");
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys(s.userId);
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys(s.userId.substring(4));
	    driver.findElement(By.id("submitButton")).click();
	    if(s.gitUrl=="") continue;
	    assertEquals(format(s.gitUrl), format(driver.findElement(By.xpath("//p")).getText()));
	}

    driver.close();
  }
  public String format(String string){
	 string = string.trim();
	 if(string.length()==0) {
		 return string;
	 }
	 int p = string.length();
	 if(string.charAt(p-1)=='/'){
		 return string.substring(0, p-1);
	 }
	 return string;
	
	 
  }
  public List<Student> getStudentList(){
	  List<Student> ret = new ArrayList<Student>();
	  XSSFWorkbook workbook = null;
		try {
	        // ¶ÁÈ¡ExcelÎÄ¼þ
	        InputStream inputStream = new FileInputStream("/Users/cxz/input.xlsx");
	        workbook = new XSSFWorkbook(inputStream);
	        inputStream.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows= sheet.iterator();
		while (rows.hasNext())  
	    {  
	      row = (XSSFRow) rows.next();  
	      Iterator cells = row.cellIterator();  
	      Student tmp = new Student();
	      int cnt = 0;
	      while (cells.hasNext())  
	      {  
	        cell = (XSSFCell) cells.next();  
	        if(cnt==0) {
	        	cell.setCellType(XSSFCell.CELL_TYPE_STRING);
	        	tmp.userId = cell.getStringCellValue();
	        }
	        else
	        	tmp.gitUrl = cell.getStringCellValue();
	        cnt++;
	        
	      } 
	      ret.add(tmp);
	    }  
	  return ret;
  }
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
