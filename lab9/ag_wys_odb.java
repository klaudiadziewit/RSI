import java.util.Random;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

public class ag_wys_odb extends Agent{
    Random randomNumber;
    protected void setup() {
        System.out.println("Hello World! My name is: "+getLocalName());
        
        addBehaviour(new CyclicBehaviour(this) {
        public void action() {
            randomNumber = new Random();
            int state = randomNumber.nextInt(2);
            System.out.println(state);
            if (state == 0) {
                ACLMessage m = new ACLMessage(ACLMessage.CFP);
                m.addReceiver(new AID("Ala", AID.ISLOCALNAME));
                //m.setOntology("presence");
                m.setContent("CFP MESSAGE");
                send(m);
            }
            else{
                ACLMessage m = new ACLMessage(ACLMessage.REQUEST);
                m.addReceiver(new AID("Ala", AID.ISLOCALNAME));
                //m.setOntology("presence");
                m.setContent("REQUEST MESSAGE");
                send(m);
            }

            ACLMessage msg = myAgent.receive();
            if (msg != null) {
                System.out.println("MESSAGE CONTENT: " + msg.getContent());
                if (msg.getPerformative() == ACLMessage.INFORM){
                      myAgent.doDelete();
                }
            }
            else {
                block();
            }
        }
        });
    }
}
