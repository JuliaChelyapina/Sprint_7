package ru.praktikumServices.qaScooter.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    public static Courier random() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, firstName);
    }
}
