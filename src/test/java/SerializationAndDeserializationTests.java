import org.junit.Test;
import pojo.pet.Category;
import pojo.pet.Pet;
import pojo.pet.Tag;

import java.util.Arrays;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SerializationAndDeserializationTests {

    @Test
    public void createPet() {
            Category category = new Category();
            category.setId(666);
            category.setName("dogs");

            Tag tag = new Tag();
            tag.setId(1);
            tag.setName("dogs-category");

            Pet pet = new Pet();
            pet.setId(777);
            pet.setCategory(category);
            pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
            pet.setTags(Collections.singletonList(tag));
            pet.setStatus("sold");

            given().log().all().body(pet).contentType("application/json")
                    .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                    .then().log().all().statusCode(200);

            Pet[] pets = given().log().all()
//                    .body(pet)
//                    .contentType("application/json")
                    .queryParam("status", "sold")
                    .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/findByStatus")
                    .then().log().all().statusCode(200).extract().as(Pet[].class);

            assertTrue(Arrays.asList(pets).size() > 0, "List of pets");

        }
    }
