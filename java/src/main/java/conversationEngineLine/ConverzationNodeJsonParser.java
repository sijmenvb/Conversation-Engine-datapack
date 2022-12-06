package conversationEngineLine;

import java.util.LinkedList;

import org.json.simple.JSONObject;

import conversationEngineImporter.ConverzationNode;
import conversationEngineImporter.Functions;

public abstract class ConverzationNodeJsonParser extends ConverzationNode {

	public ConverzationNodeJsonParser(JSONObject in, int id) {
		super(in, id);
	}

	public static void parseBody(String body) {
				
		String lines[] = body.split("(\\r?\\n)|((?=\\[\\[)|(?<=\\]\\]))|((?=<<)|(?<=>>))"); // splits before [[ and << ,
		// after ]] and >> and it
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
					switch (arguments[0].toLowerCase()) { // get the first argument
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
					case "playsound":
						if (arguments.length == 2) {
							this.lines.push(new PlaySound(arguments[1], this));
						} else {
							System.err.println("Error " + lines[i]
									+ " is invalid. example: <<command|some custom command>> (use @s to select the player talkign with the npc)");
						}
						break;
					case "if":
						if (arguments.length == 4) {
							if (arguments[1].toLowerCase().equals("score")) {
								String target = arguments[3];
								if (!isValidRange(target)) {
									target = "1";
									System.err.println("Error " + lines[i]
											+ " 4th argument should be a range. example: <<if|score|name of score|..5>>");
								}
								this.lines.push(new IfScoreLine(arguments[2], target, this));
							} else {
								System.err.println("Error " + lines[i]
										+ " is invalid. example: <<if|score|name of score|target score>> ");
							}
						} else if (arguments.length == 3) {
							if (arguments[1].toLowerCase().equals("tag")) {
								this.lines.push(new IfTagLine(arguments[2], this));
							} else if (arguments[1].toLowerCase().equals("custom")) {
								this.lines.push(new IfCustomLine(arguments[2], this));
							} else {
								System.err
										.println("Error " + lines[i] + " is invalid. example: <<if|tag|name of tag>>");
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
					case "tag":
						if (arguments.length == 3) {
							boolean b = false;
//if the second argument is remove make it a remove tag otherwise assume add.
							if (arguments[1].toLowerCase().equals("remove")) {
								b = true;
							}
							this.lines.push(new TagLine(b, arguments[2], this));
						} else {
							System.err
									.println("Error " + lines[i] + " is invalid. example: <<tag|add|name of the tag>>");
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

}
