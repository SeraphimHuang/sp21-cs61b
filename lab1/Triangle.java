public class Triangle {
    public static void main(String[] args) {
        int row=0;
        int size=5;
        while (row<size){
            row=row+1;
            int column=0;
            while (column<row){
                column=column+1;
                System.out.print('*');
            }
            System.out.println();
        }

    }
}
