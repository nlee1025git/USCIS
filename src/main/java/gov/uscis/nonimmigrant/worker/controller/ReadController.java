package gov.uscis.nonimmigrant.worker.controller;

import org.springframework.ui.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReadController {
    public void getData() {
        String fileName = "/Users/kyu/Desktop/csv1.csv";
        char delimiter = ',';

        List<List<String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))) {
            String line;
            boolean isFirstLine = true;
            List<String> headers = new ArrayList<>();

            while ((line = br.readLine()) != null) {
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

        System.out.println(data);
    }
}
