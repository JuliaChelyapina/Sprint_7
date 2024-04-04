package ru.praktikumServices.qaScooter.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikumServices.qaScooter.courier.CourierCred;
import ru.praktikumServices.qaScooter.RestClient;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient {

    private final static String ORDERS_ACT = "/api/v1/orders";
    private final static String ORDERS_CANCEL = "/api/v1/orders/cancel";
    @Step("Создание заказа")
    public ValidatableResponse create(Order order) {
        return given().log().all()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDERS_ACT)
                .then();
    }
    @Step("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return given()//.log().all()
                .spec(getSpec())
                .when()
                .get(ORDERS_ACT)
                .then();
    }
    @Step("Отмена заказа")
    public ValidatableResponse cancel(String track) {
        return given().log().all()
                .spec(getSpec())
                .body("{\"track\":" + track + "}")
                .when()
                .put(ORDERS_CANCEL + "?track=" + track)
                .then();
    }
}
