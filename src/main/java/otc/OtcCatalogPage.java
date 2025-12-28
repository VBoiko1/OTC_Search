package otc;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.BasePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;

public class OtcCatalogPage extends BasePage {

    private static final String PRODUCT_CSS = "div[itemtype='http://schema.org/Product']";
    private static final String PRODUCT_NAME_CSS = "a[itemprop='name']";
    private static final String PRICE_NAME_CSS = "h3[itemprop='price']";

    private final ElementsCollection products = $$(PRODUCT_CSS)
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
                .find(By.cssSelector(PRODUCT_NAME_CSS))
                .as("Название товара")
                .getText();
    }

    /**
     * Возвращает цену товара из карточки.
     */
    public String getProductPrice(SelenideElement product) {
        return product
                .find(By.cssSelector(PRICE_NAME_CSS))
                .as("Цена товара")
                .getText();
    }
}