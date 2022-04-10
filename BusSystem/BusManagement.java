import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.TST;
import edu.princeton.cs.algs4.Queue;

public class BusManagement {

	public static ArrayList<Stops> stops = new ArrayList<Stops>();
	public static ArrayList<Integer> array=new ArrayList<Integer>(1772371);
	public static ArrayList<Integer> array2=new ArrayList<Integer>(1772379);
	public static ArrayList<String> stopData = new ArrayList<String>();
	public static ArrayList<Trips> trips = new ArrayList<Trips>();
	public static TST tst = new TST();
	public static ArrayList<DirectedEdge> DirectedEdge = new ArrayList<DirectedEdge>();
	static EdgeWeightedDigraph graph = new EdgeWeightedDigraph(1772371);
	
	public static void main(String[] args) {
		stop();
		Transfer();
		stopTime();
		tripArray();
		
		boolean quit = false;
		Scanner scannerValue = new Scanner(System.in);
		while (quit==false) {
		System.out.println("\nWelcome");	
		System.out.println("Please select the corresponding number for searching the bus information.");	
		System.out.println("1-Finding The Shortest Path Between Two Stops");
		System.out.println("2-Searching For The Bus Stop");	
		System.out.println("3-Searching the trips by arrival time");
		System.out.println("4-Exit The Bus Management System");	
		System.out.println("Type the corresponding number: ");	
		if (scannerValue.hasNextInt()) {	
			int number = scannerValue.nextInt();
			if (number == 4) {
				quit = true;
				System.out.println("The Bus Management System is exiting.");
			}
		else if (number == 1) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Shortest path between start stop and end stop");
		System.out.println("start stop: ");
		String start = scanner.nextLine();
		System.out.println("end stop: ");
		String end = scanner.nextLine();
		int startStop = Integer.parseInt(start);
		int endStop = Integer.parseInt(end);
		if (checkValidStop(startStop)==true && checkValidStop(endStop)==true)
		{
		DijkstraSP sp = new DijkstraSP(graph, startStop);
	            if (sp.hasPathTo(endStop)) {
	                StdOut.printf("%d to %d (%.2f)  ", startStop, endStop, sp.distTo(endStop));
	                for (DirectedEdge e : sp.pathTo(endStop)) {
	                    StdOut.print(e + "   ");
	                }
	                StdOut.println();
	            }
	            else {
	                StdOut.printf("%d to %d         no path\n", startStop, endStop);
	            }
	        }
		
		if (checkValidStop(startStop)==false && checkValidStop(endStop)==false) {
			System.out.println("This is not a valid start stop and end stop.");
		}
		else if (checkValidStop(startStop)==false) {
			System.out.println("This is not a valid start stop.");
		}
		else if (checkValidStop(endStop)==false) {
			System.out.println("This is not a valid end stop.");
		}
		}
		
		else if (number==2) {
			try {
			System.out.println("Enter the stop name you want to search for: ");
			Scanner scannerStop = new Scanner(System.in);
			String search = scannerStop.nextLine();
			for (int i=0; i<stops.size(); i++) {
			tst.put(stops.get(i).getStopName(), stops.get(i).getLine());
			}
			Iterable<String> stopName = tst.keysWithPrefix(search);
			boolean check = true;
			if (search.length()<1) {
				check = false;
				System.out.println("This is not a valid stop search input.");
			}
			if (check == true) {
				for (String key : stopName) {
					System.out.println(tst.get(key));
					}				
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("This is not a valid stop search input.");
		}
		}
			
		else if (number==3) {
			System.out.println("Enter the arrival time you want to search for trips: ");
			Scanner scannerTrip = new Scanner(System.in);
			String tripSearch = scannerTrip.nextLine();
			int count;
			boolean checking = checkValidTime(tripSearch);
			if (checking == true) {
			for (int i=0; i<trips.size(); i++) {
	            String time = trips.get(i).getArrivalTime();
				if (time.equalsIgnoreCase(tripSearch)==true) {
					count = i;
					System.out.println(trips.get(count).tripString());
				}						
			}
		}
			else {
				System.out.println("This is not a valid input. Arrival time should be provided by the user as hh:mm:ss.");
			}
		}						
		}
		}
	}

	
	public static void stop() {
		try {
    		FileReader fr = new FileReader("stops");
    		Scanner scanner = new Scanner(fr);
    		scanner.nextLine();
    		while (scanner.hasNextLine()) {
    			String stopString = scanner.nextLine();
    			stops.add(new Stops(stopString));
    		}
    		scanner.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Transfer() {
		try {
			double weight = 0;
    		FileReader fr = new FileReader("transfers");
    		Scanner scanner = new Scanner(fr);
    		scanner.nextLine();
    		while (scanner.hasNext()) {
    			String[] str = scanner.nextLine().split(",");
    			int number = Integer.parseInt(str[2]);
    			if (number == 0) {
    				weight = 2;
    			}
    			else if(number == 2) {
    				int number2 = Integer.parseInt(str[3]);
    				weight = number2/100;
    			}
    			graph.addEdge(new DirectedEdge(Integer.parseInt(str[0]), Integer.parseInt(str[1]), weight));
    		}
    		scanner.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void stopTime() {
		try {
    		FileReader fr = new FileReader("stop_times");
    		Scanner scanner = new Scanner(fr);
    		scanner.nextLine();
    		while (scanner.hasNext()) {
    			String[] str = scanner.nextLine().split(",");
    			array2.add(Integer.parseInt(str[0]));
    			array.add(Integer.parseInt(str[3]));	
	        }
    		for (int j=0; j<array2.size()-1; j++) {
    			int first = array2.get(j);
    			int next = array2.get(j+1);
    		if (first == next) {
    		graph.addEdge(new DirectedEdge(array.get(j), array.get(j+1), 1));
    		}
    		}
    		scanner.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean checkValidStop(int stopNum) {
		for (int i=0; i < stops.size(); i++) {
			String stopID = stops.get(i).getStopID();
			if (Integer.parseInt(stopID) == stopNum) {
				return true;
			}
		}
		return false;
	}
	
	public static void tripArray() {
		try {
			FileReader fr = new FileReader("stop_times");
			Scanner scanner2 = new Scanner(fr);
			scanner2.nextLine();
			while (scanner2.hasNext()) {
				String tString = scanner2.nextLine();
    			trips.add(new Trips(tString));
			}
			scanner2.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean checkValidTime(String checkTime) {
		if (checkTime.length()<1) {
			System.out.println("This is not a valid input. Wrong time format.");
			return false;
		}
		if (checkTime.length()<8 || checkTime.length()>8) {
			System.out.println("This is not a valid input. Wrong time format.");
			return false;
		}
		if (checkTime.length()==8) {
		        if (Character.isWhitespace(checkTime.charAt(0))) {
		        	checkTime = checkTime.substring(1);	        
		        }
		
		String[] checkTimeArray;		
        checkTimeArray = checkTime.split(":");

        if ((Integer.parseInt(checkTimeArray[0]) < 0 || Integer.parseInt(checkTimeArray[0]) > 23) && (Integer.parseInt(checkTimeArray[1]) < 0 || Integer.parseInt(checkTimeArray[1]) > 59) && (Integer.parseInt(checkTimeArray[2]) < 0 || Integer.parseInt(checkTimeArray[2]) > 59)) {
			System.out.println("This is not a valid time input.");
			return false;
		}
        else {
			if ((Integer.parseInt(checkTimeArray[0])) < 0 || (Integer.parseInt(checkTimeArray[0]) > 23)) {
				System.out.println("The hour part is not valid.");
				return false;
			}
			if ((Integer.parseInt(checkTimeArray[1])) < 0 || (Integer.parseInt(checkTimeArray[1])) > 59) {
				System.out.println("The minute part is not valid.");
				return false;
			}
			if (Integer.parseInt(checkTimeArray[2]) < 0 || Integer.parseInt(checkTimeArray[2]) > 59) {
				System.out.println("The second part is not valid.");
				return false;
			}
            }
		}		
		return true;	
	}
	
}

