package org.example.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.model.Animal;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerDemo {
    private static final String TOPIC_NAME = "AnimalWithSerializer";
    private static final String BOOTSTRAP_SERVER = "localhost:9092";
    private static KafkaConsumer<String, Animal> kafkaConsumer;
    private static final String group_ID = "group_id";

    public static void main(String[] args) {
        System.out.println("Code");
      //  KafkaConsumer kafkaConsumer = null;
        try {
            Properties properties = new Properties();
            properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
            properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.example.utils.ProducerSerializer");
            properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, group_ID);
            properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            kafkaConsumer = new KafkaConsumer<String, Animal>(properties);
            kafkaConsumer.subscribe(Arrays.asList(TOPIC_NAME));

            while (true) {
                ConsumerRecords<String, Animal> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
                System.out.println("Consumer Records");
                for (ConsumerRecord<String, Animal> consumerRecord : consumerRecords) {
                    System.out.println("Key : " + consumerRecord.key() + " Value : " + consumerRecord.value());
                    System.out.println("Parition : " + consumerRecord.partition() + " Offset : " + consumerRecord.offset());
                }
            }

        } catch (Exception e) {
            System.out.println("Love" + e);
        } finally {
            if (kafkaConsumer != null)
                kafkaConsumer.close();
        }
    }
}
