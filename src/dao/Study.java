package dao;

/**
 * Contains all information found in the study.csv
 * @author Maisha Jauernig
 */
public class Study {
    private Integer _id;
//    private Integer _name;
//    private String _description;
//    private Integer _leader;
//    private String _dbName;
//    private String _state;
//    private String _createdDate;
//    private String _startDate;
//    private String _completedDate;
//    private String _nameAlias;
//    private String _topicarn;
//    private Integer _rulequest;
//    private Integer _uploadHeartbeatInterval;
//    private Integer _uploadInterval;
    private final String[] _allData;
    
    /**
     * @param data - the data of the study as a String[]
     */
    Study(String[] data){
        _allData = data;
    }
    
    /**
     * @return the ID of this study as an int
     */
    public Integer getStudyId(){
        if (_id == null){
            _id = Integer.parseInt(_allData[Constants.STUDY_STUDYID_IDX]);
        }
        return _id;
    }
}