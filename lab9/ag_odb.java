import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

public class ag_odb extends Agent{
	
	protected void setup() {
		addBehaviour(new CyclicBehaviour(this) {
			public void action() {
				ACLMessage msg = myAgent.receive();
				if (msg != null) {
					ACLMessage reply = msg.createReply();
					if (msg.getPerformative() == ACLMessage.REQUEST) {
						System.out.println("Received REQUEST message from agent "+msg.getSender().getName());
						System.out.println("Message : "+msg.getContent());
						reply.setPerformative(ACLMessage.INFORM);
						reply.setContent("Done");
					} 
					
					else if(msg.getPerformative() == ACLMessage.CFP){
						System.out.println("Received CFP message from agent "+msg.getSender().getName());
						System.out.println("Message : "+msg.getContent());
						reply.setPerformative(ACLMessage.REQUEST);
						reply.setContent("One more time");
					}
					
					else {
						reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
						reply.setContent("Unable to understand content");
					}
					
					myAgent.send(reply);
				}
				
				else {
					block();
				}
			}
		});
	}
}
