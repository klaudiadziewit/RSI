import java.util.Random;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class zlecajacy extends Agent{
    zlecajacy my;
    Random random;
    Integer number;

    protected void setup() {
        super.setup();

        my = this;
        random = new Random();
        number = null;

        addBehaviour(new OneShotBehaviour(this) {
            public void action() {
                number = (int)(Math.round(random.nextDouble()*100));
                System.out.println("Wysolowana liczba:   " + number);
            }
        });

        FSMBehaviour fsmBehaviour = new FSMBehaviour(this) {
            public int onEnd() {
                myAgent.doDelete();
                return super.onEnd();
            }
        };

        addBehaviour(new TickerBehaviour(this, 2000) {
            protected void onTick() {
                System.out.println("Sprawdzanie us³ugi POTÊGA");

                DFAgentDescription dfd = new DFAgentDescription();
                ServiceDescription sd = new ServiceDescription();
                sd.setType( "POTEGA" );
                dfd.addServices(sd);

                SearchConstraints ALL = new SearchConstraints();
                ALL.setMaxResults(new Long(-1));
                try
                {
                    DFAgentDescription[] result = DFService.search(myAgent, dfd, ALL);
                    System.out.println("Ilosc uslug:    " + result.length);
                    System.out.println(" ");
                    if(result.length>0) {
                        send(result[0].getName());
                        recieve(this);
                    }
                }
                catch (FIPAException fe) {
                    System.out.println("ERROR"); }
            }
        });
    }
    private void send(AID aid) {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(aid);
        msg.setContent(Integer.toString(number));
        send(msg);
        System.out.println("Wysylanie wiadomoœci REQUEST przez zlecajacego");
    }
    private void recieve(Behaviour b) {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage msg1 = blockingReceive(mt);
        if(msg1!= null) {
            if(msg1.getPerformative() == ACLMessage.INFORM){
                System.out.println("Odebranie wiadomosci inform przez zlecajacego  " +  msg1.getContent());
                my.doDelete();
            }
        }
        else {
            b.block();
        }
    }
    protected void takeDown() {
        System.out.println("Zakonczenie dzialania przez zlecajacego");
    }
}