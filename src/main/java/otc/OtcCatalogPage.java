package otc;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;

public class OtcCatalogPage extends BasePage {

    private static final String PRODUCT_SELECTOR = "div[itemtype='http://schema.org/Product']";
    private static final String NAME_SELECTOR = "a[itemprop='name']";
    private static final String PRICE_SELECTOR = "h3[itemprop='price']";
    // Карточки товаров
    private final ElementsCollection products = $$(PRODUCT_SELECTOR);

    public OtcCatalogPage waitListProduct() {
        products.shouldBe(sizeGreaterThan(0));
        return this;
    }

    //  Метод сбора данных
    public List<Map<String, String>> collectProductsData() {

        List<Map<String, String>> result = new ArrayList<>();
        log.debug("Найдено товаров на странице: {}", products.size());
        for (int i = 0; i < products.size(); i++) {
            // Карточка по индексу
            SelenideElement product = products.get(i);

            String name = product
                    .$(NAME_SELECTOR)
                    .getText();
            log.debug("Название товара #{}: {}", i + 1, name);

            String price = product
                    .$(PRICE_SELECTOR)
                    .getText();
            log.debug("Цена товара #{}: {}", i + 1, price);

            Map<String, String> productData = new HashMap<>();
            productData.put("name", name);
            productData.put("price", price);


            result.add(productData);
        }

        return result;
    }
}
