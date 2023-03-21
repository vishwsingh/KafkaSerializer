package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.example.model.Animal;

import javax.sql.rowset.serial.SerialException;

public class ProducerSerializer implements Serializer<Animal> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Animal animal) {

        try {
            if(animal == null) {
                System.out.println("Animal object is null, can't serialize it");
                return null;
            }
            System.out.println("Serializing....");
            return objectMapper.writeValueAsBytes(animal);
            //return ObjectMapper.writeValueAsBytes(animal);
        } catch(Exception e) {
           // throw new SerialException("Error when serializing animal to byte array");
            throw new SerializationException("Error when serializing animal to byte array");
        }
    }
}
