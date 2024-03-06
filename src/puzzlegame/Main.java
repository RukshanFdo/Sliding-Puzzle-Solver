package puzzlegame;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 +........................................................................+
 . Name:  Rukshan Fernando                                                .
 . UoWNO: 18098217                                                        .
 . StID:  2019784                                                         .
 +------------------------------------------------------------------------+
 **/

public class Main {
    /**The method is capable of reading text files. and make an ArrayList with all the items from the text file*/
    static ArrayList<ArrayList<String>> grid;
    public static void doubledDirectory(int count){
        ArrayList<String> rowTest = grid.get(1);
        ArrayList<ArrayList<String>> checkGrid = new ArrayList<>();
        for(int i = 0; i<(Math.pow(2,count)-1);i++){
            for(int x = 0 ; x< grid.size(); x++){
                checkGrid.add(rowTest);}
        }

       grid.addAll(checkGrid);
        System.out.println("row: "+grid.size());
        System.out.println("col: "+grid.get(0).size());
    }

    //read file
    static public void readsData(String nameOfFile) {
        grid = new ArrayList<>();
        try {
            File ourObj = new File(nameOfFile);
            Scanner ourReader = new Scanner(ourObj);
            while (ourReader.hasNextLine()) {
                String data = ourReader.nextLine();
                ArrayList<String> listD = new ArrayList<>();
                for (int i = 0; i < data.length(); i++) {
                    listD.add(String.valueOf(data.charAt(i)));
                }
                grid.add(listD);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        readsData("datafile.txt");
        //readsData("defaultWorst.txt");
        //readsData("examples/maze10_1.txt");
        //readsData("examples/maze10_2.txt");
        //readsData("examples/maze10_3.txt");
        //readsData("examples/maze10_4.txt");
        //readsData("examples/maze10_5.txt");
        //readsData("examples/maze15_1.txt");
        //readsData("examples/maze15_2.txt");
        //readsData("examples/maze15_3.txt");
        //readsData("examples/maze15_4.txt");
        //readsData("examples/maze15_5.txt");
        //readsData("examples/maze20_1.txt");
        //readsData("examples/maze20_2.txt");
        //readsData("examples/maze20_3.txt");
        //readsData("examples/maze20_4.txt");
        //readsData("examples/maze20_5.txt");
        //readsData("examples/maze25_1.txt");
        //readsData("examples/maze25_2.txt");
        //readsData("examples/maze25_3.txt");
        //readsData("examples/maze25_4.txt");
        //readsData("examples/maze25_5.txt");
        //readsData("examples/maze30_1.txt");
        //readsData("examples/maze30_2.txt");
        //readsData("examples/maze30_3.txt");
        //readsData("examples/maze30_4.txt");
        //readsData("examples/maze30_5.txt");
        //readsData("examples_2/puzzle_20.txt");
        //readsData("examples_2/puzzle_40.txt");
        //readsData("examples_2/puzzle_80.txt");
        //readsData("examples_2/puzzle_160.txt");
        //readsData("examples_2/puzzle_320.txt");
        //readsData("examples_2/puzzle_640.txt");
        //readsData("examples_2/puzzle_1280.txt");
        //readsData("examples_2/puzzle_2560.txt");

        //doubledDirectory(4);
        long begin = System.currentTimeMillis();
        BFS pathSearcher = new BFS(grid);
        pathSearcher.find();
        long latest = System.currentTimeMillis();
        double elapsed = (latest - begin) / 1000.0;
        System.out.println("time : "+ elapsed+"s");
    }
}
