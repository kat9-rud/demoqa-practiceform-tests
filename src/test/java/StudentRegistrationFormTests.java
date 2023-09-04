import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "2560x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillStudentRegistrationFormTest() {
        open("/automation-practice-form");

        var firstName = "Anne";
        var lastName = "Smith";
        var email = "anne.smith@example.com";
        var mobile = "7823526297";
        var subjects = "Physics";
        var currentAddress = "7539 W Dallas St";

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(".custom-radio:nth-child(2)").click();
        $("#userNumber").setValue(mobile);

        $("#dateOfBirthInput").click();
        $(".react-datepicker").shouldBe(visible);
        $(".react-datepicker__month-select").selectOption(0);
        $(".react-datepicker__year-select").selectOptionByValue("1990");
        $(".react-datepicker__day--001").click();
        $("#subjectsInput").setValue(subjects);
        $("#subjectsInput").pressEnter();
        $("label[for='hobbies-checkbox-1'").click();
        File file = $("#uploadPicture").uploadFile(new File("src/test/java/../resources/user-logo.png"));

        $("#currentAddress").setValue(currentAddress).pressTab();

        $("#react-select-3-input").setValue("NCR").pressTab().pressTab();
        $("#react-select-4-input").setValue("Delhi").pressTab().pressTab();
        $("#submit").pressEnter();
        $("#example-modal-sizes-title-lg").shouldBe(visible).shouldHave(text("Thanks for submitting the form"));
    }
}
