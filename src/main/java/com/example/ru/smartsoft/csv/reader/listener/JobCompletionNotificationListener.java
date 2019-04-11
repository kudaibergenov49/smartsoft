package com.example.ru.smartsoft.csv.reader.listener;

import com.example.ru.smartsoft.csv.reader.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            jdbcTemplate.query(
                    "SELECT ssoid, ts, grp, type, subtype, url, orgid, formid, code, ltpa, sudirresponse, ymdh FROM usr",
                    (rs, row) -> new User(
                            rs.getString("ssoid"),
                            rs.getLong("ts"),
                            rs.getString("grp"),
                            rs.getString("type"),
                            rs.getString("subtype"),
                            rs.getString("url"),
                            rs.getString("orgid"),
                            rs.getString("formid"),
                            rs.getString("code"),
                            rs.getString("ltpa"),
                            rs.getString("sudirresponse"),
                            rs.getDate("ymdh"))
            ).forEach(user -> log.info("Found <" + user + "> in the database."));
        }
    }
}