package endpoint.vacancy;

import com.varmetrics.dao.model.Vacancy;
import com.varmetrics.dao.repository.VacancyRepository;
import com.varmetrics.endpoint.vacancy.VacancyService;
import com.varmetrics.service.company.Company;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VacancyServiceTest {

    @Test
    public void scanAndGetAllVacanciesTest() throws ExecutionException, InterruptedException {
        // GIVEN
        String searchString = "Java Москва";
        List<Vacancy> vacanciesExpect = getVacancies();

        VacancyRepository vacancyRepository = mock(VacancyRepository.class);
        when(vacancyRepository.findAll()).thenReturn(vacanciesExpect);

        Company company = mock(Company.class);
        Future<List<Vacancy>> listFuture = mock(Future.class);
        when(listFuture.get()).thenReturn(vacanciesExpect);
        ExecutorService executorService = mock(ExecutorService.class);
        when(executorService.submit(company)).thenReturn(listFuture);

        VacancyService vacancyService = new VacancyService(
                mock(TransactionTemplate.class),
                vacancyRepository,
                singletonList(company),
                executorService);

        // WHEN
        List<Vacancy> vacanciesResult = vacancyService.scanAndGetAllVacancies(searchString);

        // THEN
        Assert.assertEquals(vacanciesExpect, vacanciesResult);
    }

    @Test
    public void deleteAllVacancies() {
        // GIVEN
        VacancyService vacancyService = new VacancyService(
                mock(TransactionTemplate.class),
                mock(VacancyRepository.class),
                singletonList(mock(Company.class)),
                mock(ExecutorService.class));

        // WHEN
        vacancyService.deleteAllVacancies();
    }

    @Test
    public void getAllVacanciesTest() {
        // GIVEN
        List<Vacancy> vacanciesExpect = getVacancies();

        VacancyRepository vacancyRepository = mock(VacancyRepository.class);
        when(vacancyRepository.findAll()).thenReturn(vacanciesExpect);
        VacancyService vacancyService = new VacancyService(
                mock(TransactionTemplate.class),
                vacancyRepository,
                singletonList(mock(Company.class)),
                mock(ExecutorService.class));

        // WHEN
        List<Vacancy> vacanciesResult = vacancyService.getAllVacancies();

        // THEN
        Assert.assertEquals(vacanciesExpect, vacanciesResult);
    }

    private List<Vacancy> getVacancies() {
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle("Разработчик java");
        vacancy.setCompanyName("Сбер. IT");
        vacancy.setSalary("от 300 000 руб.");
        vacancy.setLocation("Москва");
        vacancy.setSiteName("hh.ru");
        vacancy.setUrl("https://hh.ru/vacancy/40740811?query=java");
        vacancy.setDateVacancy("3 декабря");
        vacancy.setDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));

        Vacancy vacancy2 = new Vacancy();
        vacancy2.setTitle("Java developer");
        vacancy2.setCompanyName("Сбер для экспертов");
        vacancy2.setSalary("до 350 000 руб.");
        vacancy2.setLocation("Москва, Кутузовская");
        vacancy2.setSiteName("Superjob.ru");
        vacancy2.setUrl("https://www.superjob.ru/vakansii/razrabotchik-java-34590555.html");
        vacancy2.setDateVacancy("3 декабря");
        vacancy2.setDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));

        ArrayList<Vacancy> vacancies = new ArrayList<>();
        vacancies.add(vacancy);
        vacancies.add(vacancy2);

        return vacancies;
    }
}
