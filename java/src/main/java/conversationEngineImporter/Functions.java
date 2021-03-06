package conversationEngineImporter;

import java.util.Arrays;

// this class is to store common static functions
public class Functions {
	/**will replace each instance of target in str by replacement until no instance of target is left.
	 */
	private static Boolean debug = false;
	private static long time = System.nanoTime();
	
	public static void debug(String s) {
		if (debug) {
			System.out.println(String.format("%s %d", s,(System.nanoTime()-time)/1000000));
		}
	}
	
	public static String recReplace(String str, String target, String replacement) {
		String str2 = str.replace(target, replacement);
		if (str == str2) {
			return str2;
		} else {
			return recReplace(str2,target,replacement);
		}
	}

	// keeps a string as an escaped string.
	public static String stringEscape(String s) {
		return s.replace("\\", "\\\\").replace("\t", "\\t").replace("\b", "\\b").replace("\n", "\\n")
				.replace("\r", "\\r").replace("\f", "\\f").replace("\"", "\\\"");
	}

	public static String closestString(String[] list, String target) {
		String closest = "";
		int best = Integer.MAX_VALUE;

		for (String item : list) {
			int distance = levenshteinDistance(item, target);

			if (distance == 0) {// if it is an exact match stop looking
				return item;
			}

			if (distance < best) {
				best = distance;
				closest = item;
			}
		}

		return closest;
	}

	// ---- Levenshtein distance (similarity between two strings) ----
	// source:https://www.baeldung.com/java-levenshtein-distance
	public static int levenshteinDistance(String x, String y) {
		int[][] dp = new int[x.length() + 1][y.length() + 1];

		for (int i = 0; i <= x.length(); i++) {
			for (int j = 0; j <= y.length(); j++) {
				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else {
					dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)),
							dp[i - 1][j] + 1, dp[i][j - 1] + 1);
				}
			}
		}

		return dp[x.length()][y.length()];
	}

	private static int costOfSubstitution(char a, char b) {
		return a == b ? 0 : 1;
	}

	private static int min(int... numbers) {
		return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
	}
	// ---- Levenshtein distance ----
	// source:https://www.baeldung.com/java-levenshtein-distance
}
