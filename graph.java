import java.io.*;
import java.lang.*;
import java.util.*;
import java.lang.Object;
import java.util.LinkedList;

public class Graph{
  //protected int size = 0;
  
  //protected CompNode[] nodeList;
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
        System.out.println("!!!!index c1 is null \n");
      }
      else{
        if (comps[c1].isEmpty()){
        comps[c1].add(first);
        }
      }

      //Add (c2, t) to linkedlist at c2 if c2 is empty
      if (comps[c2] == null){
        System.out.println("!!!!index c2 is null \n");
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

  public boolean virusCheck(LinkedList<CompNode>[] graph, int c1, int t1, int c2, int t2){
    Queue<CompNode> comps = new LinkedList<CompNode>();
    System.out.println(comps.size());
    
    int computer = c1;
    int ind = 0;
    
    System.out.println(c1+" is infected at "+t1+". ");
    CompNode source = graph[computer].get(ind);
    while (source.getTime() < t1){
      ind++;
      source = graph[computer].get(ind);
      System.out.println("time: "+source.getTime());
    }
    System.out.println("=====starting from node: "+source.toString()+"=======");
    
    return true;
  }

  
  public static void main(String[] args){
    int n = 4;
    int m = 5;
    int[] comp1 = {1, 2, 3, 1, 1};
    int[] comp2 = {2, 4, 4, 3, 4};
    int[] time = {4, 8, 8, 10, 12};
    int[] query = {2, 8, 3, 8};

    Graph testGraph = new Graph();
    LinkedList<CompNode>[] graph = testGraph.buildGraph(n, m, comp1, comp2, time);
    //LinkedList<CompNode> singleArr = new 
    for (int i = 1; i <= n; i++){
      System.out.println(graph[i].toString());
    }

    if (graph != null){
      System.out.println("Graph created with "+graph.length+" nodes"+". \n");
    }
    System.out.println("If computer "+query[0]+" is infected at time "+query[1]+",");
    System.out.println("will compter "+query[2]+" have been infected at time "+query[3]+"?");
    testGraph.virusCheck(graph, query[0], query[1], query[2], query[3]);
  }

  

  
  

}
