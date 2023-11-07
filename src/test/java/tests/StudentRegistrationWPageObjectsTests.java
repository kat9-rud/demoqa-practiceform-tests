package tests;

import data.TestData;
import org.junit.jupiter.api.Test;

public class StudentRegistrationWPageObjectsTests extends TestBase {
    TestData data = new TestData();
    @Test
    void fillStudentRegistrationFormTest() {
        page.openPage()
                .setFirstName(data.firstName)
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
    }
}
