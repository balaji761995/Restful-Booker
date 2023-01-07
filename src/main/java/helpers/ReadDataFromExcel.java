package helpers;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadDataFromExcel {

    //below method read the data from excel and return row values in array for objects

    public static Object[] readTestDataFromExcel(String filePath, String sheetName) throws IOException {

        Object[] testData;
        InputStream path = Files.newInputStream(Paths.get(filePath));

        XSSFWorkbook workbook = new XSSFWorkbook(path);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        XSSFRow headerRow = sheet.getRow(0);

        int rowCount = sheet.getLastRowNum();    //Get the last row with value
        int colCount = headerRow.getLastCellNum();   //Get the last column with value
        testData = new Object[rowCount];

        for(int i = 0; i < rowCount; i++){       //Loop works on rows
            List<String> rowValue = new ArrayList<>();
            XSSFRow row = sheet.getRow(i+1);
            for(int j = 0; j < colCount; j++){    //Loop works on column
                XSSFCell cellValue = row.getCell(j);
                String value = new DataFormatter().formatCellValue(cellValue);
                if(value.isEmpty()){
                    rowValue.add(null);
                }else{
                    rowValue.add(value);
                }
            }
            testData[i] = rowValue;
        }
        return testData;
    }

}
