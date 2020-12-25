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

import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_0;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_1;

@Service
public class VacancyService {

    private static final Logger logger = LoggerFactory.getLogger(VacancyService.class);

    private final TransactionTemplate transactionTemplate;
    private final VacancyRepository vacancyRepository;
    private final List<Company> companyList;

    @Autowired
    public VacancyService(TransactionTemplate transactionTemplate,
                          VacancyRepository vacancyRepository,
                          List<Company> companyList) {
        this.transactionTemplate = transactionTemplate;
        this.vacancyRepository = vacancyRepository;
        this.companyList = companyList;
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
        try {
            long currentTimeMillis = System.currentTimeMillis();
            List<Vacancy> resultList = new LinkedList<>();
            logger.info(VAR_METRICS_0.getText(), searchString, companyList);
            companyList.forEach(company -> resultList.addAll(company.getVacancies(searchString)));

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
