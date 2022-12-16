package conversationEngineLine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

import org.json.simple.JSONObject;

import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.Functions;
import conversationEngineImporter.PluginLoader;
import conversationEngineImporterInterfaces.CEScheduledCommand;
import conversationEngineImporterInterfaces.ConversationLineInterface;

public abstract class ConversationNodeJsonParser {

	public static LinkedList<ConversationLineInterface> linetypes = new LinkedList<ConversationLineInterface>();

	public static void parseBody(String body, ConversationNode node) {

		String lines[] = body.split("(\\r?\\n)|((?=\\[\\[)|(?<=\\]\\]))|((?=<<)|(?<=>>))"); // splits before [[ and << ,
		// after ]] and >> and it
		// splits and removes next
		// lines next-lines

		for (int i = lines.length - 1; i >= 0; i--) { // go over array backwards (since we want the linkedlist to be in
														// order)
			if (lines[i] == "" && i > 0 && lines[i - 1] != "") { // if this line is empty and the next line is not also
																	// empty ignore it. do not display empty lines
			} else if (lines[i].matches("\\[\\[([^\\|]*)\\|([^\\|]*)\\]\\]")) { // if the line is in the format of [[
																				// some text | some text ]]
				PointerLine pointerLine = new PointerLine(lines[i], node);// convert input to PointerLine.
				node.lines.push(pointerLine); // add the pointerLine to the list.
				node.outPointer.push(pointerLine.getPointer());// update the outPointer list.

			} else if (lines[i].matches("\\[\\[([^\\|]*)\\]\\]")) { // check for pointers without text we might want to
																	// make this syntax for going straight to another
																	// node

				// convert the string of type [[text]] to type [[text|text]]. so making the
				// message the same as the text
				String s = lines[i].substring(0, lines[i].length() - 2);
				s += "|";
				s += s.substring(2, s.length() - 1);
				s += "]]";

				PointerLine pointerLine = new PointerLine(s, node);// convert input to PointerLine.
				node.lines.push(pointerLine); // add the pointerLine to the list.
				node.outPointer.push(pointerLine.getPointer());// update the outPointer list.
			} else if (lines[i].matches("<<.*>>")) { // if the line is in the form <<text>>
				String arguments[] = lines[i].substring(2, lines[i].length() - 2).split("\\|");// split the command into
				// arguments
				if (arguments.length == 0) { // check if there are arguments.
					System.err.println("Error " + lines[i] + " needs more arguments!");

				} else {
					ConversationLine parsedLine = null;
					for (ConversationLineInterface linetype : linetypes) { // try to parse the arguments into one of the
																			// loaded conversationlines
						if (Objects.equals(arguments[0].toLowerCase(), linetype.getYarnCommand())) {
							parsedLine = linetype.tryParseArguments(arguments, node);
							if (parsedLine != null) {
								node.lines.push(parsedLine); // line successfully parsed
								break;
							}
						}
					}
					if (parsedLine == null) { // line did not match any of the loaded classes
						System.err.println("Error " + lines[i] + " is invalid syntax!");
					}
				}

			} else { // if it is not in any special syntax treat it as text
				node.lines.push(new StringLine(lines[i], node)); // convert the input to a string line.
			}
		}

		// once done parsing
		if (node.outPointer.isEmpty()) { // if this node is a dead end add an end line.
			node.lines.addLast(new EndLine(node));
		}
	}

}
