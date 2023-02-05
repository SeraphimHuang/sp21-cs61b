public class DrawTriangle {
    public static void drawTriangle(int n){
        int row = 1;
        while (row <= n){
            int size = 0;
            while (size < row){
                System.out.print('*');
                size = size+1;
            }
            row = row+1;
            System.out.println();
        }
    }
    public static void main(String[] args){
        drawTriangle(10);
    }
}
