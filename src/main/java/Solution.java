public class Solution {
    public static void main(String[] args) {
        try {
            Horse h = new Horse(null, 0, 0);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }



    }
}
