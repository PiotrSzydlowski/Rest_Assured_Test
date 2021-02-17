package pet;

import com.github.javafaker.Faker;
import dataGenerator.PetTestDataGenerator;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.pet.Pet;
import testBases.SuiteTestBase;

import static io.restassured.RestAssured.given;

public class UpdatePetTest extends SuiteTestBase {

    Pet pet;

    @BeforeMethod
    public void createPetBeforePutMethod(){
        PetTestDataGenerator generatedPet = new PetTestDataGenerator();
        pet = generatedPet.generatePet();
    }

    @Test
    public void updatePet(){
        pet.setName(new Faker().name().firstName());
        Pet actualPet = given()
                .body(this.pet)
                .contentType("application/json")
                .when()
                .put("pet")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Pet.class);

        Assertions.assertThat(pet).isNotEqualTo(actualPet);
    }
}
