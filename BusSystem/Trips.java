
public class Trips {
	private int trip_id,stop_id;
	private String arrival_time;
    private String departure_time,stop_sequence,stop_headsign,pickup_type,drop_off_type;

	public Trips(String Input) {	
		String tripsInput[] = Input.split(",");
		trip_id = Integer.parseInt(tripsInput[0]);
		arrival_time = tripsInput[1];
		departure_time = tripsInput[2];
		stop_id = Integer.parseInt(tripsInput[3]);
		stop_sequence = tripsInput[4];
		stop_headsign = tripsInput[5];
		pickup_type = tripsInput[6];
		drop_off_type = tripsInput[7];
	}
	
	public String getTripID() {
		return trip_id + "";
	}
	public String getArrivalTime() {
		return arrival_time;
	}
	public String getDepartureTime() {
		return departure_time;
	}
	public String getStopID() {
		return stop_id + "";
	}
	public String getStopSequence() {
		return stop_sequence;
	}
	public String getStopHeadsign() {
		return stop_headsign;
	}
	public String getPickupType() {
		return pickup_type;
	}
	public String getDropOffType() {
		return drop_off_type;
	}
	
	public String tripString() {
		String string = null;
		string = trip_id + "," + arrival_time + "," + departure_time + "," + stop_id + "," + stop_sequence + "," + stop_headsign + "," + pickup_type + "," + drop_off_type;
		return string;
	}

}
