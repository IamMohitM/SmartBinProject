import okhttp3.OkHttpClient;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Mohit on 17-03-2017.
 */
public class GetBinDataFromSheet {
    public static void main(String args[])
    {

        //PostDataDemo p=new PostDataDemo();
        try{
            FileInputStream excelFile=new FileInputStream("C:\\Users\\Mohit\\Desktop\\Axelta\\Project\\Project Bins\\MyBin2Excel.xlsx");
            Workbook workbook=new XSSFWorkbook(excelFile);
            Sheet dataSheet=workbook.getSheetAt(0);
            Iterator<Row> iterator=dataSheet.iterator();

            String date="",time="";
            double height=0;
            Row rowOne=dataSheet.getRow(0);
            SimpleDateFormat format=new SimpleDateFormat("HH:mm");
            while(iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                if (currentRow.getRowNum() >0) {
//Starts iteration from 2nd row(1st index). 1st row has column names (time values
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();
                        if (currentCell.getColumnIndex() != 0) {
//ie=terates if column is not zero. 0th column has date values
                            int index = currentCell.getColumnIndex();
                            Cell cell = rowOne.getCell(index);
                            Date t = cell.getDateCellValue();
                            time=format.format(t);
                            date=currentRow.getCell(0).getStringCellValue();
                            //System.out.println(time);
                            if (currentCell.getCellTypeEnum() == CellType.STRING) {
                                date = currentCell.getStringCellValue();
                            } else
                                height = currentCell.getNumericCellValue();

                            String data="{\n'time':'" + time+":00" +
                                    "',\n'date':'" + date +
                                    "',\n'bin_height':" + height +
                                    ",\n'device_type':'Generic',\n" +
                                    "'device_key':'BO4WRK88EVNWT5LGFER3',\n" +
                                    "'node_no':'002',\n" +
                                    "'device_no':'Hyd_Bin',\n" +
                                    "'client':'GHMC'\n},";
                            // You can copy paste the output to a json file or write it directly from the program
				//Make sure the json is validated. add the out put between '[]' to make it an array of json objects.
                            System.out.println(data);

                        }
                    }
                }
            }

        }catch (Exception e)
        {
            System.out.println("Exception "+e.getMessage()+" "+e.getStackTrace());
        }
    }
}
