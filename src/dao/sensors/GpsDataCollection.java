package dao.sensors;

import maps.*;

/**
 * A map from coupon ID to OneCouponsGpsData
 * @author Maisha Jauernig
 */
final class GpsDataCollection {
    IMJ_Map<Integer, OneCouponsGpsData> _couponToData;
    
    GpsDataCollection() {
        _couponToData = new MJ_Map_Factory<Integer, OneCouponsGpsData>().create();
    }

    void addCouponAndItsData(int couponId, OneCouponsGpsData data){
        _couponToData.put(couponId, data);
    }
    
    /**
     * @param couponId - ID of the coupon for which to get all GPS data as an int
     * @return the GPS data associated with the given coupon as an OneCouponsGpsData
     */
    public OneCouponsGpsData getCouponData(int couponId){
        return _couponToData.get(couponId);
    }
    
    /**
     * @param couponId - ID of a coupon for which to check if it exists in this GpsDataCollection
     * @return boolean whether or not the given coupon exists in this GpsDataCollection
     */
    public boolean hasCouponEntry(int couponId){
        return _couponToData.containsKey(couponId);
    }
    
    /**
     * @return the number of coupon to data entries in this GpsDataCollection
     */
    public int length(){
        return _couponToData.size();
    }
}
