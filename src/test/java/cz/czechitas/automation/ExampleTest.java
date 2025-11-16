package cz.czechitas.automation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Example test class for functionality showcase
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class ExampleTest extends TestRunner {

    @Test
    void contactsPageUrlTest() {
        browser.headerMenu.goToContactsSection();
        asserter.generalSection.checkPageUrl("www.czechitas.cz");
    }

    @Test
    void successfulLoginTest() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail("da-app.admin@czechitas.cz");
        browser.loginSection.insertPassword("Czechitas123");
        browser.loginSection.clickLoginButton();
        asserter.applicationSection.checkIsLoggedIn();
    }

    // paramertized test - find out what is wrong with this test
    @ParameterizedTest()
    @ValueSource(strings = {"123456789", "ASDFBVC", "123"})
    void icoFieldTest(String icoValue) {
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO(icoValue);
    }

    // DATBP25C-10
    // User Registration new account

    @Test
    void succesfullRegistration() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.clickRegistrationMenuLink();

        var randomName = browser.generateRandomName(5);
        var randomSurname = browser.generateRandomName(6);
        var fullName = randomName + " " + randomSurname;
        var randomEmail = randomName.toLowerCase() + "@seznam.cz";

        browser.loginSection.insertNameAndSurname(fullName);
        browser.loginSection.insertEmail(randomEmail);
        browser.loginSection.insertPassword("Test1234");
        browser.loginSection.insertPasswordConfirmation("Test1234");
        browser.loginSection.clickSubmitRegistrationButton();

        // ověření, že uživatel je přihlášen
        asserter.loginSection.checkUserIsLoggedIn(fullName);

        // ověření, že seznam přihlášek je prázdný
        asserter.applicationSection.checkApplicationsTableIsEmpty();

        browser.loginSection.logout();

        // ověření, že je uživatel odhlášen
        asserter.loginSection.checkUserIsNotLoggedIn();
    }

    // Login process

    @Test
    void succesfullLogin() {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail("lotty.cici@seznam.cz");
        browser.loginSection.insertPassword("Heslo1234");
        browser.loginSection.clickLoginButton();

    // ověření, že uživatel je přihlášen
        asserter.applicationSection.checkIsLoggedIn();

        browser.loginSection.logout();

    // ověření, že je uživatel odhlášen
        asserter.loginSection.checkUserIsNotLoggedIn();
    }

    // Pomocná metoda pro login
    private void login(String email, String password) {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(email);
        browser.loginSection.insertPassword(password);
        browser.loginSection.clickLoginButton();
    }

    // Pomocná metoda na generování data narození
    private String generateValidBirthdate() {
        var random = new java.util.Random();
        int year = random.nextInt(2022 - 2006 + 1) + 2006; // mezi 2006 a 2022
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1; // bezpečně do 28 kvůli únoru

        return String.format("%02d.%02d.%d", day, month, year);
    }

     //DATBP25C-27
    // Test vytvoření přihlášky jako Admin

    @Test
    void succesfullCreateApplicationAsAdmin() {
        login("da-app.admin@czechitas.cz","Czechitas123");

        var randomPrijmeni = browser.generateRandomName(6);
        var randomName = browser.generateRandomName(5);
        var birthdate = generateValidBirthdate();

        browser.applicationSection.selectProgrammingSection();
        browser.applicationSection.clickCreatePythonApplicationButton();  

        browser.applicationDetailsSection.selectTerm("02.02. - 06.02.2026");
        browser.applicationDetailsSection.insertStudentFirstName(randomName);
        browser.applicationDetailsSection.insertStudentLastName(randomPrijmeni);
        browser.applicationDetailsSection.insertBirthdate(birthdate);
        browser.applicationDetailsSection.selectCashPaymentMethod();
        browser.applicationDetailsSection.clickHealthDisabilityCheckbox();
        browser.applicationDetailsSection.insertHealthDisabilityNote("Žák má alergii na lepek");
        browser.applicationDetailsSection.insertNote("Poznámka od rodiče");
        browser.applicationDetailsSection.clickAcceptTermsCheckbox();
        browser.applicationDetailsSection.clickCreateApplicationButton();

        // ověření výsledku
        asserter.applicationDetailSection.checkFirstName(capitalize(randomName));
        asserter.applicationDetailSection.checkLastName(capitalize(randomPrijmeni));
        asserter.applicationDetailSection.checkLegalRepresentativeEmail("da-app.admin@czechitas.cz");
        asserter.applicationDetailSection.checkLegalRepresentativeName("Lišák Admin");
        asserter.applicationDetailSection.checkDateOfBirth(birthdate);
        asserter.applicationDetailSection.checkPaymentMethod("Hotově");
        asserter.applicationDetailSection.checkTerm("02.02. - 06.02.2026");
        asserter.applicationDetailSection.checkHealthDisabilityNote("Žák má alergii na lepek");
        asserter.applicationDetailSection.checkNote("Poznámka od rodiče");

        browser.waitFor(7);
        browser.loginSection.logout();
    }
    // Pomocná metoda = kapitalizace
    private String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0,1).toUpperCase() + input.substring(1);
    }

    // Test vytvoření přihlášky jako Master Admin
    @Test
    void succesfullCreateApplicationAsMasterAdmin() {
        login("da-app.master@czechitas.cz", "AppRoot123");

        var randomPrijmeni = browser.generateRandomName(6);
        var randomName = browser.generateRandomName(5);
        var birthdate = generateValidBirthdate();

        browser.internalMenu.goToApplicationsSection();
        browser.applicationSection.clickCreateNewApplicationButton();

        browser.applicationDetailsSection.selectTerm("02.02. - 06.02.2026");
        browser.applicationDetailsSection.insertStudentFirstName(randomName);
        browser.applicationDetailsSection.insertStudentLastName(randomPrijmeni);
        browser.applicationDetailsSection.insertBirthdate(birthdate);
        browser.applicationDetailsSection.selectFKSPPaymentMethod();
        browser.applicationDetailsSection.clickCreateApplicationButton();

    // ověření výsledku
        asserter.applicationDetailSection.checkTerm("02.02. - 06.02.2026");
        asserter.applicationDetailSection.checkPaymentMethod("FKSP");
        asserter.applicationDetailSection.checkLegalRepresentativeName("Robin Master");
        asserter.applicationDetailSection.checkFirstName(capitalize(randomName));
        asserter.applicationDetailSection.checkLastName(capitalize(randomPrijmeni));
        asserter.applicationDetailSection.checkDateOfBirth(birthdate);
        asserter.applicationDetailSection.checkLegalRepresentativeEmail("da-app.master@czechitas.cz");

        browser.waitFor(7);
        browser.loginSection.logout();
    }

    // Test vytvoření přihlášky jako Rodič
    @Test
    void succesfullCreateApplication2() {
        login("lotty.cici@seznam.cz","Heslo1234");

        var randomPrijmeni = browser.generateRandomName(6);
        var randomName = browser.generateRandomName(5);
        var birthdate = generateValidBirthdate();

        browser.headerMenu.goToApplicationsSection();
        browser.applicationSection.clickCreateNewApplicationButton();
        browser.applicationSection.selectProgrammingSection();
        browser.applicationSection.clickCreatePythonApplicationButton();

        browser.applicationDetailsSection.selectTerm("02.02. - 06.02.2026");
        browser.applicationDetailsSection.insertStudentFirstName(randomName);
        browser.applicationDetailsSection.insertStudentLastName(randomPrijmeni);
        browser.applicationDetailsSection.insertBirthdate(birthdate);
        browser.applicationDetailsSection.selectCashPaymentMethod();
        browser.applicationDetailsSection.clickHealthDisabilityCheckbox();
        browser.applicationDetailsSection.insertHealthDisabilityNote("Žák má alergii na lepek");
        browser.applicationDetailsSection.insertNote("Poznámka od rodiče");
        browser.applicationDetailsSection.clickAcceptTermsCheckbox();
        browser.applicationDetailsSection.clickCreateApplicationButton();

        // ověření výsledku
        asserter.applicationDetailSection.checkFirstName(capitalize(randomName));
        asserter.applicationDetailSection.checkLastName(capitalize(randomPrijmeni));
        asserter.applicationDetailSection.checkLegalRepresentativeEmail("lotty.cici@seznam.cz");
        asserter.applicationDetailSection.checkLegalRepresentativeName("Lotty Čiči");
        asserter.applicationDetailSection.checkDateOfBirth(birthdate);
        asserter.applicationDetailSection.checkPaymentMethod("Hotově");
        asserter.applicationDetailSection.checkTerm("02.02. - 06.02.2026");
        asserter.applicationDetailSection.checkHealthDisabilityNote("Žák má alergii na lepek");
        asserter.applicationDetailSection.checkNote("Poznámka od rodiče");

        browser.waitFor(7);
        browser.loginSection.logout();
    }

    // DATBP25C-28
    // Create booking as a teacher = škola v přírodě
    @Test
    void sucessfullOrderasteacher(){
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO("22834958");
        browser.orderDetailSection.insertClient("ZŠ Hronov");
        browser.orderDetailSection.insertFullAddress("Náměstí 1, 549 31 Hronov");
        browser.orderDetailSection.insertSubstitute("petr.svoboda@zshronov.cz");
        browser.orderDetailSection.insertContactPersonNameAndSurname("Petr", "Svoboda");
        browser.orderDetailSection.insertContactPersonTelephone("+420777888999");
        browser.orderDetailSection.insertContactPersonEmail("email@test.cz");
        browser.orderDetailSection.insertStartDate("01.01.2026");
        browser.orderDetailSection.insertEndDate("10.01.2026");
        browser.orderDetailSection.insertSecondStartDate("05.02.2026");
        browser.orderDetailSection.insertSecondEndDate("15.02.2026");

        // Škola v přírodě – dopolední varianta
        browser.orderSection.selectSchoolInNatureOption();
        browser.orderDetailSection.insertChildrenCountToSchoolInNature(5);
        browser.orderDetailSection.insertInAgeToSchoolInNature(15);
        browser.orderDetailSection.insertAdultsCountToSchoolInNature(2);
        browser.orderDetailSection.insertStartTime("12:00");
        browser.orderDetailSection.selectBreakfastStartToSchoolInNature();
        browser.orderDetailSection.insertEndTime("20:00");
        browser.orderDetailSection.selectBreakfastEndToSchoolInNature();

        browser.orderDetailSection.saveSchoolInNatureOrder();

        // Ověření výsledku
        asserter.generalSection.checkCurrentUrl("https://team8-2022brno.herokuapp.com/objednavka/pridat");
        asserter.generalSection.checkToastContainsText("Objednávka byla úspěšně uložena");
    }

    // Create booking as a teacher = Příměstský tábor

    @Test
    void sucessfullOrderasteacher2() {
        // generování testovacích dat
        var ico = browser.generateIco(6); // např. "123456"
        var company = browser.generateCompanyName(5);            // např. "ZŠ Abcde"
        var address = browser.generateStreetAddress();          // např. "Ulice 12, 66101 Brno"
        var firstName = browser.generatePersonFirstName(4);     // "Jana"
        var lastName = browser.generatePersonLastName(6);       // "Nováková"
        var phone = browser.generatePhoneCz();                  // "+420777123456"
        var email = browser.generateEmail(firstName + lastName);

        browser.headerMenu.goToKindergartenAndSchoolSection();

        browser.orderSection.insertICO(ico);
        browser.orderDetailSection.insertClient(company);
        browser.orderDetailSection.insertFullAddress(address);
        browser.orderDetailSection.insertSubstitute("Mgr. " + firstName + " " + lastName);
        browser.orderDetailSection.insertContactPersonNameAndSurname(firstName, lastName);
        browser.orderDetailSection.insertContactPersonTelephone(phone);
        browser.orderDetailSection.insertContactPersonEmail(email);

        browser.orderDetailSection.insertStartDate("01.07.2026");
        browser.orderDetailSection.insertEndDate("05.07.2026");
        browser.orderDetailSection.insertSecondStartDate("10.07.2026");
        browser.orderDetailSection.insertSecondEndDate("15.07.2026");

        // Příměstský tábor – dopolední varianta
        browser.orderSection.selectSuburbanCampOption();
        browser.orderDetailSection.selectAfternoonSuburbanCampVariant();
        browser.orderDetailSection.insertChildrenCountToSuburbanCamp(15);
        browser.orderDetailSection.insertInAgeToSuburbanCamp(10);
        browser.orderDetailSection.insertAdultsCountToSuburbanCamp(5);

        browser.orderDetailSection.saveSuburbanCampOrder();

        // Ověření výsledku
        asserter.generalSection.checkCurrentUrl("https://team8-2022brno.herokuapp.com/objednavka/pridat");
        asserter.generalSection.checkToastContainsText("Objednávka byla úspěšně uložena");
    }

    @Test
    void sucessfullOrderasteacher3() {
        browser.headerMenu.goToKindergartenAndSchoolSection();

        browser.orderSection.insertICO("22834958");
        browser.orderDetailSection.insertClient("ZŠ Žižkov");
        browser.orderDetailSection.insertFullAddress("Náměstí 20, 661 01 Žižkov");
        browser.orderDetailSection.insertSubstitute("Mgr. Jana Nováková");
        browser.orderDetailSection.insertContactPersonNameAndSurname("Jana", "Nováková");
        browser.orderDetailSection.insertContactPersonTelephone("+420777888999");
        browser.orderDetailSection.insertContactPersonEmail("novakovajana@test.cz");

        browser.orderDetailSection.insertStartDate("01.07.2026");
        browser.orderDetailSection.insertEndDate("05.07.2026");
        browser.orderDetailSection.insertSecondStartDate("10.07.2026");
        browser.orderDetailSection.insertSecondEndDate("15.07.2026");

        // Příměstský tábor – dopolední varianta
        browser.orderSection.selectSuburbanCampOption();
        browser.orderDetailSection.selectAfternoonSuburbanCampVariant();
        browser.orderDetailSection.insertChildrenCountToSuburbanCamp(15);
        browser.orderDetailSection.insertInAgeToSuburbanCamp(10);
        browser.orderDetailSection.insertAdultsCountToSuburbanCamp(5);

        browser.orderDetailSection.saveSuburbanCampOrder();

        // Ověření výsledku
        asserter.generalSection.checkCurrentUrl("https://team8-2022brno.herokuapp.com/objednavka/pridat");
        asserter.generalSection.checkToastContainsText("Objednávka byla úspěšně uložena");
    }

    // Přihlášení jako admin a kontrola vytvořených přihlášek
    @Test
    void succesfullLoginAsAdmin2() {
        login("da-app.admin@czechitas.cz","Czechitas123");

        browser.internalMenu.goToOrdersSection();

        // Ověření, že tabulka objednávek obsahuje alespoň jednu objednávku
        asserter.generalSection.checkOrdersTableIsNotEmpty();
    }

    // Přihlášení jako Master admin a kontrola vytvořených přihlášek
    @Test
    void succesfullLoginAsMasterAdmin() {
        login("da-app.master@czechitas.cz", "AppRoot123");

        browser.internalMenu.goToOrdersSection();

        // Ověření, že tabulka objednávek obsahuje alespoň jednu objednávku
        asserter.generalSection.checkOrdersTableIsNotEmpty();
}
}