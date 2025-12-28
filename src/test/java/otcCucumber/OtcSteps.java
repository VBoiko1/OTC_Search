package otcCucumber;

import helpers.ConfigContainer;
import helpers.FileUtils;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import otc.OtcHomePage;
import otc.OtcCatalogPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

public class OtcSteps {

    private final ConfigContainer config;
    private final ScenarioContext context;
    private final OtcHomePage otcHomePage;
    private final OtcCatalogPage otcCatalogPage;

    public OtcSteps() {
        this.config = ConfigContainer.getInstance();
        this.context = new ScenarioContext();
        this.otcHomePage = new OtcHomePage();
        this.otcCatalogPage = new OtcCatalogPage();
    }

    @Когда("Открывает главную страницу OTC")
    public void openMainPage() {
        open("https://otc.ru/");
        otcHomePage.waitSearchString();
    }

    @Когда("Выбирает город Краснодар")
    public void selectCity() {
        otcHomePage
                .openCityFilter()
                .waitSearchCity()
                .selectCity(config.getProperty("FilterCityKrasnodar"), true)
                .selectCity(config.getProperty("FilterCityMoscow"), false)
                .applyFilter()
                .waitCityFilter(config.getProperty("WaitCityKrasnodar"));
    }

    @Когда("Ищет товар Принтер")
    public void searchProduct() {
        otcHomePage
                .setSearchValue(config.getProperty("ProductValue1"))
                .pressSearch()
                .waitSearchCatalog();
    }

    @Тогда("Отображается список товаров")
    public void waitCatalog() {
        otcCatalogPage.waitListProduct();
    }

    @Тогда("Сохраняет результаты поиска в CSV файл")
    public void collectProducts() {

        for (int i = 0; i < otcCatalogPage.getProductsCount(); i++) {
            var product = otcCatalogPage.getProduct(i);

            Map<String, String> row = new HashMap<>();
            row.put("name", otcCatalogPage.getProductName(product));
            row.put("price", otcCatalogPage.getProductPrice(product));

            context.getProductsTable().add(row);
        }
        FileUtils.saveToCsv(context.getProductsTable(), "target/products.csv");
    }

}
