package cz.czechitas.automation.assertion;

import cz.czechitas.automation.ElementFinder;
import org.openqa.selenium.WebDriver;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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

    public void checkToastContainsText(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("toast-message")
        ));
        assertThat(toast.getText()).contains(expectedText);
    }

}