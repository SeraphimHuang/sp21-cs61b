public class WhileListMax {
    /** Returns the maximum value from m */
    public static int max(int[] m){
        int m_len = m.length;
        int index = 0;
        int return_value = 0;
        while (index < m_len){
            if (m[index] > return_value){
                return_value = m[index];
            }
            index = index + 1;
        }
        return return_value;
    }
    public static void main(String[] args){
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 23};
                System.out.println(max(numbers));
    }
}
