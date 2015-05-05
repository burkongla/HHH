package no.ntnu.ttm4115.hhh.heater.component;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public float DesiredTemperature;
	public float CurrentTemperature;
	public boolean Status;
	public java.lang.String looPayload;

	public Parameters MQTTSetup() {
		MQTTConfigParam m = new MQTTConfigParam("dev.bitreactive.com");
		m.addSubscribeTopic("hhh/heaters");
		Parameters p = new Parameters(m);
		return p;
	}

	public void msgHandler(MQTTMessage m) {
		System.out.println("Entered msgHandler.");
		String[] message = new String(m.getPayload()).split("\\s+");
		
		if(message[0].equals("Heating:")) {
			setStatus(true);
			setLooPayload(message[0] + " " + message[1]);
			
		} else if (message[0].equals("Cooling:")) {
			setStatus(true);
			setLooPayload(message[0] + " " + message[1]);
		} else if (message[0].equals("Off:")) {
			setStatus(false);
		}
	}

	public MQTTMessage createMessage() {
		System.out.println("Sending message");
		String payload = getLooPayload();
		byte[] bytes = payload.getBytes();
	    String topic = "hhh/thermostats";
	    System.out.println(payload);
		MQTTMessage message = new MQTTMessage(bytes, topic);
		message.setQoS(2);
		return message;
	}

	public float getDesiredTemperature() {
		return DesiredTemperature;
	}

	public void setDesiredTemperature(float desiredTemperature) {
		DesiredTemperature = desiredTemperature;
	}

	public float getCurrentTemperature() {
		return CurrentTemperature;
	}

	public void setCurrentTemperature(float currentTemperature) {
		CurrentTemperature = currentTemperature;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	public java.lang.String getLooPayload() {
		return looPayload;
	}

	public void setLooPayload(java.lang.String looPayload) {
		this.looPayload = looPayload;
	}
	
	

}
