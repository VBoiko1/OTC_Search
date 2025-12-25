package otc;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.BasePage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;

public class OtcCatalogPage extends BasePage {

    private static final String PRODUCT_SELECTOR = "div[itemtype='http://schema.org/Product']";
    private static final String NAME_SELECTOR = "a[itemprop='name']";
    private static final String PRICE_SELECTOR = "h3[itemprop='price']";

    private final ElementsCollection products = $$(PRODUCT_SELECTOR)
            .as("Карточки товаров");

    public OtcCatalogPage waitListProduct() {
        log.info("Ожидает появление списка товаров");
        products.shouldBe(sizeGreaterThan(0));
        return this;
    }

    /**
     * Возвращает количество товаров на странице.
     */
    public int getProductsCount() {
        return products.size();
    }

    /**
     * Возвращает карточку товара по указанному индексу.
     */
    public SelenideElement getProduct(int index) {
        return products
                .get(index)
                .as("Карточка товара #" + (index + 1));
    }

    /**
     * Возвращает название товара из карточки.
     */
    public String getProductName(SelenideElement product) {
        return product
                .$(NAME_SELECTOR)
                .as("Название товара")
                .getText();
    }

    /**
     * Возвращает цену товара из карточки.
     */
    public String getProductPrice(SelenideElement product) {
        return product
                .$(PRICE_SELECTOR)
                .as("Цена товара")
                .getText();
    }
}