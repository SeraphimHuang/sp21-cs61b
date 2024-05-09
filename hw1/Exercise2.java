public class Exercise2 {
    public static int max(int[] m) {
        int rv = m[0];
        int i = 1;
        while (i < m.length){
            if (m[i] > rv){
                rv = m[i];
            }
            i += 1;
        }
        return rv;
    }
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.print(max(numbers));
    }
}
