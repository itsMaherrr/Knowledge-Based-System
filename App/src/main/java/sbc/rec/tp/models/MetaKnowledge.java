package sbc.rec.tp.models;

import java.util.HashMap;
import java.util.function.BiFunction;

public class MetaKnowledge {
	
	public static HashMap<String, BiFunction<Integer, Integer, Boolean>> MetaRules = new HashMap<String, BiFunction<Integer, Integer, Boolean>>() {{
		put("superieur", (x, y) -> x > y);
	}};
}
