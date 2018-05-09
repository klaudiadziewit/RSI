import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class Class1 extends Agent {
	protected void setup() {
	    System.out.println("Agent " +getLocalName()+ " starting...");
	    doDelete();
	} 

	protected void takeDown(){
		System.out.println("Agent " +getLocalName()+ " deleting...");
	}
}