package br.inatel.sd.labmqtt.client;

import java.util.Random;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class TemperatureSensorPublisherLoop {
    public static void main(String[] args) throws InterruptedException, MqttException {

        String publisherId = UUID.randomUUID().toString();
        IMqttClient publisher = new MqttClient(MyConstants.BROKER_URI, publisherId);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        publisher.connect(options);

        MqttMessage msg = getSoilTemperature();
        msg.setQos(0);
        msg.setRetained(true);

        for (int i = 0; i < 10; i++) {
            try {
                publisher.publish(MyConstants.TOPIC_1, msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(2000);
        }
        
        publisher.disconnect();
    }

    private static MqttMessage getSoilTemperature() {
        Random r = new Random();
        double temperature = 80 + r.nextDouble() * 20.0;
        byte[] payload = String.format("T:%04.2f", temperature).getBytes();
        return new MqttMessage(payload);
    }
}
