package ranbot.command.chooser;

/**
 * Chooser behavior.
 */
public interface Chooser {
	/**
	 * Chooses one among the given choices. This might not choose anything at
	 * all from the choices, depending on the implementation, and return null
	 * instead.
	 * 
	 * @param choices
	 *            the choices to choose from.
	 * @return The choice made.
	 */
	String choose(String[] choices);

	/**
	 * Retrieves the statement to go along with the given choice. {@code choice}
	 * can be null, depending on the implementation. Anything returned by
	 * {@link #choose(String[])} should be accepted by this function.
	 * 
	 * @param choice
	 *            The choice to build the statement on.
	 * @return The statement regarding the given choice.
	 */
	String getChoiceStatement(String choice);
}
