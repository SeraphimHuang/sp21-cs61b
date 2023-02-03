public class TriangleDraw {
    public static void drawTriangle(int N) {
        int row=0;
        while (row<N){
            row=row+1;
            int column=0;
            while (column<row){
                column=column+1;
                System.out.print('*');
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        drawTriangle(10);
    }
}
