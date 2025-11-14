package cz.czechitas.automation;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

/**
 * Register specific selenium actions
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@ParametersAreNonnullByDefault
final class RegisterAction {

    private final ElementFinder elementFinder;

    RegisterAction(ElementFinder elementFinder) {
        this.elementFinder = Objects.requireNonNull(elementFinder);
    }

    void insertFirstAndLastName(String firstName, String lastName) {
        var nameInput = elementFinder.findByXPath("//*[@id=\"name\"]");
        nameInput.sendKeys(firstName + " " + lastName);
    }

    void insertEmail(String email) {
        var emailInput = elementFinder.findByXPath("//*[@id=\"email\"]");
        emailInput.sendKeys(email);
    }

    void insertPassword(String password) {
        var passwordInput = elementFinder.findByXPath("//*[@id=\"password\"]");
        passwordInput.sendKeys(password);
    }

    void insertPasswordConfirmation(String password) {
        var passwordConfirmationInput = elementFinder.findByXPath("//*[@id=\"password-confirm\"]");
        passwordConfirmationInput.sendKeys(password);
    }

    void clickRegisterButton() {
        var registerButton = elementFinder.findByXPath("/html/body/div/div/div/div/div/div[2]/form/div[5]/div/button");
        registerButton.click();
    }
}
