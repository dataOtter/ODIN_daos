package dao.sensors;

import dao.Constants;

/**
 *
 * @author Maisha Jauernig
 */
class ProximityBTSensorParameters extends AbsSensorParameters {
	private final double _interval;
	
	ProximityBTSensorParameters(String line) {
		super (line);
		_interval = Double.parseDouble(_paramNameToVal.get(Constants.STUDYTOSENSOR_BT_INTERVAL_LABEL));
	}
	
	@Override
	public double getInterval() {
		return _interval;
	}
}
