package otc;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class OtcHomePage extends BasePage {

    private final SelenideElement searchString = $(".mantine-Input-input")
            .as("Строка поиска");

    private final SelenideElement filterCity = $(".mantine-Center-root")
            .as("Фильтр городов");

    private final SelenideElement searchCity = $("input[placeholder='Найти город']")
            .as("Поиск города");

    private final ElementsCollection allCityCheckbox = $$("div.Checkbox-module__fmEfMG__body")
            .as("Список городов, чек-боксы");

    private final SelenideElement buttonFilled = $x("//button[.//span[text()='Применить']]")
            .as("Кнопка Применить");

    private final SelenideElement buttonSearch = $x("//button[.//span[text()='Найти']]")
            .as("Кнопка Найти");

    private final SelenideElement shouldHaveCity = $("div[class*='SeoRegionSelector-module__']")
            .as("Выбранный город");

    private final SelenideElement tabSearch = $x("//span[normalize-space()='Поиск']")
            .as("Вкладка Поиск");

    public OtcHomePage waitSearchString() {
        log.info("Ожидает видимости строки поиска {}", searchString);
        searchString.shouldBe(visible);
        return this;
    }

    public OtcHomePage setSearchValue(String product) {
        log.info("Ввод значения в строку поиска: {}", product);
        searchString
                .shouldBe(visible)
                .setValue(product);
        log.info("Значение '{}' успешно введено", product);
        return this;
    }

    public OtcHomePage pressSearch() {
        log.info("Нажатие на {}", buttonSearch);
        buttonSearch.click();
        return this;
    }

    public OtcHomePage openCityFilter() {
        log.info("Нажатие на {} ", filterCity);
        filterCity.click();
        return this;
    }

    public OtcHomePage waitSearchCity() {
        log.info("Ожидает видимости строки {}", searchCity);
        searchCity.shouldBe(visible);
        return this;
    }

    public OtcHomePage applyFilter() {
        log.info("Нажатие на {}", buttonFilled);
        buttonFilled.click();
        return this;
    }

    public OtcHomePage waitCityFilter(String city) {
        log.info("Ожидает видимость выбранного города {}",city);
        shouldHaveCity.shouldBe(text(city));
        return this;
    }

    /**
     * Выбирает город из списка чек-боксов по названию.
     */
    public OtcHomePage selectCity(String cityName, Boolean check) {
        log.info("Выбирает город {}, чек-бокса {}", cityName, check);

        allCityCheckbox
                .findBy(text(cityName))
                .$("input")
                .as("Чек-бокс города " + cityName)
                .shouldBe(visible)
                .setSelected(check);

        return this;
    }

    public OtcHomePage waitSearchCatalog() {
        tabSearch.shouldBe(visible);
        return this;
    }
}