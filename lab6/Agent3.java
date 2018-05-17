import jade.core.Agent;
public class Agent3 extends Agent{
	int argsAmount;
	
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " has started");
		Object[] arguments = getArguments();
		argsAmount = Integer.parseInt(arguments[0].toString());
		System.out.println("Argument " + argsAmount);
		for(int i=0; i<argsAmount; i++) {
			System.out.println("Agent " + getLocalName() + " Argument no: " + i);
		}
	}
}
