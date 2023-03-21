package org.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.model.Animal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Properties;

public class KafkaProducerDemo {

    private static final String TOPIC_NAME = "AnimalWithSerializer";

    private static final String BOOTSTRAP_SERVER = "localhost:9092";
    private static KafkaProducer<String, Animal> kafkaProducer;

    private static ArrayList<Animal> getAnimalList() {
        ArrayList<Animal> animalList = new ArrayList<>();
        Animal lion = new Animal("Lion", "Brown", "Wild");
        Animal cat = new Animal("Cat", "Black", "Domestic");
        animalList.add(lion);
        animalList.add(cat);
        return animalList;
    }

    public static void main(String[] args) {

       // KafkaProducer kafkaProducer = null;
        try {
            Properties properties = new Properties();
            properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
            properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.example.utils.ProducerSerializer");
            kafkaProducer = new KafkaProducer<String, Animal>(properties);
            ArrayList<Animal> animalList = getAnimalList();

            for (Animal animal : animalList) {
                ProducerRecord<String, Animal> producerRecord = new ProducerRecord<>(TOPIC_NAME, animal);
                kafkaProducer.send(producerRecord);
                System.out.println("Successfully sent to the Topic " + animal.getName());
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (kafkaProducer != null) {
                kafkaProducer.flush();
                kafkaProducer.close();
            }
        }
    }
}
