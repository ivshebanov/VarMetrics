package com.varmetrics.endpoint.vacancy;

import com.varmetrics.dao.model.Vacancy;
import com.varmetrics.dao.repository.VacancyRepository;
import com.varmetrics.service.company.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static com.varmetrics.VarMetricsEvent.VAR_METRICS_0;
import static com.varmetrics.VarMetricsEvent.VAR_METRICS_1;
import static com.varmetrics.VarMetricsEvent.VAR_METRICS_7;
import static org.apache.commons.lang3.Validate.notNull;

@Service
public class VacancyService {

    private static final Logger logger = LoggerFactory.getLogger(VacancyService.class);

    private final TransactionTemplate transactionTemplate;
    private final VacancyRepository vacancyRepository;
    private final List<Company> companyList;
    private final ExecutorService executorService;

    @Autowired
    public VacancyService(TransactionTemplate transactionTemplate,
                          VacancyRepository vacancyRepository,
                          List<Company> companyList,
                          ExecutorService executorService) {

        this.transactionTemplate = notNull(transactionTemplate, "transactionTemplate must not be null");
        this.vacancyRepository = notNull(vacancyRepository, "vacancyRepository must not be null");
        this.companyList = notNull(companyList, "companyList must not be null");
        this.executorService = notNull(executorService, "executorService must not be null");
    }

    public List<Vacancy> getAllVacancies() {
        try {
            return vacancyRepository.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return new ArrayList<>();
    }

    public void deleteAllVacancies() {
        try {
            vacancyRepository.deleteAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public List<Vacancy> scanAndGetAllVacancies(String searchString) {
        synchronized (executorService) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                logger.info(VAR_METRICS_0.getText(), searchString, companyList);

                List<Vacancy> resultList = new LinkedList<>();
                List<Future<List<Vacancy>>> submits = new LinkedList<>();

                companyList.forEach(company -> {
                    company.setSearchString(searchString);
                    submits.add(executorService.submit(company));
                });
                logger.debug(VAR_METRICS_7.getText(), companyList.size(), submits.size());
                for (Future<List<Vacancy>> submit : submits) {
                    resultList.addAll(submit.get());
                }

                long lastTimeMillis = System.currentTimeMillis();
                long timeS = (lastTimeMillis - currentTimeMillis) / 1000;
                logger.info(VAR_METRICS_1.getText(), resultList.size(), timeS);

                if (resultList.size() > 0) {
                    doInTransaction(() -> {
                        vacancyRepository.deleteAll();
                        vacancyRepository.saveAll(resultList);
                    });
                }
                return vacancyRepository.findAll();
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            return new ArrayList<>();
        }
    }

    private void doInTransaction(CallWithoutResult callWithoutResult) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    callWithoutResult.call();
                } catch (Exception exc) {
                    logger.error(exc.getMessage(), exc);
                    transactionStatus.setRollbackOnly();
                    throw exc;
                }
            }
        });
    }

    private interface CallWithoutResult {
        void call();
    }
}
