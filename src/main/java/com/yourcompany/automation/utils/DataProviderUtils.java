package com.yourcompany.automation.utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderUtils {
    @DataProvider(name = "loginData")
    public static Object[][] loginData() throws IOException {
        // Path to the CSV file
        String csvFilePath = "src/main/resources/testData.csv";

        // Read the CSV file and extract data
        List<Object[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the CSV line by comma
                String[] values = line.split(",");
                // Add data as Object[] array to the list
                data.add(new Object[]{values[0], values[1], Boolean.parseBoolean(values[2]), values[3], values[4]});
            }
        }

        // Convert list to Object[][] array
        Object[][] dataArray = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i] = data.get(i);
        }

        return dataArray;
    }
}
