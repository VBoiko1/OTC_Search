package otc;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;

public class OtcCatalogPage {

    // Карточки товаров
    private final ElementsCollection products =
            $$("div[itemtype='http://schema.org/Product']");

    public OtcCatalogPage waitListProduct() {
        products.shouldBe(sizeGreaterThan(0));
        return this;
    }

    //  Сбор данных
    public List<Map<String, String>> collectProductsData() {

        List<Map<String, String>> result = new ArrayList<>();

        // Получаем количество найденных товаров
        int productsCount = products.size();

        // Классический цикл for
        for (int i = 0; i < productsCount; i++) {

            // Берём конкретную карточку по индексу
            SelenideElement product = products.get(i);

            // Достаём имя товара
            String name = product
                    .$("a[itemprop='name']")
                    .getText();

            // Достаём цену
            String price = product
                    .$("h3[itemprop='price']")
                    .getText();

            // Складываем данные в Map
            Map<String, String> productData = new HashMap<>();
            productData.put("name", name);
            productData.put("price", price);

            // Добавляем Map в итоговый список
            result.add(productData);
        }

        return result;
    }
}
