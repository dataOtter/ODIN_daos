package dao.rules;

import dao.Constants;

public class OnButtonRuleParams extends AbsRuleParams {
    private final Integer _id;
    private final String _label;

	OnButtonRuleParams(String ruleRow, int formatVersion) {
		super(ruleRow, formatVersion);
		_id = Integer.parseInt(this.getParamVal(Constants.RULES_BUTTONID));
		_label = this.getParamVal(Constants.RULES_BUTTONLABEL);
	}

	public Integer getId() {
		return _id;
	}

	public String getLabel() {
		return _label;
	}
}
