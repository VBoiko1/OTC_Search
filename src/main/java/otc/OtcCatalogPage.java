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
        products.shouldBe(sizeGreaterThan(0));
        return this;
    }

    /** Количество товаров */
    public int getProductsCount() {
        return products.size();
    }

    /** Карточка товара по индексу */
    public SelenideElement getProduct(int index) {
        return products.get(index)
                .as("Карточка товара #" + (index + 1));
    }

    /** Название товара */
    public String getProductName(SelenideElement product) {
        return product.$(NAME_SELECTOR)
                .as("Название товара")
                .getText();
    }

    /** Цена товара */
    public String getProductPrice(SelenideElement product) {
        return product.$(PRICE_SELECTOR)
                .as("Цена товара")
                .getText();
    }

}
