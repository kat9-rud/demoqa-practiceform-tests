package data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class TestData {
    Faker faker = new Faker();
    private final String[] hobbies = new String[]{"Sports", "Reading", "Music"};
    private final String[] subjects = new String[]{"Maths", "Chemistry", "Physics", "Computer Science",
            "English", "History"};
    private final String[] states = new String[]{"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String email = faker.internet().emailAddress();
    public String gender = faker.demographic().sex();
    public String mobile = String.valueOf(faker.number().numberBetween(9151111111L, 9269999999L));
    private String[] dayMonthYear = getRandomDate();
    public String dayOfBirth = String.format("%s", dayMonthYear[0]);
    public String monthOfBirth = String.format("%s", dayMonthYear[1]);
    public String yearOfBirth = String.format("%s", dayMonthYear[2]);
    public String subject = getRandomValue(subjects);
    public String hobby = getRandomValue(hobbies);
    public String currentAddress = faker.address().streetAddress();
    public String state = getRandomValue(states);
    public String city = getCity();
    public String pic = "user-logo.png";

    private String getRandomValue(String... initialValues) {
        return faker.options().option(initialValues);
    }

    private String getCity() {
        String cityItem = null;

        if (state.equals("NCR")) {
            cityItem = getRandomValue("Delhi", "Gurgaon", "Noida");
        }

        if (state.equals("Uttar Pradesh")) {
            cityItem = getRandomValue("Agra", "Lucknow", "Merrut");
        }

        if (state.equals("Haryana")) {
            cityItem = getRandomValue("Karnal", "Panipat");
        }

        if (state.equals("Rajasthan")) {
            cityItem = getRandomValue("Jaipur", "Jaiselmer");
        }


        return cityItem;
    }
    private String[] getRandomDate() {
        return new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(faker.date().birthday(18, 70)).split(" ");
    }
}
