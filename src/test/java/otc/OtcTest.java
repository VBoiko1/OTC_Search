package otc;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
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
                .selectCity("Москва", false)
                .applyFilter("Краснодар")
                .setSearchValue("Принтер")
                .waitSearchCAtalog();

        List<Map<String, String>> products =
                otcCatalogPage
                        .waitListProduct()
                        .collectProductsData();


        FileUtils.saveToCsv(products, "target/products.csv");

    }
}

