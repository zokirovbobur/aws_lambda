package com.epam;

import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class HttpApiRequestHandlerTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testWelcomeLambda(){
        String string = "{\n" +
            "  \"id\": \"3\",\n" +
            "  \"price\": \"13\"\n" +
            "}";
        System.out.println(fromInput(string));
    }

    public Product fromInput(Object input){
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = null;
        try {
            map = objectMapper.readValue((String)input, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String id = map.get("id");
        String price = map.get("price");
        return new Product(id,price);
    }
}
