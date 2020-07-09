package com.ibm;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.ibm.avro.OrderEvent;

public class BasicConsumer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("group.id", "my-group");
		props.put("bootstrap.servers", "172.17.146.23:9092");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
		props.put("schema.registry.url", "http://172.17.146.23:8081");

		KafkaConsumer<String, OrderEvent> consumer = new KafkaConsumer<String, OrderEvent>(props);
		consumer.subscribe(Collections.singletonList("avro_order_topic"));

		Boolean emptyPoll = false;

		while (!emptyPoll) {
			ConsumerRecords<String, OrderEvent> records = consumer.poll(Duration.ofMillis(500));
			System.out.println("Record Count: " + records.count());
			for (ConsumerRecord<String, OrderEvent> record : records) {
				System.out.printf("Received Message topic =%s, partition =%s, offset = %d, key = %s, value = %s\n",
						record.topic(), record.partition(), record.offset(), record.key(), record.value());
			}

			emptyPoll = records.isEmpty();

			consumer.commitSync();
			System.out.println("Committed Offset to kakfa...");
			System.out.println("Records to be polled next: " + !emptyPoll);
		}

		System.out.println("#### END Consumer ####");
	}

}
