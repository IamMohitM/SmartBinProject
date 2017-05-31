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
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();
                        if (currentCell.getColumnIndex() != 0) {
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
                            /*String response=p.post("http://aws2.axelta.com/services/data", data);
                            System.out.println(response);*/
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
