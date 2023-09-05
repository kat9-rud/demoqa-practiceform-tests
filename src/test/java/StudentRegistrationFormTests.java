import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "2560x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillStudentRegistrationFormTest() {
        var firstName = "Anne";
        var lastName = "Smith";
        var email = "anne.smith@example.com";
        var gender = "Female";
        var mobile = "7823526297";
        var dayOfBirth = "01";
        var monthOfBirth = "January";
        var yearOfBirth = "1990";
        var subject = "Physics";
        var hobby = "Sports";
        var currentAddress = "7539 W Dallas St";
        var state = "NCR";
        var city = "Delhi";
        var pic = "user-logo.png";

        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);

        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(mobile);

        $("#dateOfBirthInput").click();
        $(".react-datepicker").shouldBe(visible);

        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day--0" + dayOfBirth + ":not(.react-datepicker__day--outside-month)").click();

        $("#subjectsInput").setValue("" + subject.charAt(0));
        $("#react-select-2-option-0").shouldBe(text(subject)).click();

        $("#hobbiesWrapper").$(byText(hobby)).click();

        File file = $("#uploadPicture").uploadFile(new File("src/test/java/../resources/" + pic));

        $("#currentAddress").setValue(currentAddress);

        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();

        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldBe(visible).shouldHave(text("Thanks for submitting the form"));

        $(".table tbody tr:nth-child(1) td:nth-child(2)").shouldHave(text(firstName + " " + lastName));
        $(".table tbody tr:nth-child(2) td:nth-child(2)").shouldHave(text(email));
        $(".table tbody tr:nth-child(3) td:nth-child(2)").shouldHave(text(gender));
        $(".table tbody tr:nth-child(4) td:nth-child(2)").shouldHave(text(mobile));
        $(".table tbody tr:nth-child(5) td:nth-child(2)").shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $(".table tbody tr:nth-child(6) td:nth-child(2)").shouldHave(text(subject));
        $(".table tbody tr:nth-child(7) td:nth-child(2)").shouldHave(text(hobby));
        $(".table tbody tr:nth-child(8) td:nth-child(2)").shouldHave(text(pic));
        $(".table tbody tr:nth-child(9) td:nth-child(2)").shouldHave(text(currentAddress));
        $(".table tbody tr:nth-child(10) td:nth-child(2)").shouldHave(text(state + " " + city));
    }
}
