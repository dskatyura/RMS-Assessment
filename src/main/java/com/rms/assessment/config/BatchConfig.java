package com.rms.assessment.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import batch.StringEncryptor;
 
@Configuration
@EnableBatchProcessing
public class BatchConfig {
     
    @Autowired
    private JobBuilderFactory jobs;
 
    @Autowired
    private StepBuilderFactory steps;
     
    @Bean
    @StepScope
    public FlatFileItemReader<String> reader() {
	    	DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
	    	tokenizer.setDelimiter("\n");
	    	tokenizer.setNames(new String[]{"line"});
	    	
        return new FlatFileItemReaderBuilder<String>()
            .name("stringItemReader")
            .resource(new FileSystemResource("")).lineTokenizer(tokenizer)
            .build();
    }

    @Bean
    public StringEncryptor processor() {
        return new StringEncryptor();
    }

    @Bean
    public FlatFileItemWriter<String> writer() {
      return new FlatFileItemWriterBuilder<String>()
          .name("stringItemWriter")
          .resource(new FileSystemResource("target/test-outputs/sample-encrypt.txt"))
          .lineAggregator(new PassThroughLineAggregator<>()).build();
    }
    
    @Bean
    public Job encryptionJob() {
      return jobs.get("encryptionJob")
          .start(encryptionStep()).build();
    }

    @Bean
    public Step encryptionStep() {
      return steps.get("step1")
          .<String, String>chunk(5).reader(reader())
          .processor(processor()).writer(writer()).build();
    }
}