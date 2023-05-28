package requestsAPI.requests;

import io.restassured.response.Response;
import requestsAPI.model.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String LOGIN_PATH = "https://stellarburgers.nomoreparties.site/api/auth/login";
    private static final String DELETE_PATH = "https://stellarburgers.nomoreparties.site/api/auth/user";

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

    //Удаление пользователя
    public Response deleteUser(User user){
        return given()
                .header("Authorization", getAccessToken(user))
                .delete(DELETE_PATH);
    }

}
