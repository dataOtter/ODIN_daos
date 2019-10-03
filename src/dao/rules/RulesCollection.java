package dao.rules;

import orderedcollection.*;

/**
 * A collection of OneRule
 * @author Maisha Jauernig
 */
public class RulesCollection {
	private IMJ_OC<OneRule> _allRules;
	
	/**
	 * @param rules
	 */
	RulesCollection(IMJ_OC<OneRule> rules) {
		_allRules = rules.getDeepCopy();
	}
	
	/**
	 * @param ruleType - type of the rule, such as WhileAt
	 * @return a RulesCollection subset of this RulesCollection that contains 
	 * only rules of the given type
	 */
	public RulesCollection getRulesCollectionByType(String ruleType){
		IMJ_OC<OneRule> newRules = new MJ_OC_Factory<OneRule>().create();
		for (OneRule r: _allRules) {
			if (r.getRuleType().contains(ruleType)) {
				newRules.add(r);
			}
		}
		RulesCollection c = new RulesCollection(newRules);
		return c;
    }
	
	/**
	 * @return an IMJ_OC<Integer> of all rule IDs found in this RulesCollection
	 */
	public IMJ_OC<Integer> getAllRids() {
		IMJ_OC<Integer> rids = new MJ_OC_Factory<Integer>().create();
		for (OneRule r: _allRules) {
			rids.add(r.getRuleId());
		}
		return rids;
	}
	
	/**
	 * @return the IMJ_OC<OneRule> contained in this RulesCollection
	 */
	public IMJ_OC<OneRule> getRules(){
		return _allRules;
	}
	
	/**
	 * @param rid - the rule ID for which to get the OneRule object
	 * @return the OneRule associated with the given rule ID
	 */
	public OneRule getRuleById(int rid) {
		for (OneRule r: _allRules) {
            if (r.getRuleId() == rid){
                return r;
            }
        }
        return null;
	}
}
