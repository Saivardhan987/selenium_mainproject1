package Pages;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.Base;

public class RetrieveItems extends Base{
	
	By fitness = By.xpath("//a[@id='ContextualHotkey_27']");
	By gym = By.xpath("//span[@title='Gym']");
	By opt = By.xpath("//span[@class='meditle1 lng_commn']");
	
	Object[] output = new Object[20];
	Object[][] obj = new Object[20][1];
	public void items() throws IOException {
		logger = report.createTest("Getting the submenu's");
		try {
		openURL("websiteURLKey");	
		driver.findElement(fitness).click();
		driver.findElement(gym).click();
		reportPass("Gym option is clicked");
		Screenshoot("Gym");
		System.out.println("Gym Sub-Menu items :");
		List<WebElement> options = driver.findElements(opt);
		for(WebElement option :options){
		    System.out.println(option.getText());
		}
		int count = 0;
		for (int i=0;i<options.size();i++)
		{
			output[count] = options.get(i).getText();
			obj[count][0] = options.get(i).getText();
			count++;
		}
		
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Fitness Data");
		
		int rows = obj.length;
		int cols = obj[0].length;
		
		for(int k=0;k<rows;k++) {
			XSSFRow row = sheet.createRow(k);
			for(int p=0;p<cols;p++) {
				XSSFCell cell = row.createCell(p);
				Object value = obj[k][p];
				if(value instanceof String) {
					cell.setCellValue((String)value);
				}
				if(value instanceof Integer) {
					cell.setCellValue((Integer)value);
				}
			}
		}
		
		String filePath = ".\\ExcelReport\\FitnessService.xlsx";
		FileOutputStream outstream = new FileOutputStream(filePath);
		workbook.write(outstream);
		outstream.close();
		
		
		reportPass("Sub menus are obtained Successfully.");
		System.out.println("\nExcel report generated Successfully. ");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}
