public class DrawingATriangleB {
    public static void drawTriangle(int N){
        int row = 0;

        while (row < N){
            int col = 0;
            System.out.println();
            while (col <= row){
                System.out.print('*');
                col += 1;
            }
            row += 1;
        }
    }
    public static void main(String[] args){
        drawTriangle(10);
    }
}
