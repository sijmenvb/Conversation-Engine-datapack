package conversationEngineInporter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**this class takes the input file and converts/parses it into a CEStory
 *  
 * @author Sijmen_v_b
 *
 */
public class Generator {
	
	public CEStory generateStory(JSONArray nodesArray) {
		System.out.println(nodesArray.get(0));
		ConverzationNode n = new ConverzationNode((JSONObject) nodesArray.get(0), 0);
		System.out.println(n.toCommand());
		
		return new CEStory();
	}

}
