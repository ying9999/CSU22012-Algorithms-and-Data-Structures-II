import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.DijkstraSP;

public class BusManagement {

	public static ArrayList<Stops> stops = new ArrayList<Stops>();
	public static ArrayList<Integer> array=new ArrayList<Integer>(1772371);
	public static ArrayList<Integer> array2=new ArrayList<Integer>(1772379);
	public static ArrayList<DirectedEdge> DirectedEdge = new ArrayList<DirectedEdge>();
	static EdgeWeightedDigraph graph = new EdgeWeightedDigraph(1772371);
	
	public static void main(String[] args) {
	//	stop();
		Transfer();
		stopTime();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Shortest path between start stop and end stop");
		System.out.println("start stop: ");
		String start = scanner.nextLine();
		System.out.println("end stop: ");
		String end = scanner.nextLine();
		int startStop = Integer.parseInt(start);
		int endStop = Integer.parseInt(end);
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
	
/*	public static void stop() {
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
	*/
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
	
}
