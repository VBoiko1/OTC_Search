package otc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileUtils {

    public static void saveToCsv(List<Map<String, String>> data, String filePath) {

        try (FileWriter writer = new FileWriter(filePath)) {


            writer.write("name,price\n");


            for (Map<String, String> item : data) {
                writer.write(
                        item.get("name") + "," +
                                item.get("price") + "\n"
                );
            }

        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи CSV файла", e);
        }
    }
}
