package ru.praktikumServices.qaScooter.orderTest;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.List;

@RunWith(Parameterized.class)
public class OrderCreateTest extends BaseOrder {
    private final List<String> color;

    public OrderCreateTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет: {0}")
    public static Object[][] color() {
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("GRAY")},
                {List.of("BLACK, GRAY")},
                {List.of()}
        };
    }

    @Test
    @DisplayName("Создание заказа с разными предпочитаемыми цветами")
    public void orderWithDiffColors() {
        Allure.description("Проверяем корректность создания заказа с разными цветами: " + color.toString());
        order.setColor(color);
        ValidatableResponse responseCreateOrder = orderClient.create(order);
        responseCreateOrder.assertThat()
                .statusCode(201)
                .body("track", notNullValue());
        track = responseCreateOrder.extract().path("track").toString();
    }

}
