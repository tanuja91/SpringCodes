package com.ibm;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.ibm.avro.OrderEvent;

public class AsyncOrderProducer {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put("bootstrap.servers", "172.17.146.23:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		props.put("schema.registry.url", "http://172.17.146.23:8081");

		try (KafkaProducer<String, OrderEvent> producer = new KafkaProducer<String, OrderEvent>(props)) {
			for (int i = 1; i <= 10; i++) {
				OrderEvent ord = new OrderEvent();
				ord.setCustid("ID_"+i);
				ord.setOrderId("ORD_" + i);
				ord.setProductId("PRDT");
				ord.setQuantity("1");
				ProducerRecord<String, OrderEvent> record = new ProducerRecord<String, OrderEvent>("avro_order_topic",
						"ORD_" + i, ord);
				producer.send(record, new Callback() {

					@Override
					public void onCompletion(RecordMetadata metadata, Exception exception) {
						if (exception != null) {
							exception.getMessage();
						} else {
							System.out.println("####Kafka Record Posted####");
							System.out.println("Offset: " + metadata.offset());
							System.out.println("Partition: " + metadata.partition());
							System.out.println("Timestamp: " + metadata.timestamp());
							System.out.println("###########################");

						}

					}
				});
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
