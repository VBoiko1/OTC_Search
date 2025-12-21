package otc;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.BasePage;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OtcHomePage extends BasePage {

    // Поле [Строка поиска]
    private final SelenideElement searchString = $(".mantine-Input-input");

    // Выпадающий список для фильтра по городам
    private final SelenideElement filterCity = $(".mantine-Center-root");

    //Поиск по городам [Найти город]
    private final SelenideElement searchCity = $("input[placeholder='Найти город']");

    // Коллекция всех чекбоксов
    private final ElementsCollection allCityCheckbox = $$("div.Checkbox-module__fmEfMG__body");

    //Кнопка "Применить"
    private final SelenideElement buttonFilled = $x("//button[.//span[text()='Применить']]");

    //Кнопка "Найти"
    private final SelenideElement buttonSearch = $x("//button[.//span[text()='Найти']]");

    //Поле фильтра города
    private final SelenideElement shouldHaveCity = $("div[class*='SeoRegionSelector-module__']");

    public OtcHomePage waitSearchString() {
        searchString.shouldBe(visible);
        return this;
    }

    public OtcHomePage setSearchValue(String product) {
        searchString.shouldBe(visible);
        searchString.setValue(product);
        startSearch();
        return this;
    }

    public OtcHomePage startSearch(){
        buttonSearch.click();
        return this;
    }

    public OtcHomePage openCityFilter(){
        filterCity.click();
        searchCity.shouldBe(visible);
        return this;
    }

    public OtcHomePage applyFilter(String city){
        buttonFilled.click();
        shouldHaveCity.shouldBe(text(city));
        return this;
    }


    // Выбрать любой город по названию
    public OtcHomePage selectCity(String cityName, Boolean check) {
        log.info("Выбирает город {}", cityName);
        log.info("Флаг чек-бокса {}",check);

        allCityCheckbox.findBy(text(cityName))
                .$("input")
                .shouldBe(visible)
                .setSelected(check);

        return this;
    }

    public OtcHomePage waitSearchCatalog(){
        $x("//span[normalize-space()='Поиск']")
                .shouldBe(visible);
        return this;
    }

}
