package basicRestAssuredApiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DummyApiTests {

    String id;
    @BeforeClass
    public static void init() {
        baseURI = "https://dummy.restapiexample.com/api/v1/";
    }

    @Test
    public void test1(){

        given()
                .accept(ContentType.JSON)
                .when()
                .get("employees")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response().prettyPrint();

    }

    @Test
    public void test2() {

       Response response = given()
                .accept(ContentType.JSON)
                .body("{\n" +
                        "    \"name\":\"Green\",\n" +
                        "    \"salary\":\"900\",\n" +
                        "    \"age\":\"34\"\n" +
                        "}")
                .when()
                .post("create")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();

        //response.prettyPrint();

       id = response.jsonPath().getString("data.id");
        System.out.println("id = " + id);

    }

    @Ignore
    @Test
    public void test3(){

        given()
                .accept(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get("employee/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response().prettyPrint();


   }

    @Test
    public void test4(){

        Response response = given()
                .accept(ContentType.JSON)
                .body("{\n" +
                        "    \"name\":\"Green\",\n" +
                        "    \"salary\":\"900\",\n" +
                        "    \"age\":\"40\"\n" +
                        "}")
                .when()
                .put("update/" + id)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();


    }

    @Test
    public void test5(){

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .put("delete/" + id)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();
    }
}
