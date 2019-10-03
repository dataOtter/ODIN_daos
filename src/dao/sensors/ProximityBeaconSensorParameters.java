package dao.sensors;

import dao.Constants;

/**
 *
 * @author Maisha Jauernig
 */
class ProximityBeaconSensorParameters extends AbsSensorParameters {
	private final double _interval;
	
	ProximityBeaconSensorParameters(String line) {
		super (line);
		_interval = Double.parseDouble(_paramNameToVal.get(Constants.STUDYTOSENSOR_BEACON_INTERVAL_LABEL));
	}
	
	@Override
	public double getInterval() {
		return _interval;
	}
}
