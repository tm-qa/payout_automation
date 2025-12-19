package com.qa.turtlemint.pages.CSV_Validatator;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.util.*;

    public class CsvUtils {

        // Read CSV into List<String[]>
        public static List<String[]> readCsv(File file) throws Exception {
            CSVReader reader = new CSVReader(new FileReader(file));
            return reader.readAll();
        }

        // Assert a specific cell (rowIndex, colIndex)
        public static void assertCell(List<String[]> data, int rowIndex, int colIndex, String expected) {
            String actual = data.get(rowIndex)[colIndex].trim();
            if (!actual.equals(expected)) {
                throw new AssertionError("Cell mismatch at row " + rowIndex + ", col " + colIndex +
                        ". Expected: " + expected + ", Actual: " + actual);
            }
        }

        // Assert entire row matches expected values
        public static void assertRow(List<String[]> data, int rowIndex, List<String> expectedRow) {
            String[] actualRow = data.get(rowIndex);
            if (actualRow.length != expectedRow.size()) {
                throw new AssertionError("Row column count mismatch at row " + rowIndex);
            }

            for (int i = 0; i < actualRow.length; i++) {
                if (!actualRow[i].trim().equals(expectedRow.get(i).trim())) {
                    throw new AssertionError("Row mismatch at row " + rowIndex + ", col " + i +
                            ". Expected: " + expectedRow.get(i) + ", Actual: " + actualRow[i]);
                }
            }
        }

        // Assert column contains an expected value anywhere
        public static void assertColumnContains(List<String[]> data, int colIndex, String expected) {
            for (String[] row : data) {
                if (row[colIndex].trim().equals(expected)) {
                    return; // success
                }
            }
            throw new AssertionError("Column " + colIndex + " does not contain expected value: " + expected);
        }

        //****** To Get Latest Downloaded CSV File

        private static final String DOWNLOAD_DIR = "/var/lib/jenkins/workspace/payout"; // Jenkins
//        private static final String DOWNLOAD_DIR = "//Users//rahulpatil//Downloads"; // Local

        public static File getLatestCsvFile() {
            File downloadDir = new File(DOWNLOAD_DIR);

            File[] csvFiles = downloadDir.listFiles((dir, name) -> name.endsWith(".csv"));

            if (csvFiles == null || csvFiles.length == 0) {
                throw new AssertionError("No CSV files found in directory: " + DOWNLOAD_DIR);
            }

            return List.of(csvFiles)
                    .stream()
                    .max(Comparator.comparingLong(File::lastModified))
                    .orElseThrow(() ->
                            new AssertionError("Unable to determine latest CSV file"));
        }

}
