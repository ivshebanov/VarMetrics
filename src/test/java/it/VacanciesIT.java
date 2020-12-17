package it;

import com.varmetrics.dao.model.Vacancy;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class VacanciesIT {

    @Test
    public void scanAndGetAllVacanciesTest() {
        List<Vacancy> vacancies = sendRequest("/vacancies/scan", Vacancy.class);

        Assert.assertNotNull(vacancies);
        Assert.assertNotNull(vacancies.get(0));
        Assert.assertNotNull(vacancies.get(0).getTitle());
        Assert.assertNotNull(vacancies.get(0).getCompanyName());
        Assert.assertNotNull(vacancies.get(0).getSiteName());
        Assert.assertNotNull(vacancies.get(0).getUrl());
        Assert.assertNotNull(vacancies.get(0).getDate());
    }

    @Test
    public void getAllVacancies() {
        List<Vacancy> vacancies = sendRequest("/vacancies", Vacancy.class);

        Assert.assertNotNull(vacancies);
        Assert.assertNotNull(vacancies.get(0));
        Assert.assertNotNull(vacancies.get(0).getTitle());
        Assert.assertNotNull(vacancies.get(0).getCompanyName());
        Assert.assertNotNull(vacancies.get(0).getSiteName());
        Assert.assertNotNull(vacancies.get(0).getUrl());
        Assert.assertNotNull(vacancies.get(0).getDate());
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
