package dao.sensors;

/**
 * Contains a latitude and a longitude
 * @author Maisha Jauernig
 */
class GpsCoordinate {
    private double _lat;
    private double _lon;
    
    GpsCoordinate(double lat, double lon){
    	_lat = lat;
    	_lon = lon;
    }
    
    public double getLat(){
        return _lat;
    }
    
    public double getLon(){
        return _lon;
    }
    
    public void print(){
        System.out.println("Lat: " + _lat + " Lon: " + _lon);
    }
}
