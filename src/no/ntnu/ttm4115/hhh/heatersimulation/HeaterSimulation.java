package no.ntnu.ttm4115.hhh.heatersimulation;

import java.util.Random;

import no.ntnu.item.arctis.runtime.Block;

public class HeaterSimulation extends Block {
	private int id = 1;

	public int randomDuration() {
		int min = 1;
		int max = 5;
		int random = min + (int)(Math.random() * ((max - min) + 1));
		return random*1800;
	}

	public int createTemp() throws InterruptedException {
		 int max =30;
		 int min =15;
		while (true) {
			Random rand = new Random();
			int temp =rand.nextInt((max-min)+1)+min;
			return temp;
      // 	System.out.println(temp);
		  //System.out.println(new Date());
		    //Thread.sleep(5 * 1000);
		}
   }
		
	public void stop() {
	id =1;
	}

	

}
