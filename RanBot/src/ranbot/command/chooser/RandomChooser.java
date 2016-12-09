package ranbot.command.chooser;

import java.util.Random;

public class RandomChooser implements Chooser {

	protected final Random random;

	public RandomChooser() {
		random = new Random();
	}

	@Override
	public String choose(String[] choices) {
		return choices[random.nextInt(choices.length)];
	}

	@Override
	public String getChoiceStatement(String choice) {
		return "I choose " + choice + "!";
	}
}
