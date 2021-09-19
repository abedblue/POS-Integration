/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ggapp;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Abe
 */
public class SquareCorrections {
    
public static final String SAMPLE_XLSX_FILE_PATH = "Inventory.xlsx";    
public static final String SAMPLE_XLSX_FILE_PATH2 = "Inventory2.xlsx";

public static void main(String[] args)
{
try{
Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
Sheet sheet = workbook.getSheetAt(0);
Workbook workbook2 = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH2));
Sheet sheet2 = workbook2.getSheetAt(0);




for(int i = 1; i < sheet.getLastRowNum(); i++){
try{
    Row myInventoryValues = sheet.getRow(i);
        
    if(Integer.parseInt(myInventoryValues.getCell(7).toString()) != 0 && myInventoryValues.getCell(3).toString().equals("Bottle and Can Beer and Cider"))
        {        
        for(int j = 1; j < sheet2.getLastRowNum(); j++)
        {
        Row myInventoryValues2 = sheet2.getRow(j);
        if(myInventoryValues2.getCell(3).toString().equals("Bottle and Can Beer and Cider") && myInventoryValues.getCell(1).toString().equals(myInventoryValues2.getCell(1).toString()))
        {
        if(Integer.parseInt(myInventoryValues.getCell(7).toString()) == Integer.parseInt(myInventoryValues2.getCell(7).toString()))
        {
        System.out.println(myInventoryValues.getCell(1).toString() + " " + myInventoryValues.getCell(7).toString());
        myInventoryValues2.getCell(7).setCellValue("0");
        }
        j = sheet2.getLastRowNum();
        }
        }
        }
    }
    catch(NumberFormatException e)
    {}
}
FileOutputStream fileOut = new FileOutputStream("output.xls");
      workbook2.write(fileOut);
      fileOut.close();
}
catch(Exception e){
e.printStackTrace();
}
}
}
