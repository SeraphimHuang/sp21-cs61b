public class DrawingATriangleA {
    public static void main(String[] args){
        int size = 5;
        int row = 0;

        while (row < size){
            int col = 0;
            System.out.println();
            while (col <= row){
                System.out.print('*');
                col += 1;
            }
            row += 1;
        }
    }
}
