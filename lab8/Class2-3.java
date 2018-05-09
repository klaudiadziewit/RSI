import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.Agent;

public class Class2-3 extends Agent{
	protected void setup() {
		
		System.out.println("Starting...");
		ParallelBehaviour par = new ParallelBehaviour();
		par.addSubBehaviour( new OneShotBehaviour()
		{
			public void action() {
				System.out.println("First step");
			}
		});

		par.addSubBehaviour( new OneShotBehaviour()
		{
			public void action() {
				System.out.println("Second step");
			}
		});
		
		par.addSubBehaviour( new OneShotBehaviour()
		{
			public void action() {
				System.out.println("Third step");
				removeBehaviour(par);
				System.out.println("Deleting...");
			}
		});
		addBehaviour( par );
	}
}