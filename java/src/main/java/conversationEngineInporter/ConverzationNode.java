package conversationEngineInporter;

import java.util.HashMap;
import java.util.LinkedList;

import org.json.simple.JSONObject;

import conversationEngineLine.BuyLine;
import conversationEngineLine.CommandLine;
import conversationEngineLine.ConversationLine;
import conversationEngineLine.ElseLine;
import conversationEngineLine.EndIfLine;
import conversationEngineLine.EndLine;
import conversationEngineLine.GiveLine;
import conversationEngineLine.IfScoreLine;
import conversationEngineLine.IfTagLine;
import conversationEngineLine.PointerLine;
import conversationEngineLine.StringLine;

/**
 * this class will hold all the individual nodes of the conversation with
 * references to the nodes connected to this one.
 * 
 * @author Sijmen_v_b
 *
 */
public class ConverzationNode {
	private LinkedList<ConversationLine> lines = new LinkedList<ConversationLine>();

	private int id;
	private String name;
	private LinkedList<String> outPointer = new LinkedList<String>(); // a list of names of nodes this node points to.
	private LinkedList<String> inPointer = new LinkedList<String>(); // a list of names of nodes that point to this
																		// node.
	private String profession = "none";

	public ConverzationNode(JSONObject in, int id) {
		this.id = id;
		this.name = ((String) in.get("title"));// make sure there are no spaces in the name.
		String body = (String) in.get("body");
		body = Functions.recReplace(body, "\n\n", "\n \n"); // replace to sequential nextlines to have a space until no
															// sequential nextlines are left.
		String lines[] = body.split("(\\r?\\n)|((?=\\[\\[)|(?<=\\]\\]))|((?=<<)|(?<=>>))"); // splits before [[ and << ,
																							// after ]] and << and it
																							// splits and removes next
																							// lines next-lines

		for (int i = lines.length - 1; i >= 0; i--) { // go over array backwards (since we want the linkedlist to be in
														// order)
			if (lines[i] == "" && i > 0 && lines[i - 1] != "") { // if this line is empty and the next line is not also
																	// empty ignore it.
				// do not display empty lines
			} else if (lines[i].matches("\\[\\[([^\\|]*)\\|([^\\|]*)\\]\\]")) { // if the line is in the format of
																				// [[ some text | some text ]]
				PointerLine pointerLine = new PointerLine(lines[i], this);// convert input to PointerLine.
				this.lines.push(pointerLine); // add the pointerLine to the list.
				outPointer.push(pointerLine.getPointer());// update the outPointer list.

			} else if (lines[i].matches("\\[\\[([^\\|]*)\\]\\]")) { // check for pointers without text
				// we might want to make this syntax for going straight to another node

				// convert the string of type [[text]] to type [[text|text]]. so making the
				// message the same as the text
				String s = lines[i].substring(0, lines[i].length() - 2);
				s += "|";
				s += s.substring(2, s.length() - 1);
				s += "]]";

				PointerLine pointerLine = new PointerLine(s, this);// convert input to PointerLine.
				this.lines.push(pointerLine); // add the pointerLine to the list.
				outPointer.push(pointerLine.getPointer());// update the outPointer list.
			} else if (lines[i].matches("<<.*>>")) { // if the line is in the form <<text>>
				String arguments[] = lines[i].substring(2, lines[i].length() - 2).split("\\|");// split the command into
																								// arguments
				if (arguments.length == 0) { // check if there are arguments.
					System.err.println("Error " + lines[i] + " needs more arguments!");

				} else {
					switch (arguments[0].toLowerCase()) {
					case "profession":
						if (arguments.length == 2) {
							String[] villager = { "none", "armorer", "butcher", "cartographer", "cleric", "farmer",
									"fisherman", "fletcher", "leatherworker", "librarian", "mason", "nitwit",
									"shepherd", "toolsmith", "weaponsmith" };

							profession = Functions.closestString(villager, arguments[1]);
						} else {
							System.err.println("Error " + lines[i] + " is invalid. example: <<profession|farmer>>");
						}

						break;
					case "give":
						String item;
						int ammount;
						if (arguments.length == 2) {
							item = arguments[1];
							ammount = 1;
							this.lines.push(new GiveLine(item, ammount, this));
						} else if (arguments.length == 3) {
							item = arguments[1];
							try {
								ammount = Integer.parseInt(arguments[2]);
							} catch (NumberFormatException e) {
								ammount = 1;
								System.err.println("Error " + lines[i]
										+ " 3rd argument should be a number. example: <<cooked_beef|4>>");
							}
							this.lines.push(new GiveLine(item, ammount, this));
						} else {
							System.err.println("Error " + lines[i] + " is invalid. example: <<give|cooked_beef|4>>");
						}
						break;
					case "command":
						if (arguments.length == 2) {
							this.lines.push(new CommandLine(arguments[1], this));
						} else {
							System.err.println("Error " + lines[i]
									+ " is invalid. example: <<command|some custom command>> (use @s to select the player talkign with the npc)");
						}
						break;
					case "if":
						if (arguments.length == 4) {
							if (arguments[1].toLowerCase().equals("score")) {
								int target;
								try {
									target = Integer.parseInt(arguments[3]);
								} catch (NumberFormatException e) {
									target = 1;
									System.err.println("Error " + lines[i]
											+ " 4th argument should be a number. example: <<if|score|name of score|1>>");
								}
								this.lines.push(new IfScoreLine(arguments[2], target, this));
							} else {
								System.err.println("Error " + lines[i]
										+ " is invalid. example: <<if|score|name of score|target score>> ");
							}
						} else if (arguments.length == 3) {
							if(arguments[1].toLowerCase().equals("tag")) {
								this.lines.push(new IfTagLine(arguments[2], this));
							} else {
								System.err.println("Error " + lines[i]
										+ " is invalid. example: <<if|tag|name of tag>>");
							}
						} else {
							System.err.println("Error " + lines[i]
									+ " is invalid. example: <<if|score|name of score|target score>> ");
						}

						break;
					case "else":
						this.lines.push(new ElseLine(this));
						break;
					case "endif":
						this.lines.push(new EndIfLine(this));
						break;
					case "buy":
						if (arguments.length == 5) {
							int getAmmount;
							int payAmmount;
							try {
								getAmmount = Integer.parseInt(arguments[2]);
								payAmmount = Integer.parseInt(arguments[4]);
							} catch (NumberFormatException e) {
								getAmmount = 1;
								payAmmount = 1;
								System.err.println("Error " + lines[i]
										+ " 3rd argument should be a number. example: <<buy|carrot|20|diamond|1>>");
							}
							this.lines.push(new BuyLine(arguments[1], getAmmount, arguments[3], payAmmount, this));
						} else {
							System.err.println("Error " + lines[i]
									+ " is invalid. example: <<buy|carrot|20|diamond|1>> (where you but 20 carrots for 1 diamond)");
						}
						break;
					case "end":
						this.lines.push(new EndLine(this));
						break;
					default:
						System.err.println("Error " + lines[i] + " is invalid syntax!");
						break;

					}
				}

			} else { // if it is not in any special syntax treat it as text
				this.lines.push(new StringLine(lines[i], this)); // convert the input to a string line.
			}
		}

		// once done parsing
		if (outPointer.isEmpty()) { // if this node is a dead end add an end line.
			this.lines.addLast(new EndLine(this));
		}
	}

	public LinkedList<Integer> getValidInpointerIds(HashMap<String, ConverzationNode> nodes) {
		LinkedList<Integer> list = new LinkedList<Integer>();

		// for all the valid input pointers get the node id's
		for (String name : inPointer) {
			list.add(nodes.get(name).getId());
		}

		// if this node is the starting node then 0 is also a valid starting point.
		if (isStartingNode()) {
			list.add(0);
		}

		return list;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, NPC npc, Boolean clearchat) {
		String s = "";

		// add the clear chat message.
		if (clearchat) {
			String nexline = "";
			for (int i = 0; i < 20; i++) {
				nexline += "\\n";
			}

			s += String.format(
					"    execute if score @s CE_suc matches 1 run tellraw @s [{\"text\":\"%s\",\"color\":\"white\"}]\n",
					nexline);
		}

		LinkedList<String> condition = new LinkedList<String>();
		condition.addLast("    execute if score @s CE_suc matches 1 ");
		for (int i = 0; i < lines.size(); i++) {
			String con = "";

			// if the line is a <<else>> do not apply the last conditional

			for (int j = 0; j < condition.size(); j++) {

				con += condition.get(j);
			}
			s += lines.get(i).toCommand(nodes, ceStory, npc, condition, con);

		}

		return s;
	}

	public void addInPointer(String name) {
		inPointer.push(name);// update the inPointer list.
	}

	public LinkedList<String> getOutPointer() {
		return outPointer;
	}

	/**
	 * see's if this node has no other nodes pointing to it
	 * 
	 * @return true if this is a starting node
	 */
	public boolean isStartingNode() {
		return inPointer.isEmpty();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name.toLowerCase().replace(" ", "_");
	}

	public String getRealName() {
		return name;
	}

	public String getProfession() {
		return profession;
	}

}
