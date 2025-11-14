package cz.czechitas.automation;

import java.util.Objects;

/**
 * OrderDetail specific selenium actions
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class OrderDetailAction {
    private final ElementFinder elementFinder;

    OrderDetailAction(ElementFinder elementFinder) {
        this.elementFinder = Objects.requireNonNull(elementFinder);
    }

    void insertSubstitute(String substitute) {
        var substituteInput = elementFinder.findByXPath("//*[@id=\"substitute\"]");
        substituteInput.sendKeys(substitute);
    }

    void insertContactPersonNameAndSurname(String name, String surname) {
        var contactPersonInput = elementFinder.findByXPath("//*[@id=\"contact_name\"]");
        contactPersonInput.sendKeys(name + " " + surname);
    }

    void insertContactPersonTelephone(String telephone) {
        var contactPersonTelephoneInput = elementFinder.findByXPath("//*[@id=\"contact_tel\"]");
        contactPersonTelephoneInput.sendKeys(telephone);
    }

    void insertContactPersonEmail(String email) {
        var contactPersonEmailInput = elementFinder.findByXPath("//*[@id=\"contact_mail\"]");
        contactPersonEmailInput.sendKeys(email);
    }

    void insertStartDate(String startDate) {
        var startDateInput = elementFinder.findByXPath("//*[@id=\"start_date_1\"]");
        startDateInput.sendKeys(startDate);
    }

    void insertEndDate(String endDate) {
        var endDateInput = elementFinder.findByXPath("//*[@id=\"end_date_1\"]");
        endDateInput.sendKeys(endDate);
    }

    void insertSecondStartDate(String startDate) {
        var startDateInput = elementFinder.findByXPath("//*[@id=\"start_date_2\"]");
        startDateInput.sendKeys(startDate);
    }

    void insertSecondEndDate(String endDate) {
        var endDateInput = elementFinder.findByXPath("//*[@id=\"end_date_2\"]");
        endDateInput.sendKeys(endDate);
    }
    void insertThirdStartDate(String startDate) {
        var startDateInput = elementFinder.findByXPath("//*[@id=\"start_date_3\"]");
        startDateInput.sendKeys(startDate);
    }

    void insertThirdEndDate(String endDate) {
        var endDateInput = elementFinder.findByXPath("//*[@id=\"end_date_3\"]");
        endDateInput.sendKeys(endDate);
    }

    void selectForenoonSuburbanCampVariant() {
        var element = elementFinder.findByXPath("//*[@id=\"camp-date_part\"]");

        element.click();
        var option = elementFinder.findByXPath(
                "/html/body/div[1]/div/div/div/div/div/form/div[5]/div[1]/div[1]/div[1]/select/option[1]"
        );
        option.click();
    }

    void selectAfternoonSuburbanCampVariant() {
        var element = elementFinder.findByXPath("//*[@id=\"camp-date_part\"]");

        element.click();
        var option = elementFinder.findByXPath(
                "/html/body/div[1]/div/div/div/div/div/form/div[5]/div[1]/div[1]/div[1]/select/option[2]"
        );
        option.click();
    }

    void insertChildrenCountToSuburbanCamp(int childrenCount) {
        var suburbanCampInput = elementFinder.findByXPath("//*[@id=\"camp-students\"]");
        suburbanCampInput.sendKeys(String.valueOf(childrenCount));
    }

    void insertChildrenCountToSchoolInNature(int childrenCount) {
        var natureStudentsInput = elementFinder.findByXPath("//*[@id=\"nature-students\"]");
        natureStudentsInput.sendKeys(String.valueOf(childrenCount));
    }

    void insertInAgeToSuburbanCamp(int inAge) {
        var inAgeInput = elementFinder.findByXPath("//*[@id=\"camp-age\"]");
        inAgeInput.sendKeys(String.valueOf(inAge));
    }

    void insertInAgeToSchoolInNature(int inAge) {
        var inAgeInput = elementFinder.findByXPath("//*[@id=\"nature-age\"]");
        inAgeInput.sendKeys(String.valueOf(inAge));
    }

    void insertAdultsCountToSuburbanCamp(int adultsCount) {
        var suburbanCampInput = elementFinder.findByXPath("//*[@id=\"camp-adults\"]");
        suburbanCampInput.sendKeys(String.valueOf(adultsCount));
    }

    void insertAdultsCountToSchoolInNature(int adultsCount) {
        var natureStudentsInput = elementFinder.findByXPath("//*[@id=\"nature-adults\"]");
        natureStudentsInput.sendKeys(String.valueOf(adultsCount));
    }

    void insertClient(String client) {
        var noteInput = elementFinder.findByXPath("//*[@id=\"client\"]");
        noteInput.sendKeys(client);
    }

    void insertFullAddress(String fullAddress) {
        var fullAddressInput = elementFinder.findByXPath("//*[@id=\"address\"]");
        fullAddressInput.sendKeys(fullAddress);
    }

    void insertStartTime(String arrivalTime) {
        var arrivalTimeInput = elementFinder.findByXPath("//*[@id=\"nature-start_time\"]");
        arrivalTimeInput.sendKeys(arrivalTime);
    }

    void selectBreakfastStartToSchoolInNature() {
        var element = elementFinder.findByXPath("//*[@id=\"nature-start_food\"]");

        element.click();
        var option = elementFinder.findByXPath(
                "/html/body/div[1]/div/div/div/div/div/form/div[5]/div[2]/div[1]/div[5]/select/option[1]"
        );
        option.click();
    }

    void selectLunchStartToSchoolInNature() {
        var element = elementFinder.findByXPath("//*[@id=\"nature-start_food\"]");

        element.click();
        var option = elementFinder.findByXPath(
                "/html/body/div[1]/div/div/div/div/div/form/div[5]/div[2]/div[1]/div[5]/select/option[2]"
        );
        option.click();
    }

    void selectDinnerStartToSchoolInNature() {
        var element = elementFinder.findByXPath("//*[@id=\"nature-start_food\"]");

        element.click();
        var option = elementFinder.findByXPath(
                "/html/body/div[1]/div/div/div/div/div/form/div[5]/div[2]/div[1]/div[5]/select/option[3]"
        );
        option.click();
    }

    void insertEndTime(String endTime) {
        var arrivalTimeInput = elementFinder.findByXPath("//*[@id=\"nature-end_time\"]");
        arrivalTimeInput.sendKeys(endTime);
    }

    void selectBreakfastEndToSchoolInNature() {
        var element = elementFinder.findByXPath("//*[@id=\"nature-end_food\"]");

        element.click();
        var option = elementFinder.findByXPath(
                "/html/body/div[1]/div/div/div/div/div/form/div[5]/div[2]/div[1]/div[7]/select/option[1]"
        );
        option.click();
    }

    void selectLunchEndToSchoolInNature() {
        var element = elementFinder.findByXPath("//*[@id=\"nature-end_food\"]");

        element.click();
        var option = elementFinder.findByXPath(
                "/html/body/div[1]/div/div/div/div/div/form/div[5]/div[2]/div[1]/div[7]/select/option[2]"
        );
        option.click();
    }

    void selectDinnerEndToSchoolInNature() {
        var element = elementFinder.findByXPath("//*[@id=\"nature-end_food\"]");

        element.click();
        var option = elementFinder.findByXPath(
                "/html/body/div[1]/div/div/div/div/div/form/div[5]/div[2]/div[1]/div[7]/select/option[3]"
        );
        option.click();
    }

    void saveSuburbanCampOrder() {
        var saveButton = elementFinder.findByXPath("/html/body/div[1]/div/div/div/div/div/form/div[5]/div[1]/div[2]/input");
        saveButton.click();
    }

    void saveSchoolInNatureOrder() {
        var saveButton = elementFinder.findByXPath("/html/body/div[1]/div/div/div/div/div/form/div[5]/div[2]/div[2]/input");
        saveButton.click();
    }
}
