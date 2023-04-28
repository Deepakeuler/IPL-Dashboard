package io.deepakeuler.ipldashboard.data;
import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    
@Bean
public FlatFileItemReader<MatchInput> reader() {
  return new FlatFileItemReaderBuilder<Person>()
    .name("personItemReader")
    .resource(new ClassPathResource("match-data.csv"))
    .delimited()
    .names(new String[]{"firstName", "lastName"})
    .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
      setTargetType(MatchInput.class);
    }})
    .build();
}

@Bean
public PersonItemProcessor processor() {
  return new PersonItemProcessor();
}

@Bean
public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
  return new JdbcBatchItemWriterBuilder<Person>()
    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
    .sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
    .dataSource(dataSource)
    .build();
}
    
}

