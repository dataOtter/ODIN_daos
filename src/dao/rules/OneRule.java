package dao.rules;

import dao.Constants;
import maps.*;

/**
 * Contains all information found in one row of the rules.csv
 * @author Maisha Jauernig
 */
public class OneRule {
    private final Integer _ruleId;
    private final Integer _questionId;
    private final String _ruleType;
    private final Integer _dist;
    private final Integer _minTime;
    private final Double _lat;
    private final Double _lon;
    private final int _formatVersion;
    
    /**
     * @param formatVersion
     * @param ruleRow
     */
    OneRule(int formatVersion, String ruleRow){
    	_formatVersion = formatVersion;
    	String[] line = ruleRow.split(",");
        _ruleId = Integer.parseInt(line[Constants.RULES_RULEID_IDX]);
        _questionId = Integer.parseInt(line[Constants.RULES_QUESTIONID_IDX]);
        _ruleType = line[Constants.RULES_RULETYPE_IDX];
        
        if (_ruleType.contains(Constants.RULE_WHILEAT_NAME)){
            if (_formatVersion<=0) {
                _dist = Integer.parseInt(line[3].split(":")[1].replace("\"", ""));
                _lat = Double.parseDouble(line[5].split(":")[1].replace("\"", ""));
                _lon = Double.parseDouble(line[6].split(":")[1].replace("\"", "").replace("}", ""));
                _minTime = Integer.parseInt(line[4].split(":")[1].replace("\"", ""));
            }
            else if (_formatVersion==1) {
                _dist = Integer.parseInt(line[3].split(":")[1].replace("\"", ""));
                _lat = Double.parseDouble(line[4].split(":")[1].replace("\"", ""));
                _lon = Double.parseDouble(line[5].split(":")[1].replace("\"", ""));
                _minTime = Integer.parseInt(line[6].split(":")[1].replace("\"", "").replace("}", ""));
            }
            else { // if (_formatVersion==2) 
            	int offset = ruleRow.indexOf(Constants.RULES_RULEPARAMTOVALUE);
            	String tail = ruleRow.substring(offset);
            	int start = tail.indexOf("{") + 1;
            	int finish = tail.indexOf("}");
            	String mapString = tail.substring(start,  finish);
            	String cleaned = mapString.replaceAll("\"",  "").replaceAll("\\\\",  "");
            	
            	IMJ_Map<String, String> RuleParamNameToVal = new MJ_Map_Factory<String, String>().create();
        		String[] words = cleaned.split(",");
        		for (String w:words) {
        			String[] kv = w.split(":");
        			RuleParamNameToVal.put(kv[0],  kv[1]);
        		}
        		//RuleParamNameToVal.print();
                _dist = Integer.parseInt(RuleParamNameToVal.get(Constants.RULES_DISTANCE));
                _lat = Double.parseDouble(RuleParamNameToVal.get(Constants.RULES_LATITUDE));
                _lon = Double.parseDouble(RuleParamNameToVal.get(Constants.RULES_LONGITUDE));
                _minTime = Integer.parseInt(RuleParamNameToVal.get(Constants.RULES_MINTLASTFIRE));
            }
        }
        
        else{
            _dist = null;
            _lat = null;
            _lon = null;
            _minTime = null;
        }
    }
    
    /**
     * @return  the rule type of this rule as a String
     */
    public String getRuleType(){
        return _ruleType;
    }
    
    /**
     * @return the rule ID of this rule as an int
     */
    public int getRuleId(){
        return _ruleId;
    }
    
    /**
     * @return the distance set in this rule as an int
     */
    public int getDistance(){
        return _dist;
    }
    
    /**
     * @return the minimum time since last fire set in this rule as an int
     */
    public int getMinTimeSinceLastFire(){
        return _minTime;
    }
    
    /**
     * @return the latitude set in this rule as a double
     */
    public double getLat(){
        return _lat;
    }
    
    /**
     * @return the longitude set in this rule as a double
     */
    public double getLon(){
        return _lon;
    }
}