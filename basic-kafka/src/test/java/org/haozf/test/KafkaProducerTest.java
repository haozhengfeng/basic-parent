package org.haozf.test;

import java.util.Map;

import org.haozf.kafka.producer.KafkaProducerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class KafkaProducerTest {
	
	@Autowired
	KafkaProducerServer kafkaProducer;
	
	@Test
	public void kafkaProducer(){
		
		String topic = "test";
		String value = "test";
		String ifPartition = "1";
		Integer partitionNum = 3;
		String role = "test";// 用来生成key
		Map<String, Object> res = kafkaProducer.sndMesForTemplate(topic, value, ifPartition, partitionNum, role);

		System.out.println("测试结果如下：===============");
		String message = (String) res.get("message");
		String code = (String) res.get("code");

		System.out.println("code:" + code);
		System.out.println("message:" + message);
	}
	
}
