package puzzlegame;
import java.util.ArrayList;

/**
 +........................................................................+
 . Name:  Rukshan Fernando                                                .
 . UoWNO: 18098217                                                        .
 . StID:  2019784                                                         .
 +------------------------------------------------------------------------+
 **/

public class BFS {
    /**Save Place*/
    private ArrayList<ArrayList<String>> grid;
    private ArrayList<ArrayList<Boolean>> seen;
    private ArrayList<ArrayList<ArrayList<Place>>> adjacencyList; //Adjacency-List

    /**help to tract path*/
    private ArrayList<ArrayList<Place>> redevelopArray = new ArrayList<>();

    // Start Place
    private Place sPlace;
    //End Place
    private Place fPlace;
    private int countRow;
    private int countColumn;

    BFS(ArrayList<ArrayList<String>> grid){
        this.grid = grid;
        countRow = grid.size();
        countColumn = grid.get(0).size();
    }

    /**find starting and ending places*/
    private void findPlace() {
        for (int r = 0; r < countRow; r++) {
            for (int c = 0; c < countColumn; c++) {
                if (sPlace == null && grid.get(r).get(c).equals("S"))
                    sPlace = new Place(r, c);
                if (fPlace == null && grid.get(r).get(c).equals("F"))
                    fPlace = new Place(r, c);
            }
        }
    }

    private void buildAdjacencyList() {
        adjacencyList = new ArrayList<>();
        for (int r = 0; r < countRow; r++) {
            adjacencyList.add(r, new ArrayList<>());
            for (int i = 0; i < countColumn; i++) {
                ArrayList<Place> nList = new ArrayList();

//                find a place before the neighbour rock position

                /** go to right*/
                for (int z = i + 1; z < countColumn; z++) {
                    if(z == fPlace.getColumn() && r == fPlace.getRow()){
                        nList.add(fPlace);
                        break;
                    }
                    if (i - 1 < countColumn && grid.get(r).get(i + 1).equals("0"))
                        break;
                    if (z < countColumn && (grid.get(r).get(z).equals("0"))) {
                        Place place = new Place(r, z - 1);
                        nList.add(place);
                        break;
                    } else if (z == countColumn - 1) {
                        // index reach to the ind of the grid
                        Place place = new Place(r, z);
                        nList.add(place);
                        break;
                    }
                }

                /** go to left*/
                for (int z = i - 1; z >= 0; z--) {
                    if(z == fPlace.getColumn() && r == fPlace.getRow()){
                        nList.add(fPlace);
                        break;
                    }
                    if (i - 1 >= 0 && grid.get(r).get(i - 1).equals("0"))
                        break;
                    if (grid.get(r).get(z).equals("0")) {
                        Place place = new Place(r, z + 1);
                        nList.add(place);
                        break;
                    } else if (z == 0) {
                        // index reach to the ind of the grid
                        Place place = new Place(r, z);
                        nList.add(place);
                        break;
                    }
                }

                /** go to top*/
                for (int z = r - 1; z >= 0; z--) {

                    if(i == fPlace.getColumn() && z == fPlace.getRow()){
                        nList.add(fPlace);
                        break;
                    }

                    if (r - 1 >= 0 && grid.get(r - 1).get(i).equals("0"))
                        break;
                    if (grid.get(z).get(i).equals("0")) {
                        Place place = new Place(z + 1, i);
                        nList.add(place);
                        break;
                    } else if (z == 0) {
                        // index reach to the ind of the grid
                        Place place = new Place(z, i);
                        nList.add(place);
                        break;
                    }
                }

                /** go to bottom*/
                for (int z = r + 1; z < countColumn; z++) {

                    if(i == fPlace.getColumn() && z == fPlace.getRow()){
                        nList.add(fPlace);
                        break;
                    }

                    if (r + 1 >= 0 && grid.get(r + 1).get(i).equals("0"))
                        break;
//                    if(z == fPlace.getCol() && r == fPlace.getRow()){
//                        nList.add(fPlace);
//                    }else
                    if (grid.get(z).get(i).equals("0")) {
                        Place place = new Place(z - 1, i);
                        nList.add(place);
                        break;
                    } else if (z == countColumn - 1) {
                        // index reach to the in of the grid
                        Place place = new Place(z, i);
                        nList.add(place);
                        break;
                    }
                }
                adjacencyList.get(r).add(nList);
            }
        }
    }

    // create empty list
    private void buildSeenGrid() {
        seen = new ArrayList<>();
        for (int i = 0; i < countRow; i++) {
            ArrayList<Boolean> rowTemp = new ArrayList<>();
            for (int x = 0; x < countRow; x++) {
                rowTemp.add(false);
            }
            seen.add(rowTemp);
        }
    }

    // create empty list
    private void buildRedevelopArray(){
        for(ArrayList r : grid){
            ArrayList<Place> temp = new ArrayList<>(countRow);
            for(int i = 0; i< countRow; i++){
                temp.add(new Place());
            }
            redevelopArray.add(temp);
        }
    }

    public void find(){
        findPlace();
        buildSeenGrid();
        buildAdjacencyList();
        buildRedevelopArray();
        resolve();

        ArrayList<Place> path = developPath();
//        System.out.println(path);
        convertPathToWords(path);
    }

    /**convert path list to words*/
    private void convertPathToWords(ArrayList<Place> path){
        if(path.get(0).isItEmpty()){
            System.out.println("No Path found");
            return;
        }

        int n = 1;
        Place previousPlace = sPlace;

        for(int index = 0; index< path.size();index++){
            if(path.get(index) == sPlace){
                System.out.println(n +" Start at ("+ sPlace.getRow()+","+ sPlace.getColumn()+")");
            }else if(path.get(index) == fPlace){
                System.out.println(n + " Done!");
            }else if(previousPlace.getColumn()< path.get(index).getColumn()){
                System.out.println(n +" Move right to  "+path.get(index));
            }else if(previousPlace.getColumn()> path.get(index).getColumn()){
                System.out.println(n + " Move left to  ("+path.get(index).getRow()+","+path.get(index).getColumn()+")");
            }else if(previousPlace.getRow()> path.get(index).getRow()){
                System.out.println(n + " Move up to   ("+path.get(index).getRow()+","+path.get(index).getColumn()+")");
            }else if(previousPlace.getRow()< path.get(index).getRow()){
                System.out.println(n + " Move down to   ("+path.get(index).getRow()+","+path.get(index).getColumn()+")");
            }
            previousPlace = path.get(index);
            n++;
        }
    }

    private void resolve() {
        seen.get(sPlace.getRow()).set(sPlace.getColumn(),true);

        // help to go to indexes
        Queue q = new Queue();
        //add first index to queue
        q.enqueue(sPlace);

        while(!q.isItEmpty()){

            Place place = q.dequeue();
            //get neighbour Nodes
            ArrayList<Place> neighboursNodes = adjacencyList.get(place.getRow()).get(place.getColumn());

            // go to all neighbour nodes and add to the queue
            for(Place nextPlace : neighboursNodes){
                if(!seen.get(nextPlace.getRow()).get(nextPlace.getColumn())){
                    q.enqueue(nextPlace);

                    // make node as seen
                    seen.get(nextPlace.getRow()).set(nextPlace.getColumn(), true);
//
//                    add previous node place
                    redevelopArray.get(nextPlace.getRow()).set(nextPlace.getColumn(), place);
                }
            }
        }
    }

    /** find path using regenerateArray
     * return path list start position - end*/
    private ArrayList<Place> developPath(){

        ArrayList<Place> path = new ArrayList<>();

        //start with ending position
        Place latestPositionPlace = redevelopArray.get(fPlace.getRow()).get(fPlace.getColumn());
        if(latestPositionPlace.isItEmpty()){
            path.add(new Place());
        }else{
            //adding end path to path list
            path.add(fPlace);
            path.add(latestPositionPlace);

            // checks the latest index place and find next path
            while(latestPositionPlace != sPlace){
                latestPositionPlace = redevelopArray.get(latestPositionPlace.getRow()).get(latestPositionPlace.getColumn());
                path.add(latestPositionPlace);
            }
        }

        //  revers path because path generate end - start
        ArrayList<Place> revers = new ArrayList<>();

        for(Place l : path){
            revers.add(0,l);
        }
        path = revers;
        return path;
    }
}
