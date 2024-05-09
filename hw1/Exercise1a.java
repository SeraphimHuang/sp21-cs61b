public class Exercise1a {
    public static void main(String[] args){
        // System.out.println是在文本末尾添加换行符
        int row = 1;
        while (row <= 5){
            int col = 1;
            while (col < row){
                System.out.print('*');
                col += 1;
            }
            System.out.println("*");
            row += 1;
        }
    }
}
