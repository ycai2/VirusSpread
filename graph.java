import java.io.*;
import java.lang.*;
import java.util.*;
import java.lang.Object;
import java.util.LinkedList;

public class Graph{
  
  protected CompNode first;
  protected CompNode second;

  public LinkedList<CompNode>[] buildGraph(int numComputers, int triples, int[] comp1, int[] comp2, int[] time){
    LinkedList<CompNode>[] comps = (LinkedList<CompNode>[]) new LinkedList[numComputers+1];


    for (int i=1; i <= numComputers; i++){
      comps[i] = new LinkedList<CompNode>();
    }

    for (int i=0; i < triples; i++){
      int c1 = comp1[i];
      int c2 = comp2[i];
      int t = time[i];
      first = new CompNode(c1, t);
      second = new CompNode(c2, t);

      //Add (c1, t) to linkedlist at c1 if c1 is empty
      if (comps[c1] == null){
        System.out.println("!!!!index c1 is null at " + c1);
      }
      else{
        if (comps[c1].isEmpty()){
        comps[c1].add(first);
        }
      }

      //Add (c2, t) to linkedlist at c2 if c2 is empty
      if (comps[c2] == null){
        System.out.println("!!!!index c2 is null at "+c2);
      }
      else{
        if (comps[c2].isEmpty()){
        comps[c2].add(second);
        }
      }

      comps[c1].add(second);
      comps[c2].add(first);

    }

    return comps;
  }

  public LinkedList<Integer>[] buildTimeList(int numComputers, int triples, int[] comp1, int[] comp2, int[] time){
    LinkedList<Integer>[] times = new LinkedList[numComputers+1];
    for (int i=1; i <= numComputers; i++){
      times[i] = new LinkedList<Integer>();

    }

    for (int i=0; i<triples; i++){
      int c1 = comp1[i];
      int c2 = comp2[i];
      int t = time[i];
      if (times[c1].isEmpty()){
        times[c1].add(t);
      }
      else if (t > times[c1].getLast()){
        times[c1].add(t);
      }
      if (times[c2].isEmpty()){
        times[c2].add(t);
      }
      else if (t > times[c2].getLast()){
        times[c2].add(t);
      }
    }

  

    return times;
  }

  public boolean virusCheck(LinkedList<CompNode>[] graph, LinkedList<Integer>[] timeTable, int c1, int t1, int c2, int t2){
    Queue<CompNode> comps = new LinkedList<CompNode>();
    System.out.println("Queue size: "+comps.size());
    int ind = 0;
    
    System.out.println(c1+" is infected at "+t1+". ");
    CompNode source = graph[c1].get(ind);
    ListIterator<Integer> listIterator = timeTable[c1].listIterator();
    int ttemp = 0;
    boolean flag = true;
    while ((listIterator.hasNext()) && (flag)){
      if ((ttemp=listIterator.next()) > t1){
        t1 = ttemp;
        flag = false;
        System.out.println("t1 = "+t1);
      }
    }

    
    while (source.getTime() < t1){
      ind++;
      source = graph[c1].get(ind);
      System.out.println("time: "+source.getTime());
    }
    System.out.println();
    System.out.println("  -starting from node: "+source.toString());
    source.discover();
    comps.add(source);



    while (!comps.isEmpty()){
      //CompNode e = comps.remove();
      int compID = comps.remove().getCompNum();
      for (CompNode current: graph[compID]){
        int tk = current.getTime();
        if (tk >= t1 && tk <= t2){
          if (!current.isDiscovered()){
            current.discover();

            comps.add(current);
            System.out.println("  -node: "+current.toString()+" queued. ");
          }
          else if (current.getCompNum() == c2){
            return true;
          }
        }
      }
      
    }
    return false;
  }

  
  public static void main(String[] args) throws FileNotFoundException{
    int n, m = 0;
    int query_size = 4;
    int[] query = new int[query_size];

    System.out.println("===========Input==========");
    Scanner sc = new Scanner(new File("test"));
    System.out.println(n = sc.nextInt());
    System.out.println(m = sc.nextInt()); 
    int[] comp1 = new int[m];
    int[] comp2 = new int[m];
    int[] time = new int[m];
    String[] triple = new String[3];
    triple = sc.nextLine().split(" ");  
    for (int i=0; i<m; i++){ 
      triple = sc.nextLine().split(" ");
      System.out.println("("+triple[0]+", "+triple[1]+", "+triple[2]+"); ");
      comp1[i] = Integer.valueOf(triple[0]);
      comp2[i] = Integer.valueOf(triple[1]);
      time[i] = Integer.valueOf(triple[2]);
    }
    //System.out.println(Arrays.toString(comp1));
    //System.out.println(Arrays.toString(comp2));
    //System.out.println(Arrays.toString(time));

    for (int i=0; i<query_size; i++){
      query[i] = sc.nextInt();
    }
    System.out.println(query[0]+", "+query[1]);
    System.out.println(query[2]+", "+query[3]);
    
    System.out.println("==========================\n");
    Graph testGraph = new Graph();
    LinkedList<CompNode>[] graph = testGraph.buildGraph(n, m, comp1, comp2, time);
    //LinkedList<CompNode> singleArr = new 
    LinkedList<Integer>[] timeTable = testGraph.buildTimeList(n, m, comp1, comp2, time);
    for (int i = 1; i <= n; i++){
      System.out.println("Nodes"+i+": "+graph[i].toString());
      System.out.println("Times"+i+": "+timeTable[i].toString());
    }
    

    if (graph != null){
      System.out.println("Graph created with "+(graph.length-1)+" computers"+". \n");
    }
    System.out.println("If c"+query[0]+" is infected at time "+query[1]+",");
    System.out.println("will c"+query[2]+" have been infected at time "+query[3]+"?");
    if (testGraph.virusCheck(graph, timeTable, query[0], query[1], query[2], query[3])){
      System.out.println("True\nc"+query[2]+" will have been infected at time "+query[3]+". ");
    }
    else{
      System.out.println("False\nc"+query[2]+" will NOT have been infected at time "+query[3]+". ");
    }
  }

  

  
  

}
