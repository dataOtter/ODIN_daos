/**
 * 
 */
package dao.filters;

import dao.Constants;
import dao.rules.GpsDataAdapter;
import dao.sensors.GpsCoordinate;

/**
 * @author Maisha Jauernig
 *
 */
public class LocFilterInput extends AbsFilterInput {
	private GpsCoordinate _locNow;
	
	public LocFilterInput(double timeNowInSecs, GpsDataAdapter ad) {
		super(Constants.FILTER_LOCATION, timeNowInSecs);
		_locNow = ad.getLocation(timeNowInSecs).getGpsCoord();
	}

	/**
	 * @return the _locNow
	 */
	public GpsCoordinate getLocNow() {
		return _locNow;
	}
}
