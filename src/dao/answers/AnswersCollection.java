package dao.answers;

import java.util.Iterator;

import orderedcollection.*;
import dao.rules.RulesCollection;

/**
 * A collection of OneAnswer, extends MJ_OC<OneAnswer>
 * @author Maisha Jauernig
 */
public class AnswersCollection extends MJ_OC<OneAnswer> {
	private IMJ_OC<OneAnswer> _allAnswers;
	private IMJ_OC<Integer> _rids = null;
	private IMJ_OC<Integer> _cids = null;

    /**
     * Creates an AnswersCollection
     */
    public AnswersCollection() {
		_allAnswers = new MJ_OC_Factory<OneAnswer>().create();
	}
    
    /**
     * Creates an AnswersCollection with a deep copy of the given input
     * @param answers - IMJ_OC<OneAnswer>
     */
    private AnswersCollection(IMJ_OC<OneAnswer> answers) {
		_allAnswers = answers.getDeepCopy();
	}
    
    /**
     * Extracts AnsersCollection subset for the given rule ID and coupon ID
     * @param cid - coupon ID for which to extract all answers
     * @param rid - rule ID for which to extract all answers
     * @return an AnswersCollection subset
     */
    public AnswersCollection getAnsForRuleAndCid(int cid, int rid) {
    	return this.getAnswersByRids(rid).getAnswersByCids(cid);
    }
    
    /**
     * @param qid - question ID for which to get the most recent OneAnswer
     * @param timeNow - time in seconds to use for determining "most recent"
     * @return the most recent OneAnswer to the given question ID
     */
    public OneAnswer getMostRecentAnsToQid(int qid, double timeNow) {
    	double t = 0.0;
    	OneAnswer a;
    	OneAnswer toReturn = null;
    	Iterator<OneAnswer> iter = _allAnswers.iterator();
    	
    	while (t <= timeNow && iter.hasNext()) {
    		a = iter.next();
    		t = a.getRuleFiredTime().getTimeInMillis() / 1000.0;
    		if (a.getQuestionId() == qid) {
    			toReturn = a;
    		}
    	}
    	
    	return toReturn;
    }
    
    /**
     * @param cid - coupon ID for which to extract all answers
     * @param rules - RulesCollection of all rules
     * @param ruleType - rule type for which to extract all answers
     * @return an AnswersCollection subset containing only answers for the given coupon ID and rule type
     */
    public AnswersCollection getOneRuleTypesAnswersForCid(int cid, RulesCollection rules, String ruleType) {
    	RulesCollection oneRuleTypeRulesCollection = rules.getRulesCollectionByType(ruleType);
        IMJ_OC<Integer> ruleRids = oneRuleTypeRulesCollection.getAllRids();
    	AnswersCollection ruleAns = this.getAnswersByRids(ruleRids);
    	
    	if (_cids == null) {
    		computeCids();
    	}
  
    	if ( ! _cids.contains(cid) ) {
			return new AnswersCollection();
		}
		else {
			return ruleAns.getAnswersByCids(cid);
		}
    }
    
    /**
     * @return an IMJ_OC<Integer> of all rule IDs in this AnswersCollection
     */
    public IMJ_OC<Integer> getRids() {
    	if (_rids == null) {
	    	_rids = new MJ_OC_Factory<Integer>().create();
	    	
			for (int i=0; i<_allAnswers.size(); i++) {
				int thisRid = _allAnswers.get(i).getRuleId();
				
				if ( ! _rids.contains(thisRid)) {
					_rids.add(thisRid);
				}
			}
    	}
		return _rids;
    }
	
	/**
	 * @param rids - IMJ_OC<Integer> of rule IDs for which to get all answers
	 * @return an AnswersCollection subset that contains only answers associated with the given rule IDs
	 */
	public AnswersCollection getAnswersByRids(IMJ_OC<Integer> rids) {
		AnswersCollection ans = new AnswersCollection();
		
		for (OneAnswer a: _allAnswers) {
    		if ( rids.contains(a.getRuleId()) ) {
    			ans.add(a);
    		}
		}
		return ans;
	}
	
	/**
	 * @param rid - rule ID for which to get all answers
	 * @return an AnswersCollection subset that contains only answers associated with the given rule ID
	 */
	public AnswersCollection getAnswersByRids(int rid) {
		IMJ_OC<Integer> rids = new MJ_OC_Factory<Integer>().create();
		rids.add(rid);
		return getAnswersByRids(rids);
	}
	
	/**
	 * @param cids - IMJ_OC<Integer> of coupon IDs for which to get all answers
	 * @return an AnswersCollection subset that contains only answers associated with the given coupon IDs
	 */
	public AnswersCollection getAnswersByCids(IMJ_OC<Integer> cids) {
		AnswersCollection ans = new AnswersCollection();
		
		for (OneAnswer a: _allAnswers) {
    		if ( cids.contains(a.getCouponId()) ) {
    			ans.add(a);
    		}
		}
		return ans;
	}
	
	/**
	 * @param cids - coupon IDs for which to get all answers
	 * @return an AnswersCollection subset that contains only answers associated with the given coupon ID
	 */
	public AnswersCollection getAnswersByCids(int cid) {
		IMJ_OC<Integer> cids = new MJ_OC_Factory<Integer>().create();
		cids.add(cid);
		return getAnswersByCids(cids);
	}
    
	@Override
	public boolean add(OneAnswer a) {
		return _allAnswers.add(a);
	}
	
	@Override
	public void add(int index, OneAnswer a) {
		_allAnswers.add(index, a);
	}
	
	@Override
	public OneAnswer get(int index) {
		return _allAnswers.get(index);
	}

	@Override
	public OneAnswer remove(int index) {
		return _allAnswers.remove(index);
	}
	
	@Override
	public AnswersCollection getDeepCopy() {
		// the constructor already gets a deep copy of the oc
		return new AnswersCollection(_allAnswers);
	}
	
	@Override
	public int size() {
		return _allAnswers.size();
	}
	
	@Override
	public void prepend(OneAnswer a) {
		_allAnswers.prepend(a);
	}

	@Override
	public void printAll() {
		_allAnswers.printAll();
	}

	@Override
	public void clear() {
		_allAnswers.clear();
	}

	@Override
	public OneAnswer set(int index, OneAnswer a) {
		return _allAnswers.set(index, a);
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return _allAnswers.toArray(a);
	}
	
	private void computeCids() {
		_cids = new MJ_OC_Factory<Integer>().create();
	    	
		for (int i=0; i<_allAnswers.size(); i++) {
			int thisCid = _allAnswers.get(i).getCouponId();
			
			if ( ! _cids.contains(thisCid)) {
				_cids.add(thisCid);
			}
		}
    }
}
