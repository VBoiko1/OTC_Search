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


public class OtcTest {

    OtcHomePage otcHomePage = new OtcHomePage();
    OtcCatalogPage otcCatalogPage = new OtcCatalogPage();

    @BeforeAll
    public static void setup() {

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();

        prefs.put("profile.default_content_setting_values.geolocation", 2);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        // options.addArguments("--incognito"); // Режим инкогнито.

        // Применяем опции к Selenide
        Configuration.browserCapabilities = options;
    }

    @Test
    @DisplayName("Открыть сайт , проверка наличия элемента 'Строка поиска'")
    public void openOTCMainPage() {
        String url = "https://otc.ru/";
        open(url);
        otcHomePage
                .waitSearchString()
                .openCityFilter()
                .selectCity("Краснодар", true)
                .selectCity("Москва", false)
                .applyFilter("Краснодар")
                .setSearchValue("Принтер")
                .waitSearchCAtalog();

        List<Map<String, String>> products =
                otcCatalogPage
                        .waitListProduct()
                        .collectProductsData();


        FileUtils.saveToCsv(products, "target/products.csv");
        //products.forEach(System.out::println);
    }
}

