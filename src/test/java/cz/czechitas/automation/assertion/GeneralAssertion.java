package cz.czechitas.automation.assertion;

import cz.czechitas.automation.ElementFinder;
import org.openqa.selenium.WebDriver;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Application specific assertions
 *
 * @author Adam Abbod
 * @since 1.0.0
 */
@ParametersAreNonnullByDefault
public final class GeneralAssertion {

    private final ElementFinder elementFinder;
    private final WebDriver driver;

    public GeneralAssertion(ElementFinder elementFinder, WebDriver driver) {
        this.elementFinder = Objects.requireNonNull(elementFinder);
        this.driver = Objects.requireNonNull(driver);
    }

    public void checkPageUrl(String url) {
        var urlElement = elementFinder.findByXPath("//a[text()='www.czechitas.cz']");
        assertThat(urlElement.getText()).isEqualTo(url);
    }

    public void checkCurrentUrl(String url) {
        var currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).isEqualTo(url);
    }
}