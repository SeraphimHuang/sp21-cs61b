public class maxFuntionForLoop {
    public static int forMax(int[] m){
        int rv = 0;
        for (int i = 0; i < m.length; i+= 1){
            if (m[i] > rv){
                rv = m[i];
            }
        }
        return rv;
    }
    public static  void main(String[] args){
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.print(forMax(numbers));
    }
}
