public class PrintStarSequence {
    public static void main(String[] args) {
        int r = 1;
        while (r <= 5) {
            int c = 0;
            while (c < r){
                System.out.print('*');
                c = c+1;
            }
            System.out.println();
            r = r+1;
        }
    }
}