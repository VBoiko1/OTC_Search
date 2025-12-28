package helpers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileUtils {
    private static final String CSV_HEADER = "name,price\n";

    public static void saveToCsv(List<Map<String, String>> data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(CSV_HEADER);

            for (Map<String, String> item : data) {
                String name = item.get("name");
                String price = item.get("price");
                writer.write(String.format("\"%s\",\"%s\"%n", name, price));
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи CSV файла ", e);
        }
    }
}