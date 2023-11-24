package tests;

import data.TestData;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class StudentRegistrationWPageObjectsTests extends TestBase {
    TestData data = new TestData();
    @Test
    @Tag("remote")
    void fillStudentRegistrationFormTest() {
        step("Open form", () -> {
            page.openPage();
        });

        step("Fill the form", () -> {
            page.setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setEmail(data.email)
                .setGender(data.gender)
                .setPhone(data.mobile)
                .setBirthday(data.dayOfBirth, data.monthOfBirth, data.yearOfBirth)
                .setSubjects(data.subject)
                .setHobbies(data.hobby)
                .uploadPicture(data.pic)
                .setCurrentAddress(data.currentAddress)
                .setState(data.state)
                .setCity(data.city)
                .submit();
        });

        step("Verify results", () -> {
            page.verifyResultsModalAppears()
                    .verifyResults("Student Name", data.firstName + " " + data.lastName)
                    .verifyResults("Student Email", data.email)
                    .verifyResults("Gender", data.gender)
                    .verifyResults("Mobile", data.mobile)
                    .verifyResults("Date of Birth", data.dayOfBirth + " " + data.monthOfBirth + "," + data.yearOfBirth)
                    .verifyResults("Subjects", data.subject)
                    .verifyResults("Hobbies", data.hobby)
                    .verifyResults("Picture", data.pic)
                    .verifyResults("Address", data.currentAddress)
                    .verifyResults("State and City", data.state + " " + data.city);
        });
    }
}
