package ru.praktikumServices.qaScooter.courierTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.praktikumServices.qaScooter.courier.CourierCred;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CourierCreateTest extends Base {

    @Test
    @DisplayName("Создание нового курьера")
    @Description("Проверяем, что курьера можно создать")
    public void courierCanBeCreated() {
        courierClient.create(courier).assertThat()
                .statusCode(201)
                .body("ok", is(true));

        ValidatableResponse loginResponse = courierClient.login(CourierCred.from(courier));
        courierId = loginResponse.extract().path("id");
    }

    @Test
    @DisplayName("Создание нового курьера с уже существующим логином")
    @Description("Проверяем, что нельзя создать курьера с уже существующим логином")
    public void courierCanNotBeCreatedWithSameLogin() {
        courierClient.create(courier);
        courierClient.create(courier).assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));

        ValidatableResponse loginResponse = courierClient.login(CourierCred.from(courier));
        courierId = loginResponse.extract().path("id");
    }

    @Test
    @DisplayName("Создание нового курьера с пустым полем login")
    @Description("Проверяем, что курьера нельзя создать без логина")
    public void courierCanNotBeCreatedWoLogin() {
        courier.setLogin(null);
        courierClient.create(courier).assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание нового курьера с пустым полем password")
    @Description("Проверяем, что курьера нельзя создать без пароля")
    public void courierCanNotBeCreatedWoPassword() {
        courier.setPassword(null);
        courierClient.create(courier).assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание нового курьера с пустым полем firstName")
    @Description("Проверяем, что курьера нельзя создать без имени")
    public void courierCanNotBeCreatedWoName() {
        courier.setFirstName(null);
        courierClient.create(courier).assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
