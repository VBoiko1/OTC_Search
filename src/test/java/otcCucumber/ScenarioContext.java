package otcCucumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScenarioContext {
    private final List<Map<String, String>> productsTable = new ArrayList<>();

    public List<Map<String, String>> getProductsTable() {
        return productsTable;
    }
}
