package com.example.ru.smartsoft.csv.reader.reader.processor;

import com.example.ru.smartsoft.csv.reader.reader.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<User, User> {

    private static final Logger log = LoggerFactory.getLogger(UserItemProcessor.class);

    @Override
    public User process(User user) {
        final String ssoid = user.getSsoid();
        final Long ts = user.getTs();
        final String grp = user.getGrp();
        final String type = user.getType();
        final String subtype = user.getSubtype();
        final String url = user.getUrl();
        final String orgid = user.getOrgid();
        final String formid = user.getFormid();
        final String code = user.getCode();
        final String ltpa = user.getLtpa();
        final String sudirresponse = user.getSudirresponse();
        final String ymdh = user.getYmdh();

        final User transformedUser = new User(ssoid, ts, grp, type, subtype, url, orgid, formid, code, ltpa, sudirresponse, ymdh);
        System.out.println("Converting (" + user + ") into (" + transformedUser + ")");
        log.info("Converting (" + user + ") into (" + transformedUser + ")");
        return transformedUser;
    }
}