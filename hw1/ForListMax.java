public class ForListMax {
    /** Returns the maximum value from m using a for loop. */
    public static int forMax(int[] m){
        int return_value = 0;
        for (int i=0; i < m.length; i += 1){
            if (m[i] > return_value){
                return_value = m[i];
            }
        }
        return return_value;
    }
    public static void main(String[] args){
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 23};
        System.out.println(forMax(numbers));
    }
}
