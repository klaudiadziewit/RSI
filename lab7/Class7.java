import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class Class7 extends Agent {
	protected void setup() {
	    System.out.println("Agent " +getLocalName()+ " starting...");
	    addBehaviour(new First());
	    addBehaviour(new ThreeStepBehaviour());
	}

	class ThreeStepBehaviour extends Behaviour{
		private int step=0;
		public void action(){
			
			switch(step){
			case 0:
				System.out.println("First step");
				addBehaviour(new Second());
			step++;
			break;
			case 1:
				System.out.println("Second step");
				step++;
				break;	
			case 2:
				System.out.println("Third step");
				removeBehaviour(this);
				step++;
				break;
			}
		}

		public boolean done (){
			return step == 3;
		}
	}

	class First extends OneShotBehaviour{
		
		public void action(){
			System.out.println("First behaviour");	
		}
    }

	class Second extends OneShotBehaviour{
		public void action(){
			System.out.println("Second behaviour");
		}
    }
}