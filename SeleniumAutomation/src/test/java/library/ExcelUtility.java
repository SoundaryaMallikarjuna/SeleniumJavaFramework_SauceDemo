package library;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public FileInputStream fip;
	public FileOutputStream fop;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public static void main(String[] args) throws IOException
	{
		ExcelUtility ex=new ExcelUtility("C:\\Soundarya\\eclipse-workspace\\SeleniumAutomation\\TestData\\Testdata.xlsx");
		Object data[][]=ex.twoDArray("loginCredentials");
		for(int i=0;i<data.length;i++)
		{
			for(int j=0;j<data[i].length;j++)
			{
				System.out.println(data[i][j]);
			}
		}	
		
		Object data1[][]= ex.singleRow("loginCredentials");
		
		for(int j=0;j<data1[0].length;j++)
		{
			System.out.println(data1[0][j]);
		}
	
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fip = new FileInputStream(path);
		workbook = new XSSFWorkbook(fip);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		fip.close();
		return rowCount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException {
		fip = new FileInputStream(path);
		workbook = new XSSFWorkbook(fip);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fip.close();
		return cellCount;
	}
	
	public Object[][] twoDArray(String sheet) throws IOException
	{
		int rowNum=getRowCount(sheet);
		int cellNum= getCellCount(sheet, 1);
		Object data[][]= new String[rowNum][cellNum]; //data[5][3]
		for(int i=1;i<rowNum;i++)
		{
			for(int j=0;j<cellNum;j++)
			{
				data[i-1][j]=getCellData(sheet,i,j);
				//data[0][0]=valid, standard_user, secret_sauce, link
			}
		}
		workbook.close();
		return data;		
	}
	
	public Object[][] singleRow(String sheet) throws IOException
	{
		int rowNum=1;
		int cellNum= getCellCount(sheet, rowNum);
		Object data[][]= new String[rowNum][cellNum]; //data[5][3]
		
			for(int j=0;j<cellNum;j++)
			{
				data[0][j]=getCellData(sheet,rowNum,j);
				//data[0][0]=valid, standard_user, secret_sauce, link
			}
			workbook.close();
		
		return data;		
	}

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fip = new FileInputStream(path);
		workbook = new XSSFWorkbook(fip);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter(); // any kind data format
		String data;
		try 
		{
			data = formatter.formatCellValue(cell);
			// return the formatted value of the cell as String
		} 
		catch (Exception e) 
		{
			data = "";
		}
		workbook.close();
		fip.close();
		return data;

	}

}
