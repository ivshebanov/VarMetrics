package it;

import com.varmetrics.dao.model.Eur;
import com.varmetrics.dao.model.Usd;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CurrencyIT {

    @Test
    public void usdAllTest() {
        // WHEN
        List<Usd> usds = sendRequest("/usd", Usd.class);

        // THEN
        Assert.assertNotNull(usds);
        Assert.assertNotNull(usds.get(0));
        Assert.assertNotNull(usds.get(0).getDate());
        Assert.assertTrue(usds.get(0).getCourse() != 0);
    }

    @Test
    public void eurAllTest() {
        // WHEN
        List<Eur> eurs = sendRequest("/eur", Eur.class);

        // THEN
        Assert.assertNotNull(eurs);
        Assert.assertNotNull(eurs.get(0));
        Assert.assertNotNull(eurs.get(0).getDate());
        Assert.assertTrue(eurs.get(0).getCourse() != 0);
    }

    private <T> List<T> sendRequest(String path, Class<T> responseClass) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .jsonPath().getList("", responseClass);
    }
}
