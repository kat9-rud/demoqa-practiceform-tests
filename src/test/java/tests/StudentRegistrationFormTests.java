package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
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

//        File file = $("#uploadPicture").uploadFile(new File("src/test/java/../resources/" + pic));
        $("#uploadPicture").uploadFromClasspath(pic);

        $("#currentAddress").setValue(currentAddress);

        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();

        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldBe(visible).shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName + " " + lastName), text(email), text(gender), text(mobile), text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth), text(subject), text(hobby), text(pic), text(currentAddress), text(state + " " + city));
    }
}
