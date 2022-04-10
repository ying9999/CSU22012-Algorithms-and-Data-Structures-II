import java.io.IOException;

public class Stops {
    private int stop_id, stop_code;
    private String stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url;
	
	public Stops(String input) {
		String stopInput[] = input.split(",");
		stop_id = Integer.parseInt(stopInput[0]);
		try {
		stop_code = Integer.parseInt(stopInput[1]);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			stop_code = -1;
		}
		stop_name = stopFormat(stopInput[2]);
		stop_desc = stopInput[3];
		stop_lat = stopInput[4];
		stop_lon = stopInput[5];
		zone_id = stopInput[6];
		stop_url = stopInput[7];
	}
	
	private String stopFormat(String StopDes) {
		if (StopDes.charAt(0) == 'F' && StopDes.charAt(1) == 'L' && StopDes.charAt(2) == 'A' && StopDes.charAt(3) == 'G' && StopDes.charAt(4) == 'S' && StopDes.charAt(5) == 'T' && StopDes.charAt(6) == 'O' && StopDes.charAt(7) == 'P') {
			StopDes = StopDes.substring(9) + " " + StopDes.substring(0, 8);
		}
		if (StopDes.charAt(0) == 'W' && StopDes.charAt(1) == 'B') {
			return StopDes.substring(3) + " " + "WB";
		}
		else if (StopDes.charAt(0) == 'N' && StopDes.charAt(1) == 'B') {
			return StopDes.substring(3) + " " + "NB";
		}
		else if (StopDes.charAt(0) == 'S' && StopDes.charAt(1) == 'B') {
			return StopDes.substring(3) + " " + "SB";
		}
		else if (StopDes.charAt(0) == 'E' && StopDes.charAt(1) == 'B') {
			return StopDes.substring(3) + " " + "EB";
		}
		else {
			return StopDes;
		}	
	}
	
	public String getStopID() {
		return stop_id + "";
	}
	public String getStopCode() {
		return stop_code + "";
	}
	public String getStopName() {
		return stop_name;
	}
	public String getStopDesc() {
		return stop_desc;
	}
	public String getStopLat() {
		return stop_lat;
	}
	public String getStopLon() {
		return stop_lon;
	}
	public String getZoneID() {
		return zone_id;
	}
	public String getStopUrl() {
		return stop_url;
	}
	
	public String getLine() {
		String string = null;
		string = stop_id + ", " + stop_code + ", " + stop_name + ", " + stop_desc + ", " + stop_lat + ", " + stop_lon + ", " + zone_id + ", " + stop_url;
		return string;
	}
}
