/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springteam.springbatch.processor;

import com.springteam.springbatch.domain.ChemicalElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 *
 * @author siux
 */
@Component("chemicalElementProcessor")
public class ChemicalElementProcessor implements ItemProcessor<ChemicalElement, ChemicalElement>{

    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChemicalElementProcessor.class);
    
    @Override
    public ChemicalElement process(ChemicalElement i) throws Exception {
        
        ChemicalElement transformed = new ChemicalElement();
        transformed.setName("_" + i.getName().toUpperCase() + "_");
        transformed.setEnergyLevels(i.getEnergyLevels());
        transformed.setIsMetal(i.isIsMetal());
        transformed.setState(i.getState() + " state");
        
        LOGGER.info("Transformation:   " + i + "     ----->   " + transformed);
        
        return transformed;
    }
    
}
