/**
 * Created by Mohit on 24-04-2017.
 */
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostSmartBinData {
    static JSONParser parser;/*
    public static final MediaType JSON=MediaType.parse("application/json;charset=utf-8");
    OkHttpClient client=new OkHttpClient();*/

    public static void main(String args[]) {
        PostDataDemo p=new PostDataDemo();
        String json="";
        parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("C:\\Users\\Mohit\\Desktop\\" +
                    "Bin2.json"));
            //jsonArray.toArray();

                for (Object o :jsonArray) {

                    JSONObject Temp = (JSONObject) o;

                   // System.out.println(Temp);
                    json = "{\n\"device_type\":\"Generic\",\n" +
                            "\"device_key\":\"BO4WRK88EVNWT5LGFER3\",\n" +
                            "\"node_no\":\"002\",\n" +
                            "\"device_no\":\"Hyd_Bin\",\n" +
                            "\"client\":\"GHMC\",\n" +
                            "\"date\":\"" + String.valueOf(Temp.get("date")) + "\",\n" +
                            "\"bin_height\":" + String.valueOf(Temp.get("bin_height")) + ",\n" +
                            "\"time\":\"" + String.valueOf(Temp.get("time")) + "\"\n}\n";

                    String response = p.post("http://aws2.axelta.com/services/data", json);
                    System.out.println(response);

                    System.out.println(json);
                }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    }