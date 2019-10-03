package dao.sensors;

import orderedcollection.*;
import dao.Study;
import dao.StudyReader;

/***
 * Reads the csv file names of all sensor csv files that are part of this study
 * @author Maisha Jauernig
 *
 */
public class SensorTblNamesReader {
    private IMJ_OC<String> _sensorTblNames;
    private final String _path;
    private final int _formatVersion;
    
    /**
     * @param path
     * @param formatVersion
     */
    public SensorTblNamesReader(String path, int formatVersion) {
        _path = path;
        _formatVersion = formatVersion;
        _sensorTblNames = null;
    }
    
    /**
     * @return a IMJ_OC<String> containing the csv file names of all sensors used in this study
     */
    public IMJ_OC<String> getSensorTblNames() {
    	if (_sensorTblNames == null) {
    		_sensorTblNames = new MJ_OC_Factory<String>().create();

            Study s = new StudyReader(_path, _formatVersion).getStudy();
            StudySensorsCollection studySensors = new SensorsReader(_path, _formatVersion)
            		.getStudySensorsCollection().getSensorsByStudyId(s.getStudyId());
            
            for (int i = 0 ; i<studySensors.getNumSensors(); i++){
                String tblName = studySensors.getStudySensorAtIdx(i).getSensorType().getSensorTblName();
                _sensorTblNames.add(tblName);
            } 
    	}
    	/*if (Constants.TESTING_GPS_ONLY) {
    		IMJ_OC<String> gps = new MJ_OC_Factory<String>().create();
    		gps.append("sensor_GPS");
    		return gps;
    	}*/
        return _sensorTblNames.getDeepCopy();
    }
}
