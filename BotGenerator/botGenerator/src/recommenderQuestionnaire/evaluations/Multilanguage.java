package recommenderQuestionnaire.evaluations;

import java.util.List;

import org.json.JSONObject;

import generator.Bot;

public class Multilanguage extends Evaluator implements YesNoAnswer{

	public Multilanguage(JSONObject object) {
		super(object, "multilanguage");
	}
	
	@Override
	protected boolean getMultiresponse() {
		return false;
	}

	@Override
	public List<String> evaluate(Bot bot) {
		if (bot.getLanguages().size()>1) {
			return getYes();
		}else {
			return getNo();
		}
	}
	
}