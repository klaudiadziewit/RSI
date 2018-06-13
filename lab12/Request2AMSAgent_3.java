import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREInitiator;
import jade.util.leap.Iterator;
import jade.util.leap.List;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.domain.*;
import jade.domain.JADEAgentManagement.*;
import java.util.Random;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.*;

public class Request2AMSAgent_3 extends Agent {

	Random random;
	public void setup() {
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		Profile profile = new ProfileImpl();
		Profile profile2 = new ProfileImpl();
		profile.setParameter(Profile.CONTAINER_NAME, "TestContainer");
		profile.setParameter(Profile.MAIN_HOST, "localhost");
		profile2.setParameter(Profile.CONTAINER_NAME, "TestContainer2");
		profile2.setParameter(Profile.MAIN_HOST, "localhost");
		ContainerController container = runtime.createAgentContainer(profile);
		ContainerController container2 = runtime.createAgentContainer(profile2);
		
		random = new Random();

		addBehaviour(new TickerBehaviour(this, 5000) { 
			protected void onTick() {
				getContentManager().registerLanguage(new jade.content.lang.sl.SLCodec(0));
				getContentManager().registerOntology(JADEManagementOntology.getInstance());
				QueryPlatformLocationsAction query = new QueryPlatformLocationsAction();
				Action action = new Action(getAID(),query);
			
				try {
					ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
					msg.addReceiver(getAMS());
					msg.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
					msg.setOntology(JADEManagementOntology.getInstance().getName());
					getContentManager().fillContent(msg, action);
					send(msg);
				} catch(CodecException e) {
					e.printStackTrace();
				} catch(OntologyException e) {
					e.printStackTrace();
				}
		
				try {
					ACLMessage received = blockingReceive(MessageTemplate.MatchSender(getAMS()));
					ContentElement content = getContentManager().extractContent(received);
					Result result = (Result) content;
					List listOfPlatforms = (List) result.getValue();
					Iterator iterator = listOfPlatforms.iterator();
					int rand = random.nextInt((int)listOfPlatforms.size());
					System.out.println("Found cointainers: ");
					while(iterator.hasNext()) {
						ContainerID next = (ContainerID) iterator.next();
						System.out.println(next.getName());	
					}
					doMove((ContainerID)listOfPlatforms.get(rand));
					System.out.println("Agent moved to " + ((ContainerID)listOfPlatforms.get(rand)).getName());
				} catch(UngroundedException e) {
					e.printStackTrace();
				} catch(CodecException e) {
					e.printStackTrace();
				} catch(OntologyException e) {
					e.printStackTrace();
				}
			}
		});
	}
}