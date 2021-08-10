package conversationEngineInporter;


// this class is to store common static functions
public class Functions {
	//keeps a string as an escaped string.
	public static String stringEscape(String s){
		  return s.replace("\\", "\\\\")
		          .replace("\t", "\\t")
		          .replace("\b", "\\b")
		          .replace("\n", "\\n")
		          .replace("\r", "\\r")
		          .replace("\f", "\\f")
		          .replace("\'", "\\'")
		          .replace("\"", "\\\"");
		}
}
