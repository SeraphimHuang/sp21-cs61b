public class Exercise3 {
    public static int max(int[] m) {
        int rv = m[0];
        for (int i = 1; i < m.length; i += 1){
            if (m[i] > rv){
                rv = m[i];
            }
        }
        return rv;
    }
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.print(max(numbers));
    }
}
