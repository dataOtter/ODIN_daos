package dao;

import java.util.Scanner;

/**
 * Reads all data from the study.csv file into an Study object
 * @author Maisha Jauernig
 */
public class StudyReader {
    private final String _path;
    private final int _formatVersion;
    
    /**
     * @param path
     * @param formatVersion
     */
    public StudyReader(String path, int formatVersion) {
        _path = path;
        _formatVersion = formatVersion;
    }
    
    /**
     * @return all data from the study.csv as a Study
     */
    public Study getStudy(){
    	int numCols;
    	if (_formatVersion == 2) {
    		numCols = Constants.STUDY_NUM_COLS_V2;
    	}
    	else {
    		numCols = Constants.STUDY_NUM_COLS;
    	}
		Scanner sc = new ScannerHelper(_path, Constants.STUDY_CSV, numCols).getScanner();
        String line = sc.nextLine();
        sc.close();
        return new Study(line.split(","));
    }
}
