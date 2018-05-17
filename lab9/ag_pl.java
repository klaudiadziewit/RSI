import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ag_pl extends Agent {
    private MessageTemplate template = MessageTemplate.MatchLanguage("Polski");
    
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = myAgent.receive(template);
                if (msg != null) {
                        System.out.println("SENDER: " + msg.getSender().getName());
                        System.out.println("MESSAGE: " + msg.getContent());
                }
                else {
                    block();
                }
            }
        } );
    }
}
