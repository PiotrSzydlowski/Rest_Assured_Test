import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserTest {

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest(){

        given().log().all()
                .contentType("application/json")
                .body("{\n" +
                        "  \"id\": 445,\n" +
                        "  \"username\": \"firstuser\",\n" +
                        "  \"firstName\": \"Krzysztof\",\n" +
                        "  \"lastName\": \"Kowalski\",\n" +
                        "  \"email\": \"krzysztof@test.com\",\n" +
                        "  \"password\": \"password\",\n" +
                        "  \"phone\": \"+123456789\",\n" +
                        "  \"userStatus\": 123\n" +
                        "}")
                .when().post("https://petstore.swagger.io/v2/user")
                .then().log().all().statusCode(200);

        given().log().all()
                .contentType("application/json")
                .pathParam("username", "firstuser")
                .when().get("https://petstore.swagger.io/v2/user/{username}")
                .then().log().all().statusCode(200);
    }
}
