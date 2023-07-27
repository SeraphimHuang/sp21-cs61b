public class maxFuntion {
    public static int max(int[] m){
        int rv = 0;
        if (m.length == 0){
            return rv;
        }
        int index = 0;
        while (index < m.length){
            if (m[index] > rv){
                rv = m[index];
            }
            index += 1;
        }
        return rv;
    }
    public static void main(String[] args){
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.print(max(numbers));
    }
}
