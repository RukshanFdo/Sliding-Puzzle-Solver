package puzzlegame;

/**
 +........................................................................+
 . Name:  Rukshan Fernando                                                .
 . UoWNO: 18098217                                                        .
 . StID:  2019784                                                         .
 +------------------------------------------------------------------------+
 **/

public class Place {
    private int row = -1;
    private int column = -1;
    private boolean isChecked = false;

    public Place(){
    }
    public Place(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public boolean isItEmpty(){
        if(row == -1 || column == -1){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "("+ row +
                ","+ column +
                ')';
    }
}
