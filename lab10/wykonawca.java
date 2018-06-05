import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.FIPAException;

public class wykonawca extends Agent{

    DFAgentDescription dfd;
    ServiceDescription sd;

    protected void setup() {
        super.setup();

        dfd = new DFAgentDescription();
        dfd.setName( getAID() );
        sd  = new ServiceDescription();
        sd.setType( "POTEGA" );
        sd.setName( getLocalName() );
        dfd.addServices(sd);

        try {
            DFService.register(this, dfd );
        }
        catch (FIPAException e) { e.printStackTrace(); }

    }

    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {e.printStackTrace();}

    }
}