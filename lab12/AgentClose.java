import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import jade.domain.*;
import jade.domain.JADEAgentManagement.*;
import jade.content.onto.basic.*;

public class AgentClose extends Agent {

  public void setup() {

    getContentManager().registerLanguage(new jade.content.lang.sl.SLCodec(0));
    getContentManager().registerOntology(JADEManagementOntology.getInstance());

    ShutdownPlatform ca = new ShutdownPlatform();

    Action actExpr = new Action(getAMS(), ca);

    ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
    request.addReceiver(getAMS());
    request.setOntology(JADEManagementOntology.getInstance().getName());
    request.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
    request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
	
    try {
      getContentManager().fillContent(request, actExpr);
      addBehaviour(new AchieveREInitiator(this, request) {
        protected void handleInform(ACLMessage inform) {
          System.out.println("Agent successfully created");
          System.out.println("Platform will be closed");
        }
        protected void handleFailure(ACLMessage failure) {
          System.out.println("Error creating agent.\n"+failure);
        }
      } );
    }
    catch (Exception e) {
      e.printStackTrace();
    }

}

}