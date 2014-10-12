import java.io.*;
import java.util.*;


/**
 * The VirusInfection class solves the problem of whether a certain computer in the network
 * will be infected at certain time, by given the original virus inserting time and computer 
 * with a list of communication trace data.
 *
 * This problem is solved by constructing an edge graph based on the trace data, and perform
 * Breadth-First-Search on the graph to get the minimum infection time on the denoted computer.
 * So as to know whether this computer will be infected by the given time or not.
 *
 * The major classes and methods in this implemetation are the class of node Objects, 
 * The static method Search(), which performs BFS on a given graph. The construction of the adjacency
 * list matrix is done in the main after reading inputs.
 *
 * @ author: Dora Shao
 * @ time: 10/10/2014
 * @ version1.1
 */
public class VirusInfection{


/**
 * The static class node defines the node object. Each node object contains two fields, name and time. 
 * name is the index of the computer, and time is the communication time of this computer in a
 * communication.
 * 
 * node class also have getName(), getTime(), and printNode() function.
 * getName() function returns the Name field of a node object.
 * getTime() function returns the Time field of a node object.
 * printNode() function prints the node object as a ordered pair in parenthesis, (name, time).
 */
	static class node{
		int name;
		int time;
		node(){
			name=0;
			time=0;}
		node(int a, int b){
			name=a;
			time=b;}
		public int getName(){return name;}
		public int getTime(){return time;}
		public void printNode(){System.out.print(" (" + name +", " + time + "), ");}
	}
/**
 * The function search() performs breadth first search on a given graph, and returns true if 
 * if the certain valus is been found through the search; and false if not.
 * @ param LinkedList<node>[] graph: is a graph represented by adjacency list matrix, of which the bfs 
 *   search is performed on;
 * @ param int n: the total number of elements in the graph, in this question particularly, n is the number of 
 *    computers in the network.
 * @ param int start_time: the time the virus first been inserted into the computer
 * @ param int start_node: the index of the computer of which the virus was first been inserted
 * @ param int end_time: the query time of whether a computer is infected
 * @ param int end_node: the query computer of whether a it is infected at the a certain time.
 * @ return; the search() function returns true if at the ending time the questioned computer is infected
 *     return false if at the ending time the questioned computer is not infected.
 */
	static Boolean search(LinkedList<node>[] graph, int n, int start_time, 
										int start_node, int end_time, int end_node){
    	Queue <node> q = new LinkedList();
    	boolean[] infected= new boolean[n+1];
    	for (int i=0; i<=n; n++){infected[i]=false;}//initialize infected[]
    	infected[start_node]= true;
		int layer=start_node;
		int ind=0;
		node root = graph[layer].get(ind);	
		while(root.getTime() < start_time){//find the start point to perform the search
			ind++;
			root= graph[layer].get(ind);
		}
		q.add(root);

   		while(!q.isEmpty()){
   			node elem = q.remove();
  			for (node cur: graph[elem.getName()] ){
  				if (cur.getTime()>= start_time && cur.getTime()<= end_time){// only search the nodes based on the time range
  					if ( infected[cur.getName()]==false){
  						infected[cur.getName()]=true;//set the node as been infected if reached by bfs search
  						q.add(cur);
  						if (infected[end_node]==true){// if the end_node is infected, end of search
  							return true;
  						}
  					}
  				}
  			}
   		} return false;
	}

/**
 * The main class reads input data from file "data. in" and put the trace data into a 2d Array
 * From the 2d Array, we build the graph represented by adacency list matrix, using an array of linkedLists
 * Then call the search() function to perform the seach on the constructed graph.
 * and output the search results.
 */
	public static void main (String [ ] args)throws FileNotFoundException{
// read input from file
		Scanner sc = new Scanner(new File("test"));
		int num_nodes = sc.nextInt();
		int num_tuples = sc.nextInt(); 
    	int[][] trace = new int[num_tuples][3];
    	String[] read = new String[3];

    	read = sc.nextLine().split(" ");	
		for (int i=0; i<num_tuples; i++){ 
	  		read = sc.nextLine().split(" ");
			for (int j=0; j<3; j++){//store input data in a 2d array temporarily
	    		trace[i][j] = Integer.valueOf(read[j]);
			}
		}
		int query_start_node=sc.nextInt();
		int query_start_time=sc.nextInt();
		int query_end_node=sc.nextInt();
		int query_end_time=sc.nextInt();

/// build the graph
		int c1 = 0;
		int c2 = 0;

		LinkedList<node>[] graph = new LinkedList[num_nodes+1];
		for (int i=0; i<=num_nodes; i++){
			if (graph[i] == null)
				graph[i] = new LinkedList<node>();
		}

		int[] check = new int[num_nodes];
		for (int i=0; i<num_tuples; i++){// puting a edge, a pair of nodes separately into the adjacency list matrix
			node v1 = new node(trace[i][0], trace[i][2]);
			node v2 = new node(trace[i][1], trace[i][2]);
			c1 = v1.getName();
			c2 = v2.getName();
			if (graph[c1].size()==0){graph[c1].addFirst(v1);}
			if (graph[c2].size()==0){graph[c2].addFirst(v2);}
			graph[c1].addLast(v2);
			graph[c2].add(v1);
		}
// this section of codes prints out the constructed adjacency list matrix
		/*for(int k=0; k<=num_nodes; k++){ 
			System.out.println();
			for (int l=0; l<graph[k].size(); l++){
				graph[k].get(l).printNode();}
		}*/

// Call the search function to perform the search and printout the search result.
		Boolean infection=search(graph, num_nodes, query_start_time, query_start_node, query_end_time, query_end_node);
		System.out.println();
		if (infection){
			System.out.println("If virus is to inserted at time "+query_start_time+" to computer C"+query_start_node+",");
  			System.out.println("Computer C"+query_end_node+" will be infected at time "+query_end_time+".");}
		else{
			System.out.println("If virus is to inserted at time "+query_start_time+" to computer C"+query_start_node+",");
  			System.out.println("Computer C"+query_end_node+" will not be infected at time "+query_end_time+".");}
	}
}

		

	

	
		
	

		


	

	