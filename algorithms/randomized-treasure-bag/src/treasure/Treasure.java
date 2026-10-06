
package treasure;


public class Treasure {
    private String name;
    private int count;
    
    public Treasure(String n,int c){
        this.name=n;
        this.count=c;
        
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
public void increaseCount() {
    count++;
}

public void decreaseCount() {
    count--;
}


    
   
}