package com.example.ru.smartsoft.csv.reader.reader.config;

import com.example.ru.smartsoft.csv.reader.reader.listener.JobCompletionNotificationListener;
import com.example.ru.smartsoft.csv.reader.reader.model.User;
import com.example.ru.smartsoft.csv.reader.reader.processor.UserItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public FlatFileItemReader<User> reader() {
        return new FlatFileItemReaderBuilder<User>()
                .name("userItemReader")
                .lineMapper(lineMapper())
                .linesToSkip(1)
                .resource(new ClassPathResource("sample_data.csv"))
                .delimited()
                .names(new String[]{"ssoid", "ts", "grp", "type", "subtype", "url", "orgid", "formid", "code", "ltpa", "sudirresponse", "ymdh"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
                    setTargetType(User.class);
                }})
                .build();
    }

    @Bean
    public UserItemProcessor processor() {
        return new UserItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<User> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<User>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO usr (ssoid, ts, grp, type, subtype, url, orgid, formid, code, ltpa, sudirresponse, ymdh) " +
                        "VALUES (:ssoid, :ts, :grp, :type, :subtype, :url, :orgid, :formid, :code, :ltpa, :sudirresponse, :ymdh)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<User> writer) {
        return stepBuilderFactory.get("step1")
                .<User, User>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public LineMapper<User> lineMapper() {
        DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("ssoid", "ts", "grp", "type", "subtype", "url", "orgid", "formid", "code", "ltpa", "sudirresponse", "ymdh");

        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }
}
