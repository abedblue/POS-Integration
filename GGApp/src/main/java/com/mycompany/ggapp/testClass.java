/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.ggapp;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Abe
 */
public class testClass {
     public static final String SAMPLE_XLSX_FILE_PATH = "Inventory.xlsx";

    public static void main(String[] args) throws IOException, InvalidFormatException {

        System.setProperty("webdriver.chrome.driver","ChromeDriver\\chromedriver.exe");
        
        WebDriver driver = new ChromeDriver();
        
        driver.get("https://www.beermenus.com/sessions/new");

        WebElement email = driver.findElement(By.id("email"));
        WebElement pwd = driver.findElement(By.id("password"));
        
        
        email.sendKeys("info@thegreengrowler.com");
        pwd.sendKeys("lmnop1234");
        
        
        try{
        while(!driver.getCurrentUrl().equals("https://www.beermenus.com/places/2682-green-growler-grocery/menu/edit"))
        {Thread.sleep(1000);}
        //Thread.sleep(10000);
        }
        catch(Exception e){}
        System.out.println("we on it");
        
        List<WebElement> myParentElements = driver.findElements(By.className("pure-f"));
        
        int numParents = myParentElements.size();
        System.out.println(numParents);
        
        for(int i = numParents - 1; i >= 0; i--)
        {   
        WebElement body = myParentElements.get(i).findElement(By.className("pure-f-body"));
        List<WebElement> subElement = body.findElements(By.className("pure-f"));
        if(!(subElement.size()==1))
        {
        myParentElements.remove(i);
        }
        }
        
        System.out.println(myParentElements.size());
        numParents = myParentElements.size();
        
        
        
        
        String myNewString = driver.getPageSource();
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        //System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
        
        Sheet sheet = workbook.getSheetAt(0);
        
        //Document doc = Jsoup.connect("https://www.beermenus.com/places/2682-green-growler-grocery/menu/edit").get();
        //String myCorrectedDoc = tempDoc.toString().replaceAll("\n", " ");
        
        String myCorrectedDoc = myNewString;
        myCorrectedDoc = myCorrectedDoc.replaceAll("\t", " ");
        
        System.out.println("beers to be removed");
        
        for(int i = 0; i < sheet.getLastRowNum(); i++)
        {
        Row myInventoryValues = sheet.getRow(i);
        
        try
        {
        if(Integer.parseInt(myInventoryValues.getCell(7).toString()) == 0 && myInventoryValues.getCell(3).toString().equals("Bottle and Can Beer and Cider"))
        {        
        if(myCorrectedDoc.toLowerCase().contains(myInventoryValues.getCell(1).toString().toLowerCase()) || ((myInventoryValues.getCell(1).toString().toLowerCase().contains(" single can")) && (myCorrectedDoc.toLowerCase().contains(myInventoryValues.getCell(1).toString().substring(0, myInventoryValues.getCell(1).toString().toLowerCase().indexOf(" single can"))))) || ((myInventoryValues.getCell(1).toString().toLowerCase().contains(" single bottle")) && (myCorrectedDoc.toLowerCase().contains(myInventoryValues.getCell(1).toString().substring(0, myInventoryValues.getCell(1).toString().toLowerCase().indexOf(" single bottle"))))))
        {
        System.out.println(myInventoryValues.getCell(1).toString());
        for(int k = 0; k < numParents; k++)
        {
        List<WebElement> test1 = myParentElements.get(k).findElements(By.xpath(".//*"));
        boolean goalElement = false;
        for(int j = 0; j < test1.size() && !goalElement; j++)
        {
        if(test1.get(j).getText().toLowerCase().contains(myInventoryValues.getCell(1).toString().toLowerCase()) || (myInventoryValues.getCell(1).toString().toLowerCase().contains(" single can") && test1.get(j).getText().toLowerCase().contains(myInventoryValues.getCell(1).toString().toLowerCase().substring(0, myInventoryValues.getCell(1).toString().toLowerCase().indexOf(" single can")))) || (myInventoryValues.getCell(1).toString().toLowerCase().contains(" single bottle") && test1.get(j).getText().toLowerCase().contains(myInventoryValues.getCell(1).toString().toLowerCase().substring(0, myInventoryValues.getCell(1).toString().toLowerCase().indexOf(" single bottle")))))
        {
        goalElement = true;
        System.out.println("found it");
        }
        }
        if(goalElement)
        {
     /* for(int j = 0; j < test1.size(); j++)
        {
        if(test1.get(j).getAttribute("type") != null && test1.get(j).getAttribute("type").equals("checkbox"))
        {
        //test1.get(j).click();
        }
        } */
        }
        }
        }
        }
        
        }
        catch(NumberFormatException e){}
        }
        
        System.out.println("_________");
        System.out.println("_________");
        System.out.println("_________");
        System.out.println("_________");
        System.out.println("beers to be added");
        
        for(int i = 0; i < sheet.getLastRowNum(); i++)
        {
        Row myInventoryValues = sheet.getRow(i);
        
        try
        {
        if(Integer.parseInt(myInventoryValues.getCell(7).toString()) > 0 && myInventoryValues.getCell(3).toString().equals("Bottle and Can Beer and Cider"))
        {        
        if(!(myCorrectedDoc.toLowerCase().contains(myInventoryValues.getCell(1).toString().toLowerCase()) || ((myInventoryValues.getCell(1).toString().toLowerCase().contains(" single can")) && (myCorrectedDoc.toLowerCase().contains(myInventoryValues.getCell(1).toString().substring(0, myInventoryValues.getCell(1).toString().toLowerCase().indexOf(" single can"))))) || ((myInventoryValues.getCell(1).toString().toLowerCase().contains(" single bottle")) && (myCorrectedDoc.toLowerCase().contains(myInventoryValues.getCell(1).toString().substring(0, myInventoryValues.getCell(1).toString().toLowerCase().indexOf(" single bottle")))))))
        {
        System.out.println(myInventoryValues.getCell(1).toString());
        }
        }
        
        }
        catch(NumberFormatException e){}
        }
        
    }
}
