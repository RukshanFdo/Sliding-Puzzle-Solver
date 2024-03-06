package puzzlegame;
import java.util.ArrayList;

/**
 +........................................................................+
 . Name:  Rukshan Fernando                                                .
 . UoWNO: 18098217                                                        .
 . StID:  2019784                                                         .
 +------------------------------------------------------------------------+
 **/

public class Queue {

    ArrayList<Place> queue = new ArrayList<>();
    //Adding Data
    public void enqueue(Place data){
        queue.add(data);
    }
    /**Getting data from the queue and delete data
     * returning data of place*/
    public Place dequeue(){
        Place data = queue.get(0);
        queue.remove(0);
        return data;
    }

    public boolean isItEmpty(){
        if(queue.size() == 0)
            return  true;
        return false;
    }

    @Override
    public String toString() {
        String string = "";
        for(Place i : queue){
            string+= i+ " ";
        }
        return string;
    }
}
