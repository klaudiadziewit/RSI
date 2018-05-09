import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.Agent;

public class Class2-5 extends Agent{
	protected void setup() {
		
		ParallelBehaviour par = new ParallelBehaviour();
		par.addSubBehaviour(new CyclicBehaviour()
		{
			public void action() {
				System.out.println("Cyclic 1");
			}
		});
		
		par.addSubBehaviour( new CyclicBehaviour()
		{
			public void action() {
				System.out.println("Cyclic 2");
			}
		});
		
		addBehaviour(par);
	}
}