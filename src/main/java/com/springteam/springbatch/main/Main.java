/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springteam.springbatch.main;

import com.google.gson.Gson;
import com.springteam.springbatch.config.SpringBatchConfiguration;
import com.springteam.springbatch.domain.ChemicalElement;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author siux
 */
@Component
public class Main {

    private final Gson gson;

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Autowired
    public Main(Gson gson) {
        this.gson = gson;
    }

    public static void main(String[] args) throws Exception {

        //SPRING BATCH ONLY
//        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBatchConfiguration.class)) {
//            Main main = ctx.getBean(Main.class);
//            
//            JobLauncher launcher = ctx.getBean(JobLauncher.class);
//            Job job = ctx.getBean(Job.class);
//            
//            JobParameters parameters = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
//            
//            launcher.run(job, parameters);
//            
//            ChemicalElement ce = new ChemicalElement();
//            
//            ce.setName("Hydrogen");
//            ce.setIsMetal(false);
//            ce.setEnergyLevels(new int[]{1});
//            ce.setState("Gas");
//            
//            
//            try (PrintWriter out = new PrintWriter(new FileWriter("chemicalElements.json"))) {
//                out.println(main.gson.toJson(ce));
//            }
//            
//            
//            System.in.read();
//        }
        // SRRING INTEGRATION
//        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:/batch-integration-context.xml");
//        
//        LOGGER.info("Application started...");
//        
//        System.in.read();
//        
//        ctx.refresh();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list);

        List<Integer> intSquared = list.stream().map(i -> i * i).collect(Collectors.toList());

        System.out.println(intSquared);

    }
}
