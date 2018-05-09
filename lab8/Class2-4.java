import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.Agent;

public class Class2-4 extends Agent{
	protected void setup() {
		System.out.println("Starting...");

		SequentialBehaviour threeStepBehaviour = new SequentialBehaviour();
		threeStepBehaviour.addSubBehaviour(new OneShotBehaviour()
		{
			public void action() {
				System.out.println("First step");
			}
		});

		threeStepBehaviour.addSubBehaviour(new OneShotBehaviour()
		{
			public void action() {
				System.out.println("Second step");
			}
		});
		
		threeStepBehaviour.addSubBehaviour(new OneShotBehaviour()
		{
			public void action() {
				System.out.println("Third step");
				removeBehaviour(threeStepBehaviour);
				System.out.println("Deleting...";
			}
		});
		addBehaviour(threeStepBehaviour);
	}
}