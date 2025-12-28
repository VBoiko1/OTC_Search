package otcCucumber;

import com.codeborne.selenide.Configuration;
import helpers.ConfigContainer;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Hook {

    @Before

    public void setup() {

        ConfigContainer.getInstance().loadProperties();

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 2);

        options.setExperimentalOption("prefs", prefs);
        // options.addArguments("start-maximized");
        options.addArguments("--start-fullscreen");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        // options.addArguments("--incognito");   Режим инкогнито.

        Configuration.browserCapabilities = options;
    }



    @After
    public void tearDown() {
        closeWebDriver();
    }
}
