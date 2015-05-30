package Lab6.Ex2;

import java.util.ArrayList;

/**
 * Created by Jul on 26.04.2015.
 */
public class MetroCardBank {
    private ArrayList<MetroCard> store;

    public MetroCardBank() {
        store=new ArrayList<MetroCard>();
    }

    public ArrayList<MetroCard> getStore() {
        return store;
    }

    public void setStore(ArrayList<MetroCard> store) {
        this.store = store;
    }
    public int findMetroCard(String serNum){
        if( store.contains(serNum))
        return store.indexOf(serNum);
        else return -1;
    }
    public int numCards(){
        return store.size();
    }
    public void addCard(MetroCard newCard){
           store.add(newCard);
    }
    public boolean removeCard(String serNum){
        if(store.remove(serNum))
        return true;
        else return false;
    }
   public boolean   addMoney(String   serNum,   double   money){

       if(store.remove(serNum)) return  true;
       else return false;
    }
    public boolean getMoney(String serNum, double money) {
        if(store.remove(serNum)) return  true;
        else return false;
    }


    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder("List of MetroCards:");
        for (MetroCard c : store) {
            buf.append("\n\n" + c);
        }
        return buf.toString();
    }

}
