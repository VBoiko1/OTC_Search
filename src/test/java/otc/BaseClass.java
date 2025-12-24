package otc;

import com.codeborne.selenide.Configuration;
import helpers.ConfigContainer;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class BaseClass {

    protected final ConfigContainer config;
    OtcHomePage otcHomePage = new OtcHomePage();
    OtcCatalogPage otcCatalogPage = new OtcCatalogPage();

    public BaseClass() {
        ConfigContainer configContainer = ConfigContainer.getInstance();
        configContainer.loadProperties();
        this.config = configContainer;
    }

    @BeforeAll
    public static void setup() {

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();

        prefs.put("profile.default_content_setting_values.geolocation", 2);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        // options.addArguments("--incognito");   Режим инкогнито.

        Configuration.browserCapabilities = options;
    }
}
