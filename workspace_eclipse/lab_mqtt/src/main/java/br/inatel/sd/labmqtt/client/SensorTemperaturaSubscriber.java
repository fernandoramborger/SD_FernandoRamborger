package br.inatel.sd.labmqtt.client;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class TemperatureSensorSubscriber {
	
	public static void main(String[] args) throws MqttException{
		String subscriberId = UUID.randomUUID().toString();
		IMqttClient subscriber = new MqttClient(MyConstants.BROKER_URI, subscriberId);
		
		MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(true);
		options.setConnectionTimeout(10);
		subscriber.connect(options);
		
		System.out.println("Waiting for messages");
		subscriber.subscribe(MyConstants.TOPIC_1, (topic, msg) -> {
			byte[] payload = msg.getPayload();
			System.out.println("Payload: " + new String(payload));
			System.out.println("Topic: " + topic);
		});
		
	}

}
