package ru.praktikumServices.qaScooter.orderTest;

import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import ru.praktikumServices.qaScooter.order.Order;
import ru.praktikumServices.qaScooter.order.OrderClient;
import ru.praktikumServices.qaScooter.order.OrderGenerator;

import static org.hamcrest.Matchers.is;

public class BaseOrder {

    protected OrderClient orderClient;
    protected Order order;
    protected String track;

    @Before
    @Step("Создание тестовых данных заказа")
    public void createTestData() {
        orderClient = new OrderClient();
        order = OrderGenerator.random();
    }

    @After
    @Step("Удаление тестовых данных")
    public void cleanup() {
        if (StringUtils.isNotBlank(track)) {
            orderClient.cancel(track);
        }
    }
}
