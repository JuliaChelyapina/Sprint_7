package ru.praktikumServices.qaScooter.courierTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.praktikumServices.qaScooter.courier.CourierCred;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;

public class CourierLoginTest extends Base {

    @Test
    @DisplayName("Курьер может авторизоваться")
    @Description("Проверяем, что курьер может авторизоваться, если указать верные логин и пароль")
    public void courierCanLogin() {
        courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCred.from(courier));
        loginResponse.assertThat()
                .statusCode(200)
                .body("id", notNullValue());
        courierId = loginResponse.extract().path("id");
    }

    @Test
    @DisplayName("Курьер не может авторизоваться без login")
    @Description("Проверяем, что курьер не может авторизоваться, если не указать логин")
    public void courierCantLoginWoLogin() {
        courier.setLogin(null);
        courierClient.login(CourierCred.from(courier)).assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Курьер не может авторизоваться без password")
    @Description("Проверяем, что курьер не может авторизоваться, если не указать пароль")
    public void courierCantLoginWoPassword() {
        courier.setPassword(null);
        courierClient.login(CourierCred.from(courier)).assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Курьер не может авторизоваться с неверным login")
    @Description("Проверяем, что курьер не может авторизоваться, если указать неверный логин")
    public void courierCantLoginWithWrongLogin() {
        courierClient.create(courier);
        courierId = courierClient.login(CourierCred.from(courier)).extract().path("id");
        courier.setLogin("fake");
        ValidatableResponse loginResponse = courierClient.login(CourierCred.from(courier));
        loginResponse.assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Курьер не может авторизоваться с неверным password")
    @Description("Проверяем, что курьер не может авторизоваться, если указать неверный пароль")
    public void courierCantLoginWithWrongPassword() {
        courierClient.create(courier);
        courierId = courierClient.login(CourierCred.from(courier)).extract().path("id");
        courier.setPassword("fake");
        ValidatableResponse loginResponse = courierClient.login(CourierCred.from(courier));
        loginResponse.assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
