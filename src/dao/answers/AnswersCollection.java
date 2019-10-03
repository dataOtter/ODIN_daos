package dao.answers;

import orderedcollection.*;

/**
 * A collection of OneAnswer
 * @author Maisha Jauernig
 */
public class AnswersCollection {
	private IMJ_OC<OneAnswer> _allAnswers;

    /**
     * Creates and AnswersCollection with a deep copy of the given input
     * @param answers - IMJ_OC<OneAnswer>
     */
    AnswersCollection(IMJ_OC<OneAnswer> answers) {
		_allAnswers = answers.getDeepCopy();
	}
    
    /**
     * @return an IMJ_OC<Integer> of all rule IDs found in this AnswersCollection
     */
    public IMJ_OC<Integer> getRids() {
    	IMJ_OC<Integer> rids = new MJ_OC_Factory<Integer>().create();
    	
		for (int i=0; i<_allAnswers.size(); i++) {
			int thisRid = _allAnswers.get(i).getRuleId();
			
			if ( ! rids.contains(thisRid)) {
				rids.add(thisRid);
			}
		}
		return rids;
    }
	
	/**
	 * @param rids - IMJ_OC<Integer> of rule IDs for which to get all answers
	 * @return an AnswersCollection subset of this AnswersCollection that contains 
	 * only answers associated with the given rule IDs
	 */
	public AnswersCollection getAnswersByRids(IMJ_OC<Integer> rids) {
		IMJ_OC<OneAnswer> newAnswers = new MJ_OC_Factory<OneAnswer>().create();
		for (OneAnswer a: _allAnswers) {
			int thisRid = a.getRuleId();
			
			if (rids.contains(thisRid)) {
				newAnswers.add(a);
			}
		}
		return new AnswersCollection(newAnswers);
	}
	
	/**
	 * @param rid - rule ID for which to get all answers
	 * @return an AnswersCollection subset of this AnswersCollection that contains 
	 * only answers associated with the given rule ID
	 */
	public AnswersCollection getAnswersByRids(int rid) {
		IMJ_OC<OneAnswer> newAnswers = new MJ_OC_Factory<OneAnswer>().create();
		for (OneAnswer a: _allAnswers) {
			int thisRid = a.getRuleId();
			
			if (rid == thisRid) {
				newAnswers.add(a);
			}
		}
		return new AnswersCollection(newAnswers);
	}
	
	/**
	 * @param cids - coupon ID for which to get all answers
	 * @return an AnswersCollection subset of this AnswersCollection that contains 
	 * only answers associated with the given coupon IDs
	 */
	public AnswersCollection getAnswersByCids(IMJ_OC<Integer> cids) {
		IMJ_OC<OneAnswer> newAnswers = new MJ_OC_Factory<OneAnswer>().create();
		for (OneAnswer a: _allAnswers) {
			int thisCid = a.getCouponId();
			
			if (cids.contains(thisCid)) {
				newAnswers.add(a);
			}
		}
		return new AnswersCollection(newAnswers);
	}
	
	/**
	 * @return the IMJ_OC<OneAnswer> contained in this AnswersCollection
	 */
	public IMJ_OC<OneAnswer> getAnswers(){
		return _allAnswers;
	}

	/**
	 * @param ans - OneAnswer to be added to this AnswersCollection
	 */
	void addAnswer(OneAnswer ans) {
		_allAnswers.add(ans);
	}
}
