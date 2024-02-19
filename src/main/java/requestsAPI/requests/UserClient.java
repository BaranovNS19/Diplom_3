package requestsAPI.requests;

import io.restassured.response.Response;
import requestsAPI.model.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String LOGIN_PATH = "https://stellarburgers.nomoreparties.site/api/auth/login";
    private static final String DELETE_PATH = "https://stellarburgers.nomoreparties.site/api/auth/user";
    private static final String CREATE_PATH = "https://stellarburgers.nomoreparties.site/api/auth/register";

    //Логин пользователя для получения токена авторизации
    public String getAccessToken(User user){
       Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(LOGIN_PATH);
       String accessToken = response.then().statusCode(200).extract().path("accessToken");
       return accessToken;
    }

    //Создание пользователя для тестирования входа
    public Response createUser(User user){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(CREATE_PATH);
    }

    //Удаление пользователя
    public Response deleteUser(User user){
        return given()
                .header("Authorization", getAccessToken(user))
                .delete(DELETE_PATH);
    }

}
