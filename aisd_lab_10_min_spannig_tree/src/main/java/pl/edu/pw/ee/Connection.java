package pl.edu.pw.ee;

public class Connection implements Comparable<Connection>{
    String from;
    String to;
    int weight;
    
    public Connection (String from, String to, int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    
    public String getFrom (){
        return from;
    }
    
    public String getTo (){
        return to;
    }
    
    public int getWeight (){
        return weight;
    }

    @Override
    public int compareTo(Connection compare) {
        return this.getWeight() - compare.getWeight();
    }
}
