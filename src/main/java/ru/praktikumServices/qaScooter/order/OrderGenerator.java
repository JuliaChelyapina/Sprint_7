package ru.praktikumServices.qaScooter.order;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ru.praktikumServices.qaScooter.courier.Courier;

public class OrderGenerator {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    static Faker faker = new Faker();
    static Date now = new Date();
    public static Order random() {
        final String firstName = faker.name().lastName();
        final String lastName = faker.name().lastName();
        final String address = faker.address().fullAddress();
        final String metroStation = RandomStringUtils.randomAlphabetic(10);
        final String phone = faker.phoneNumber().cellPhone();
        final int rentTime = RandomUtils.nextInt(1, 10000);
        final String deliveryDate = sdf.format(faker.date().between(now, new Date(now.getTime() + 10000)));
        final String comment = RandomStringUtils.randomAlphabetic(10);
        final List<String> color = List.of("BLACK");
        return new Order(firstName, lastName, address,
                metroStation, phone, rentTime, deliveryDate,
                comment, color);
    }
}
