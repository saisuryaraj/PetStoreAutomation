package api.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility{
	public  FileInputStream fi;
	public  FileOutputStream fo;
	public  XSSFWorkbook wb;
	public  XSSFSheet ws;
	public  XSSFRow row;
	public  XSSFCell cell;
	public  CellStyle style;
	String path;
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}
	
	public  int getRowCount(String sheetName) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetName);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}
	
	public  int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetName);
		row=ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	public  String getCellData(String sheetName,int rownum,int column) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetName);
		row=ws.getRow(rownum);
		cell = row.getCell(column);
		
		String data;
		try {
			//data=cell.toString();
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);//returns the formatted value of cell as String regardless of cell type
		}
		catch(Exception e)
		{
			data ="";
		}
		
		wb.close();
		fi.close();
		return data;
	}
	
	public  void setCellData(String sheetName,int rownum,int column,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())
		{
			wb=new XSSFWorkbook();
			fo= new FileOutputStream(path);
			wb.write(fo);
		}
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetName);
		row=ws.getRow(rownum);
		cell = row.createCell(column);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public  void fillGreenColor(String xlfile,String xlsheet,int rownum,int column) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws= wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell = row.getCell(column);
		
		style =wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public  void fillRedColor(String xlfile,String xlsheet,int rownum,int column) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws= wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell = row.getCell(column);
		
		style =wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
}
