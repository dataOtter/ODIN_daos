package dao.sensors;

import dao.Constants;

/**
 * Contains all information found in one row of the sensortypes.csv
 * @author Maisha Jauernig
 */
class SensorType {
    private Integer _id;
    private String _name;
    private String _description;
    private String _schema;
    private String _tblName;
    
    /**
     * @param line
     */
    SensorType(String[] line){
    	_id = Integer.parseInt(line[Constants.SENSORTYPES_SENSORID_IDX]);
    	_name = line[Constants.SENSORTYPES_SENSORNAME_IDX];
    	_schema = line[Constants.SENSORTYPES_SCHEMA_IDX];
    	_description = line[Constants.SENSORTYPES_DESCRIPTION_IDX];
    	
    	String[] a = _schema.split(" ");  // split the SQL on spaces
        String s = a[2];  // to get the part that contains the table name (without "create table ")
        a = s.split("\\(");  // split that part on open parenthesis
        _tblName = a[0];  // to get the table name 
    }
    
    /**
     * @return the ID of this sensor as an Integer
     */
    public Integer getSensorId(){
        return _id;
    }
    
    /**
     * @return the name of this sensor as a String
     */
    public String getSensorName(){
        return _name;
    }
    
    /**
     * @return the sensor table name associated with this sensor as a String
     */
    public String getSensorTblName(){
        return _tblName;
    }
    
    String getSchema(){
        return _schema;
    }
    
    /**
     * @return the description of this sensor as a String
     */
    public String getDescr(){
        return _description;
    }
}
