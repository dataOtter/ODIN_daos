package dao.sensors;

import orderedcollection.*;

/**
 * Contains all information associated with the given coupon ID found in the sensor_GPS.csv file as an IMJ_OC<GpsDataPoint>
 * @author Maisha Jauernig
 */
class OneCouponsGpsData {
    private final IMJ_OC<GpsDataPoint> _data;
    private final int _couponId;
    
    /**
     * @param cid
     */
    OneCouponsGpsData(int cid){
        _couponId = cid;
        _data = new MJ_OC_Factory<GpsDataPoint>().create();
    }
    
    private OneCouponsGpsData(int cid, IMJ_OC<GpsDataPoint> data){
        _couponId = cid;
        _data = data;
    }
    
    /**
     * @return a deep copy of this OneCouponsGpsData
     */
    public OneCouponsGpsData getDeepCopy(){
    	return new OneCouponsGpsData(_couponId, _data.getDeepCopy());
    }
    
    void addGpsDataPoint(GpsDataPoint gdp){
        _data.add(gdp);
    }
    
    /**
     * @param i - index as an int at which to get the GpsDataPoint 
     * @return the GpsDataPoint found at the given index
     */
    public GpsDataPoint getDataAtIdx(int i){
        return _data.get(i);
    }
    
    /**
     * @return the ID of the coupon associated with this OneCouponsGpsData as an int
     */
    public int getCouponId(){
        return _couponId;
    }
    
    /**
     * @return the number of GpsDataPoints in this OneCouponsGpsData
     */
    public int length(){
        return _data.size();
    }
    
    /**
     * @param idx - the index as an int of the GpsDataPoint to delete
     */
    public void deleteItem(int idx){
        _data.remove(idx);
    }
}
