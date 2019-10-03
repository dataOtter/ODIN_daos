package dao.sensors;

import java.util.Calendar;

/**
 * Contains the information for one GPS data point
 * @author Maisha Jauernig
 */
class GpsDataPoint {
    private final Calendar _dateTime;
    private final GpsCoordinate _coord;
    
    GpsDataPoint(Calendar dateTime, double lat, double lon) {
        _dateTime = dateTime;
        _coord = new GpsCoordinate(lat, lon);
    }
    
    /**
     * @return the GPS coordinates of this GpsDataPoint as a GpsCoordinate
     */
    public GpsCoordinate getGpsCoord() {
    	return _coord;
    }
    
    /**
     * @return the latitude of this GpsDataPoint as a Double
     */
    public Double getLat(){
        return _coord.getLat();
    } 
    
    /**
     * @return the longitude of this GpsDataPoint as a Double
     */
    public Double getLon(){
        return _coord.getLon();
    } 
    
    /**
     * @return the date and time associated with the recording of this GpsDataPoint as a Calendar object
     */
    public Calendar getDateTime(){
        return _dateTime;
    }
    
}
