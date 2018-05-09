import java.util.Scanner;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class Class5 extends Agent {
	protected void setup() {
	    System.out.println("Agent " +getLocalName()+ " starting...");
	    addBehaviour(new DeleteIfMinus());
	}

	class DeleteIfMinus extends Behaviour{
		Scanner input=new Scanner(System.in);
		int a;
		public void action(){
			a=input.nextInt();
			if(a<0){
				System.out.println("deleting...");
				removeBehaviour(this);
			}
        
        }
        
		public boolean done (){
			return (a<0);
		}	
    }
}