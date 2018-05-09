import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class Class3 extends Agent {

	protected void setup() {
	    System.out.println("Agent " +getLocalName()+ " starting...");
 
	    addBehaviour(new OneShotBehaviour(this) {
	        public void action() {
	          System.out.println("in progress");
	        } 
	    });

	    addBehaviour(new CyclicBehaviour(this) {
	        public void action() {
	          System.out.println("cyclic process");
	        } 
	    });
	  } 
}