package ru.praktikumServices.qaScooter.orderTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class GetOrderListTest extends BaseOrder {
    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверка, что в тело ответа возвращается список заказов")
    public void getOrderList() {
        ValidatableResponse responseCreate = orderClient.getOrderList();
        responseCreate.assertThat().statusCode(200);
        List<HashMap> orders = responseCreate.extract().path("orders");
        assertNotNull(orders.get(1).get("id").toString());
    }

}
