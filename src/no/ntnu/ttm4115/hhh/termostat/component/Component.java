package no.ntnu.ttm4115.hhh.termostat.component;

import no.ntnu.item.arctis.runtime.Block;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters;

public class Component extends Block {

	public float CurrentTemperature;
	public float DesiredTemperature;

	public Parameters MQTTSetup() {
		MQTTConfigParam m = new MQTTConfigParam("dev.bitreactive.com");
		m.addSubscribeTopic("hhh/heaters");
		m.addSubscribeTopic("hhh/termostats");
		Parameters p = new Parameters(m);
		return p;
	}

	public void msgHandler(MQTTMessage m) {
		
		String[] message = new String(m.getPayload()).split("\\s+");
		if (message[0].equals("Current:")) {
			this.CurrentTemperature = Float.valueOf(message[1]); 
		} else if (message[0].equals("Desired:")) {
			this.DesiredTemperature = Float.valueOf(message[1]);
		}
	    System.out.println("---------- Received Message ----------");
	    System.out.println("Sent to topic: " + m.getTopic());
	    System.out.println("Payload: " + new String(m.getPayload()));
	    System.out.println("--------------------------------------");
	}

	public MQTTMessage createMessage() {
		String payload = "Desired:"+" "+String.valueOf(DesiredTemperature);
		byte[] bytes = payload.getBytes();
	    String topic = "hhh/heaters";
		MQTTMessage message = new MQTTMessage(bytes, topic);
		message.setQoS(2);
		return message;
	}

}
