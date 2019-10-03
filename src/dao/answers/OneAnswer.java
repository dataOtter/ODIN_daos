package dao.answers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import dao.Constants;

/**
 * Contains all information found in one row of the answers.csv
 * @author Maisha Jauernig
 */
public class OneAnswer {
	private final Integer _couponId;
	private final Integer _ruleId;
	private final Integer _questionId;
	private final Integer _choiceId;
    private final String _answerText;
    private final Calendar _ruleFiredDateTime;  //(dateTime) 
    private final int _formatVersion;
    
    /**
     * @param formatVersion
     * @param answerRow - one row of the answers.csv as a String[]
     * @throws ParseException
     */
    OneAnswer(int formatVersion, String[] answerRow) throws ParseException{
    	_formatVersion = formatVersion;
    	_couponId = Integer.parseInt(answerRow[Constants.ANSWERS_COUPONID_IDX]);
		_ruleId = Integer.parseInt(answerRow[Constants.ANSWERS_RULEID_IDX]);
		_questionId = Integer.parseInt(answerRow[Constants.ANSWERS_QUESTIONID_IDX]);
		_choiceId = Integer.parseInt(answerRow[Constants.ANSWERS_CHOICEID_IDX]);
		_answerText = answerRow[Constants.ANSWERS_ANSWERTEXT_IDX];
    	_ruleFiredDateTime = getRuleFiredTimeFromLine(answerRow);
    }
    
    /**
     * @return the time the rule associated with this answer fired as a Calendar object
     */
    public Calendar getRuleFiredTime(){
        return _ruleFiredDateTime;
    }
    
    /**
     * @return the text provided as an answer, if any, as a String
     */
    public String getAnswerText(){
        return _answerText;
    }
    
    /**
     * @return the coupon ID associated with this answer as an int
     */
    public int getCouponId(){
        return _couponId;
    }
    
    /**
     * @return the rule ID associated with this answer as an int
     */
    public int getRuleId(){
        return _ruleId;
    }
    
    /**
     * @return the question ID associated with this answer as an int
     */
    public int getQuestionId(){
        return _questionId;
    }
    
    /**
     * @return the choice ID associated with this answer as an int
     */
    public int getChoiceId(){
        return _choiceId;
    }
    
    private Calendar getRuleFiredTimeFromLine(String[] line) throws ParseException{
        Calendar dateTime = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss.S", Locale.US);
        Date d = sdf.parse(line[Constants.ANSWERS_RULEFIREDTIME_IDX]);
        dateTime.setTime(d);
        return dateTime;
    }
}
