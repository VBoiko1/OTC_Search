package otc;

import helpers.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;


public class OtcTest extends BaseClass{

    @Test
    @DisplayName("Открыть сайт , проверка наличия элемента 'Строка поиска'")
    public void openOTCMainPage() {
        String url = "https://otc.ru/";
        open(url);
        otcHomePage
                .waitSearchString()
                .openCityFilter()
                .selectCity(config.getProperty("FilterCityKrasnodar"), true)
                .selectCity(config.getProperty("FilterCityMoscow"), false)
                .applyFilter(config.getProperty("WaitCityKrasnodar"))
                .setSearchValue(config.getProperty("ProductValue1"))
                .waitSearchCatalog();

        List<Map<String, String>> products =
                otcCatalogPage
                        .waitListProduct()
                        .collectProductsData();


        FileUtils.saveToCsv(products, "target/products.csv");

    }
}

