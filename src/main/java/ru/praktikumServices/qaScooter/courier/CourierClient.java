package ru.praktikumServices.qaScooter.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikumServices.qaScooter.RestClient;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestClient {

    private final static String COURIER_ACT = "/api/v1/courier";
    private final static String COURIER_LOGIN = "api/v1/courier/login";
    @Step("Регистрация курьера")
    public ValidatableResponse create(Courier courier) {
        return given().log().all()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(COURIER_ACT)
                .then();
    }
    @Step("Авторизация курьера")
    public ValidatableResponse login(CourierCred cred) {
        return given().log().all()
                .spec(getSpec())
                .body(cred)
                .when()
                .post(COURIER_LOGIN)
                .then();
    }
    @Step("Удаление курьера")
    public ValidatableResponse delete(int courierId) {
        return given().log().all()
                .spec(getSpec())
                .when()
                .delete(COURIER_ACT + "/" + courierId)
                .then();
    }
}
