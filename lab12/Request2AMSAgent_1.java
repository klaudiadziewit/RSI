import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import jade.domain.*;
import jade.domain.JADEAgentManagement.*;
import jade.content.onto.basic.*;

public class Request2AMSAgent_1 extends Agent {

  public void setup() {

    getContentManager().registerLanguage(new jade.content.lang.sl.SLCodec(0));
    getContentManager().registerOntology(JADEManagementOntology.getInstance());

    CreateAgent ca = new CreateAgent();
    ca.setAgentName("closer");
    ca.setClassName("AgentClose");
    ca.setContainer(new ContainerID("Main-Container", null));

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
        }
        protected void handleFailure(ACLMessage failure) {
          System.out.println("Error creating agent.\n"+failure);
        }
      } );
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    addBehaviour(new WakerBehaviour(this, 30000) {
      protected void onWake() {
        WhereIsAgentAction wa = new WhereIsAgentAction();
        wa.setAgentIdentifier(new AID("john", AID.ISLOCALNAME));
        Action actExpr = new Action(getAMS(), wa);
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(getAMS());
        request.setOntology(JADEManagementOntology.getInstance().getName());
        request.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
        request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        try {
          getContentManager().fillContent(request, actExpr);
          addBehaviour(new AchieveREInitiator(myAgent, request) {
            protected void handleInform(ACLMessage inform) {
              try {
                Result r = (Result)myAgent.getContentManager().extractContent(inform);
                ContainerID cid = (ContainerID) r.getValue();
                System.out.println("john is in "+cid);
              }
              catch (Exception e) {
                e.printStackTrace();
              }
            }
          });
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
  
}