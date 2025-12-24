package otc;

import helpers.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;


public class OtcTest extends BaseClass {

    @Test
    @DisplayName("Поиск товаров по городу Краснодар и экспорт результатов в CSV")
    public void openOTCMainPage() {

        List<Map<String, String>> productsTable = new ArrayList<>();

        String url = "https://otc.ru/";
        open(url);
        otcHomePage
                .waitSearchString()
                .openCityFilter()
                .waitSearchCity()
                .selectCity(config.getProperty("FilterCityKrasnodar"), true)
                .selectCity(config.getProperty("FilterCityMoscow"), false)
                .applyFilter()
                .waitCityFilter(config.getProperty("WaitCityKrasnodar"))
                .setSearchValue(config.getProperty("ProductValue1"))
                .pressSearch()
                .waitSearchCatalog();
        otcCatalogPage.waitListProduct();

        for (int i = 0; i < otcCatalogPage.getProductsCount(); i++) {

            var product = otcCatalogPage.getProduct(i);
            Map<String, String> row = new HashMap<>();
            row.put("name", otcCatalogPage.getProductName(product));
            row.put("price", otcCatalogPage.getProductPrice(product));

            productsTable.add(row);
        }

        FileUtils.saveToCsv(productsTable, "target/products.csv");

    }
}

