package otc;

import helpers.FileUtils;
import org.junit.jupiter.api.*;

import java.util.*;

import static com.codeborne.selenide.Selenide.open;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("OTC: поиск товаров и экспорт в CSV")
public class OtcTest extends BaseClass {

    List<Map<String, String>> productsTable = new ArrayList<>();

    @Test
    @Order(1)
    @DisplayName("Открытие главной страницы OTC")
    void openMainPage() {
        open("https://otc.ru/");
        otcHomePage.waitSearchString();
    }

    @Test
    @Order(2)
    @DisplayName("Выбор города Краснодар")
    void selectCity() {
        otcHomePage
                .openCityFilter()
                .waitSearchCity()
                .selectCity(config.getProperty("FilterCityKrasnodar"), true)
                .selectCity(config.getProperty("FilterCityMoscow"), false)
                .applyFilter()
                .waitCityFilter(config.getProperty("WaitCityKrasnodar"));
    }

    @Test
    @Order(3)
    @DisplayName("Поиск товара")
    void searchProduct() {
        otcHomePage
                .setSearchValue(config.getProperty("ProductValue1"))
                .pressSearch()
                .waitSearchCatalog();
    }

    @Test
    @Order(4)
    @DisplayName("Ожидание загрузки каталога")
    void waitCatalog() {
        otcCatalogPage.waitListProduct();
    }

    @Test
    @Order(5)
    @DisplayName("Сбор данных товаров")
    void collectProducts() {

        for (int i = 0; i < otcCatalogPage.getProductsCount(); i++) {
            var product = otcCatalogPage.getProduct(i);

            Map<String, String> row = new HashMap<>();
            row.put("name", otcCatalogPage.getProductName(product));
            row.put("price", otcCatalogPage.getProductPrice(product));

            productsTable.add(row);
        }
    }

    @Test
    @Order(6)
    @DisplayName("Экспорт результатов в CSV")
    void exportToCsv() {
        FileUtils.saveToCsv(productsTable, "target/products.csv");
    }
}
