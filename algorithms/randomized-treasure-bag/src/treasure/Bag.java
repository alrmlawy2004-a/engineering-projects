
package treasure;
import java.util.HashMap;
import java.util.Random;

public class Bag {
    
    
    private HashMap<String, Treasure> bag;

    public Bag() {
        bag = new HashMap<>();
    }
    public void addTreasure(String name){
        if(bag.containsKey(name)){
            bag.get(name).increaseCount();
        }else{
            bag.put(name, new Treasure(name, 1));
        }
    }
    public void removeTreasure(String name){
        if(bag.containsKey(name)){
            Treasure t=bag.get(name);
            t.decreaseCount();
            if(t.getCount()==0){
                bag.remove(name);
            }
        }else{
            System.out.println("The treasure is not in the box");
        }
    }
    public void countTreasures(){
        if(bag.isEmpty()){
            System.out.println("The box is empty");
        return;
        
    }
      for (Treasure t : bag.values()) {
        System.out.println(t.getName() + " : " + t.getCount());
    }

    }
    public boolean containsTreasure(String name) {
    return bag.containsKey(name);
}
    
    public void generateRandomTreasures() {
    String[] treasureTypes = {"Gold", "Sword", "Shield", "Potion", "Diamond"};
    Random rand = new Random();

    int totalTreasures = rand.nextInt(5) + 5; 

    for (int i = 0; i < totalTreasures; i++) {
        String treasure = treasureTypes[rand.nextInt(treasureTypes.length)];
        addTreasure(treasure); // استخدام الدالة الحالية لإضافة الكنز
    }
    
}
    public String mostFrequentTreasure() {
    if (bag.isEmpty()) {
        return "The box is empty";
    }

    Treasure maxTreasure = null;
    for (Treasure t : bag.values()) {
        if (maxTreasure == null || t.getCount() > maxTreasure.getCount()) {
            maxTreasure = t;
        }
    }
    return maxTreasure.getName() + " : " + maxTreasure.getCount();
}


    
}
