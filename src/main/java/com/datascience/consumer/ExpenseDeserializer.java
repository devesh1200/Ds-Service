package com.datascience.consumer;

import com.datascience.dto.ExpenseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import java.util.Map;

public class ExpenseDeserializer implements Deserializer<ExpenseDto>{

    @Override
    public void configure(Map configs, boolean isKey) {
        //null
    }

    @Override
    public ExpenseDto deserialize(String arg0, byte[] arg1) {
        ObjectMapper objectMapper = new ObjectMapper();
        ExpenseDto expenseDto = null;
        try{
            objectMapper.readValue(arg1,ExpenseDto.class);
        }catch (Exception e){
        }
        return expenseDto;
    }





    @Override
    public void close() {
     //null
    }
}
