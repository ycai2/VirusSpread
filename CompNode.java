import java.util.*;

public class CompNode{
  protected int compNum;
  protected int t;
  protected boolean discovered = false;

  public CompNode(int c, int tk){
    compNum = c;
    t = tk;
  }

  public void discover(){
    discovered = true;
  }

  public int getCompNum(){
    return compNum;
  }

  public int getTime(){
    return t;
  }

  public boolean isDiscovered(){
    return discovered;
  }

  public String toString(){
    String str = "("+compNum+","+t+") ";
    return str;
  }
}

