package cz.czechitas.automation;

import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;
import java.util.Random;

/**
 * Selenium actions facade for working with browser
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
final class SeleniumActionFacade {

    private final Random random = new Random();

    final PublicMenuAction headerMenu;
    final InternalMenuAction internalMenu;
    final LoginAction loginSection;
    final OrderAction orderSection;
    final OrderDetailAction orderDetailSection;
    final ApplicationAction applicationSection;
    final ApplicationDetail applicationDetailsSection;
    final ProfileAction profileSection;
    final RegisterAction registerSection;

    public SeleniumActionFacade(WebDriver driver) {
        var elementFinder = new ElementFinder(Objects.requireNonNull(driver));
        this.headerMenu = new PublicMenuAction(elementFinder);
        this.internalMenu = new InternalMenuAction(elementFinder);
        this.loginSection = new LoginAction(elementFinder);
        this.orderSection = new OrderAction(elementFinder);
        this.orderDetailSection = new OrderDetailAction(elementFinder);
        this.applicationSection = new ApplicationAction(elementFinder);
        this.applicationDetailsSection = new ApplicationDetail(elementFinder);
        this.profileSection = new ProfileAction(elementFinder);
        this.registerSection = new RegisterAction(elementFinder);
    }

    void waitFor(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }

    @Nonnull
    String generateRandomName(int nameLength) {
        var leftLimit = 97;
        var rightLimit = 122;

        return random.ints(leftLimit, rightLimit + 1)
                .limit(nameLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
    public String generateIco(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // číslo 0–9
            sb.append(digit);
        }

        return sb.toString();
    }

    // v SeleniumActionFacade

    @Nonnull
    public String generatePhoneCz() {
        // vrátí +420 a devítimístné číslo, např. +420777123456
        StringBuilder sb = new StringBuilder("+420");
        for (int i = 0; i < 9; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    @Nonnull
    public String generateEmail(String localPartBase) {
        // localPartBase může být jméno nebo název; odstraní diakritiku a mezery
        var base = localPartBase == null || localPartBase.isBlank()
                ? "user" + random.nextInt(10000)
                : localPartBase.toLowerCase().replaceAll("[^a-z0-9]", "");
        return base + "+" + Math.abs(random.nextInt(1_000_000)) + "@test.cz";
    }

    @Nonnull
    public String generatePersonFirstName(int length) {
        // první písmeno velké, zbytek malé
        String name = generateRandomName(length);
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    @Nonnull
    public String generatePersonLastName(int length) {
        String name = generateRandomName(length);
        // jednoduché zakončení pro české příjmení
        var last = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        if (!last.endsWith("ová") && random.nextBoolean()) {
            last = last + "ová";
        }
        return last;
    }

    @Nonnull
    public String generateCompanyName(int length) {
        // např. "ZŠ " + náhodné slovo
        String base = generateRandomName(length);
        return "ZŠ " + Character.toUpperCase(base.charAt(0)) + base.substring(1);
    }

    @Nonnull
    public String generateStreetAddress() {
        // jednoduchá adresa: "Ulice <číslo>, PSC Město"
        String street = Character.toUpperCase(generateRandomName(6).charAt(0)) + generateRandomName(6).substring(1);
        int number = 1 + random.nextInt(200);
        int psc = 10000 + random.nextInt(90000);
        String city = Character.toUpperCase(generateRandomName(5).charAt(0)) + generateRandomName(5).substring(1);
        return street + " " + number + ", " + psc + " " + city;
    }
}
