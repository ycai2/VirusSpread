import java.util.*;

public class Node{
  protected int compNum;
  protected int t;
  protected boolean discovered;
  protected LinkedList<Node> adjNodes = new LinkedList();

  public Node(int c, int tk){
    compNum = c;
    t = tk;

  }

  public int getCompNum(){
    return compNum;
  }

  public int getTimeStamp(){
    return t;
  }

  public boolean isDiscovered(){
    return discovered;
  }

  public 
}