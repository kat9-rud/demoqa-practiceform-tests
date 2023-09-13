package tests;

import org.junit.jupiter.api.Test;

public class StudentRegistrationWPageObjectsTests extends TestBase {
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

        page.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhone(mobile)
                .setBirthday(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(subject)
                .setHobbies(hobby)
                .uploadPicture(pic)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submit();

        page.verifyResultsModalAppears()
                .verifyResults("Student Name", firstName + " " + lastName)
                .verifyResults("Student Email", email)
                .verifyResults("Gender", gender)
                .verifyResults("Mobile", mobile)
                .verifyResults("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .verifyResults("Subjects", subject)
                .verifyResults("Hobbies", hobby)
                .verifyResults("Picture", pic)
                .verifyResults("Address", currentAddress)
                .verifyResults("State and City", state + " " + city);
    }
}
