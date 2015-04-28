package no.ntnu.ttm4115.hhh.heater.component;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public float DesiredTemperature;
	public float CurrentTemperature;

	public Parameters MQTTSetup() {
		MQTTConfigParam m = new MQTTConfigParam("dev.bitreactive.com");
		m.addSubscribeTopic("hhh/heaters/heater1");
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
	}

	public MQTTMessage createMessage() {
		String payload = "Current:"+" "+String.valueOf(CurrentTemperature);
		byte[] bytes = payload.getBytes();
	    String topic = "hhh/heaters";
		MQTTMessage message = new MQTTMessage(bytes, topic);
		message.setQoS(2);
		return message;
	}

}
