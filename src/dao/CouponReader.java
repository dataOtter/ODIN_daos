package dao;

import java.util.Scanner;

import dao.Constants;
import maps.IMJ_Map;
import maps.MJ_Map_Factory;
import orderedcollection.*;

/**
 * Reads all data from the coupon.csv file
 * @author Maisha Jauernig
 */
public class CouponReader {
	private final String _path;
	private final int _formatVersion;
	
	/**
	 * @param path
	 * @param formatVersion
	 */
	public CouponReader(String path, int formatVersion) {
		_path = path;
		_formatVersion = formatVersion;
	}
	
	/**
	 * @return IMJ_Map<Integer, String> of coupon IDs to coupon numbers that belong to participants whose consentstatus is "agreed"
	 */
	public IMJ_Map<Integer, String> getActiveCouponIdsToNumbers() {
		int numCols;
		if (_formatVersion == 2) {
			numCols = Constants.COUPON_NUM_COLS_V2;
		}
		else {
			numCols = Constants.COUPON_NUM_COLS;
		}
        Scanner sc = new ScannerHelper(_path, Constants.COUPON_CSV, numCols).getScanner();
        
        IMJ_Map<Integer, String> cidToNo = new MJ_Map_Factory<Integer, String>().create();
        
        while (sc.hasNextLine()){
        	String[] line = sc.nextLine().split(",");
            int cid = Integer.parseInt(line[Constants.COUPON_COUPONID_IDX]);
            String cno = line[Constants.COUPON_COUPONNUMBER_IDX];
            String consentStatus = line[Constants.COUPON_CONSENTSTATUS_IDX];
            
            if (Constants.COUPON_CONSENTSTATUS_CONSENTAGREED.equals(consentStatus)){
            	cidToNo.put(cid, cno);
            }
        }
        sc.close();
        return cidToNo;
	}
	
	/**
	 * @return an IMJ_OC<Integer> of coupon IDs that belong to participants whose consentstatus is "agreed"
	 */
	public IMJ_OC<Integer> getActiveCouponIds() {
		int numCols;
		if (_formatVersion == 2) {
			numCols = Constants.COUPON_NUM_COLS_V2;
		}
		else {
			numCols = Constants.COUPON_NUM_COLS;
		}
        Scanner sc = new ScannerHelper(_path, Constants.COUPON_CSV, numCols).getScanner();
        
        IMJ_OC<Integer> cids = new MJ_OC_Factory<Integer>().create();

        while (sc.hasNextLine()){
        	String[] line = sc.nextLine().split(",");
            int cid = Integer.parseInt(line[Constants.COUPON_COUPONID_IDX]);
            String consentStatus = line[Constants.COUPON_CONSENTSTATUS_IDX];
            
            if (Constants.COUPON_CONSENTSTATUS_CONSENTAGREED.equals(consentStatus)){
                cids.add(cid);
            }
        }
        sc.close();
        return cids;
    }
}
