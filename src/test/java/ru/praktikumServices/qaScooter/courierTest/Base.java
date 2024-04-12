package ru.praktikumServices.qaScooter.courierTest;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import ru.praktikumServices.qaScooter.courier.Courier;
import ru.praktikumServices.qaScooter.courier.CourierClient;
import ru.praktikumServices.qaScooter.courier.CourierGenerator;

public class Base {

    protected CourierClient courierClient;
    protected Courier courier;
    protected int courierId;

    @Before
    @Step("Создание тестовых данных курьера")
    public void createTestData() {
        courierClient = new CourierClient();
        courier = CourierGenerator.random();
    }

    @After
    @Step("Удаление тестовых данных")
    public void cleanup() {
        if (courierId != 0) {
            courierClient.delete(courierId);
        }
    }
}
