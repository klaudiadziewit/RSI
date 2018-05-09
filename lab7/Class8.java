import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class Class8 extends Agent {
	protected static TickerBehaviour a;

	protected void setup() {
	    System.out.println("Agent " +getLocalName()+ " starting...");
	    
	    addBehaviour(new TickerBehaviour(this, 2000) {
		    protected void onTick() {
		        System.out.println("a small tick");
		    } 
		});

	    addBehaviour(a=new TickerBehaviour(this, 5000) {
		    protected void onTick() {
		        System.out.println("a big tick");
		    }  
		});

	    addBehaviour(new WakerBehaviour(this, 50000) {
		    protected void handleElapsedTimeout() {
		        System.out.println("removing big tick");
		        removeBehaviour(a);
		    } 
		});

	    addBehaviour(new WakerBehaviour(this, 100000) {
		    protected void handleElapsedTimeout() {
		        System.out.println("deleting Agent");
		        myAgent.doDelete();
		    } 
		});
	} 
}