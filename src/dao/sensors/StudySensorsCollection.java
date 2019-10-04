package dao.sensors;

import orderedcollection.*;

/**
 * A collection of StudySensor
 * @author Maisha Jauernig
 */
public class StudySensorsCollection {
    private final IMJ_OC<StudySensor> _sensors;
    
    /**
     * @param sensors
     */
    StudySensorsCollection(IMJ_OC<StudySensor> sensors) {
    	_sensors = sensors;
    }
    
    /**
     * @return an IMJ_OC<Integer> containing all sensor IDs
     */
    public IMJ_OC<Integer> getSensorIds() {
    	IMJ_OC<Integer> sids = new MJ_OC_Factory<Integer>().create();
    	for (int i = 0; i<_sensors.size(); i++) {
    		sids.add(_sensors.get(i).getSensorId());
    	}
    	return sids;
    }
    
    /**
     * @param studyid - ID of the study for which to get all enabled sensors
     * @return a StudySensorsCollection containing all sensors enabled in the given study
     */
    public StudySensorsCollection getSensorsByStudyId(int studyid) {
    	IMJ_OC<StudySensor> sen = new MJ_OC_Factory<StudySensor>().create();
    	for (StudySensor s: _sensors) {
    		if (s.getStudyId() == studyid) {
    			sen.add(s);
    		}
    	}
    	return new StudySensorsCollection(sen);
    }
    
    /**
     * @param sensorid - ID of the sensor for which to get the sensor interval
     * @return the sensor interval for the given sensor as a Double
     */
    public Double getSensorInterval(int sensorid){
    	for (StudySensor sen: _sensors) {
    		int id = sen.getSensorId();
    		if (id == sensorid) {
    			return sen.getParams().getInterval();
    		}
    	}
    	return null;
    }
	
	/**
	 * @return the number of sensors in this StudySensorsCollection
	 */
	public int getNumSensors() {
		return _sensors.size();
	}
	
	public StudySensor getStudySensorAtIdx(int i) {
		return _sensors.get(i);
	}
}
