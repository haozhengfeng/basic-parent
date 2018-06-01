package org.haozf.kafka;

import java.util.Map;

import org.haozf.kafka.producer.KafkaProducerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProducterController {
	
	@Autowired
	KafkaProducerServer kafkaProducer;

	@RequestMapping("/sendMsg")
    @ResponseBody
    public void sendMsg(String msg){
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
