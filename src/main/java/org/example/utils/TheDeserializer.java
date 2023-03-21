package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.model.Animal;

import javax.sql.rowset.serial.SerialException;

public class TheDeserializer implements Deserializer<Animal> {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Animal deserialize(String s, byte[] data) {

        try {
            if(data == null) {
                System.out.println("null value");
                return null;
            } else {
                System.out.println("Deserializing");
                return objectMapper.readValue(new String(data), Animal.class);
            }
        } catch(Exception e) {
            throw new SerializationException("Error when Deserializing byte array to animal" + e);
        }
    }
}
