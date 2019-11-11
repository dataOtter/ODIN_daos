package dao.rules;

import dao.Constants;
import dao.rules.AbsRuleParams;

public class OnArrivalOrDepRuleParams extends AbsRuleParams {
	private final Integer _dist;
    private final Double _lat;
    private final Double _lon;

    OnArrivalOrDepRuleParams(String ruleRow, int formatVersion) {
		super(ruleRow, formatVersion);
        _dist = Integer.parseInt(this.getParamVal(Constants.RULES_DISTANCE));
        _lat = Double.parseDouble(this.getParamVal(Constants.RULES_LATITUDE));
        _lon = Double.parseDouble(this.getParamVal(Constants.RULES_LONGITUDE));
	}

	public Integer getDist() {
		return _dist;
	}

	public Double getLat() {
		return _lat;
	}

	public Double getLon() {
		return _lon;
	}
}
