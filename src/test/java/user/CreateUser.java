package user;

import dataGenerator.UserTestDataGenerator;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pojo.user.ApiResponse;
import pojo.user.User;
import testBases.SuiteTestBase;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class CreateUser extends SuiteTestBase {

    User userBody;

    @Test
    public void shouldUserCreatedAfterPostMethod(){

        UserTestDataGenerator userTestDataGenerator = new UserTestDataGenerator();
        userBody = userTestDataGenerator.generateUser();
        ApiResponse apiResponse = given()
                .body(userBody)
                .contentType("application/json")
                .when()
                .post("user")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(ApiResponse.class);

        assertEquals(apiResponse.getMessage(), userBody.getId().toString());
        assertEquals(apiResponse.getType(), "unknown");

    }

    @AfterMethod
    public void cleanUpAfterTest(){
        given()
                .when()
                .delete("user/{userName}", userBody.getUsername())
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
