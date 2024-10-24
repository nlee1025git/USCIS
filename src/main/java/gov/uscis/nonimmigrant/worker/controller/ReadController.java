package gov.uscis.nonimmigrant.worker.controller;

import org.springframework.ui.Model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReadController {
    private List<List<String>> data;

    public ReadController(List<List<String>> data) {
        this.data = data;
    }

    public ReadController() {
        this.data = new ArrayList<>();
    }

    public List<List<String>> getExcel() {
        String fileName = "/Users/kyu/Desktop/spring/o_out.xlsx";

        try (FileInputStream fis = new FileInputStream(fileName)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<List<String>> getData() {
        String fileName = "/Users/kyu/Desktop/spring/o_out_string.csv";
//        String fileName = "/Users/kyu/Desktop/csv1.csv";
        char delimiter = ',';

        try (BufferedReader br = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))) {
            String line;
            boolean isFirstLine = true;
            List<String> headers = new ArrayList<>();

//            int count = 10;
//            while (count != 0 && (line = br.readLine()) != null) {
//                count--;
            while ((line = br.readLine()) != null) {
                line = line.substring(1, line.length() - 24);
                String[] values = line.split(Character.toString(delimiter));

                if (isFirstLine) {
                    for (String header: values) {
                        headers.add(header);
                    }
                    isFirstLine = false;
                } else {
                    List<String> row = new ArrayList<>();
                    for (String value : values) {
                        row.add(value);
                    }
                    data.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();;
        }
        return data;
    }
}
