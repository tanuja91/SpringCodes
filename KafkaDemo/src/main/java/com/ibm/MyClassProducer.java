package com.ibm;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import com.acme.avro.*;
import com.ibm.avro.CustomerEvent;


public class MyClassProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.0.18:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		props.put("schema.registry.url", "http://192.168.0.18:8081");
		System.out.println("####set properties####");
		try (KafkaProducer<String, MyClass> producer = new KafkaProducer<String, MyClass>(props)) {
			System.out.println("####scall the producer class####");
			for (int i = 1; i <= 10; i++) {
				MyClass myClass = new MyClass();

				myClass.setCreatedAt("Student_" + i);
				myClass.setText("Student Name is BOB_"+i);
				
				User user = new User();
				user.setId( Integer.toString(i));
				user.setName("Student Name is BOB_"+i);
				user.setAliasName("Student Nick Name is BOB_"+i);
				myClass.setUser(user);
				System.out.println("####set values in pojo class####");
				ProducerRecord<String, MyClass> record = new ProducerRecord<String, MyClass>(
					"avro_myClass_topic",myClass.getCreatedAt().toString(), myClass);
				producer.send(record, new Callback() {
					
					@Override  	
					public void onCompletion(RecordMetadata metadata, Exception exception) {
						if(exception!=null) {
							exception.getMessage();
						}else {
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


