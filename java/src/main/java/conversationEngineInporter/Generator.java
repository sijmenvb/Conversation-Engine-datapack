package conversationEngineInporter;

import org.json.simple.JSONArray;

/**this class takes the input file and converts/parses it into a CEStory
 *  
 * @author Sijmen_v_b
 *
 */
public class Generator {
	
	public CEStory generateStory(JSONArray nodesArray) {
		System.out.println(nodesArray.get(0));
		
		return new CEStory();
	}

}
