/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springteam.springbatch.config;

import com.google.gson.Gson;
import com.springteam.springbatch.domain.ChemicalElement;
import com.springteam.springbatch.execution.ExecutionListener;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 *
 * @author siux
 */
@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = {"com.springteam.springbatch.main",
                                "com.springteam.springbatch.processor",
                                "com.springteam.springbatch.execution"})
public class SpringBatchConfiguration {
    
    @Autowired
    private JobBuilderFactory jobFactory;
    
    @Autowired
    private StepBuilderFactory stepFactory;
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private ResourceLoader resourceLoader;
    
    @Autowired
    private ExecutionListener executionListener;
    
    @Autowired
    private ItemReader<ChemicalElement> itemReader;
    
    @Bean
    public Job chemicalJob(@Qualifier("chemicalStep") Step step){
        return jobFactory.get("chemicalJob").start(step).build();        
    }
    
    @Bean
    public Step chemicalStep(ItemProcessor<ChemicalElement, ChemicalElement> itemProcessor){
            return stepFactory.get("chemicalStep")
                    .listener(executionListener)
                    .<ChemicalElement, ChemicalElement>chunk(10)
                    .reader(itemReader)
                    .writer(itemWriter())
                    .processor(itemProcessor)
                    .build();
    }
    
    @Bean
    @StepScope
    public FlatFileItemReader itemReader(@Value("file:/#{jobParameters['file.name']}") String filepath){
        FlatFileItemReader in = new FlatFileItemReader();
        in.setResource(resourceLoader.getResource(filepath));
        
        
        
        DefaultLineMapper mapper = new DefaultLineMapper();
        
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("name","isMetal","state");
        
        mapper.setLineTokenizer(tokenizer);
        
        
        BeanWrapperFieldSetMapper<ChemicalElement> filedMapper = new BeanWrapperFieldSetMapper<>();
        filedMapper.setTargetType(ChemicalElement.class);
        
        mapper.setFieldSetMapper(filedMapper);
        
        in.setLineMapper(mapper);
        
        return in;
    }
    
    @Bean
    public ItemWriter<ChemicalElement> itemWriter(){
        JdbcBatchItemWriter<ChemicalElement> writer = new JdbcBatchItemWriter<>();
        
        writer.setSql("INSERT INTO chemical_element (name, is_metal, state) values (:name, :isMetal, :state)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setDataSource(dataSource);
        
        
        return writer;
    }
    
    @Bean
    public Gson gson(){
        return new Gson();
    }
    
    @Bean
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        
        return databaseBuilder.setType(EmbeddedDatabaseType.H2).addScripts("classpath:/db/chemical-element-schema.sql",
                "classpath:/org/springframework/batch/core/schema-h2.sql").build();
    }
}
