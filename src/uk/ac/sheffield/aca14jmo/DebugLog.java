package uk.ac.sheffield.aca14jmo;

/**
 * uk.ac.sheffield.aca14jmo.DebugLog.java
 *
 * Class used to print debug information to the screen and toggle whether this is displayed
 */

public class DebugLog {
	private static boolean enabled = false;

	public static void enable() {
		enabled = true;
	}

	public static void disable() {
		enabled = false;
	}

	public static void println(Object o) {
		if (enabled) {
			System.out.println("DEBUG: " + o);
		}
	}
}
