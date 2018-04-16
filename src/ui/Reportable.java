package ui;

import java.io.PrintStream;
import java.io.PrintWriter;
/**
 *An  Interface class that has the following methods.
 */

public interface Reportable {
	/**
	 * method description for report
	 */
	void report();
	/**
	 * method that set destination by  getting PrintStream
	 * @param printStream use to write the destination
	 */
	void setDestination(PrintStream printStream);
	/**
	 * method that set destination by getting printWriter 
	 * @param printWriter use to send destination
	 */
	void setDestination(PrintWriter printWriter);
}
