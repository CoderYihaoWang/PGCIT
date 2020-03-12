package intarrayrotation;

public class IntArrayRotation {
    public static void main(String[] args) {
        IntArrayRotation prog = new IntArrayRotation();
        prog.start();
    }

    //Begin your codes here

    // this program assumes that the input is in valid format
    public void start(){
        System.out.println("Please input a list of arrays of integers, separate arrays by '|' and elements in an array by ','");
        System.out.print("Before: ");
        int[][] arrs = toArrays(Keyboard.readInput());
        rotate(arrs);
        System.out.println("After being rotated:");
        for (int i = 0; i < arrs.length; ++i) {
            for (int j = 0; j < arrs[i].length; ++j) {
                System.out.print(arrs[i][j]);
                if (j != arrs[i].length - 1)
                    System.out.print(", ");
            }
            if (i != arrs.length - 1)
                System.out.print("|");
        }
    }

    private int[][] toArrays(String s) {
        String[] as = s.replaceAll("\\s", "").split("\\|");
        String[][] aas = new String[as.length][];
        for (int i = 0; i < as.length; ++i)
                aas[i] = as[i].split(",");
        int[][] res = new int[aas.length][];
        for (int i = 0; i < res.length; ++i) {
            res[i] = new int[aas[i].length];
            for (int j = 0; j < res[i].length; ++j)
                res[i][j] = Integer.parseInt(aas[i][j]);
        }
        return res;
    }

    private void rotate(int[][] arrs) {
        for (int i = 0; i < arrs.length; ++i)
            rotate(arrs[i]);
    }

    private void rotate(int[] arr) {
        if (arr.length < 1)
            return;
        int t = arr[0];
        // IntelliJ generates a warning here:
        // saying that we should use arraycopy instead of copying it manually
        // however, this method is forbidden in this challenge
        for (int j = 0; j < arr.length - 1; ++j)
            arr[j] = arr[j + 1];
        arr[arr.length - 1] = t;
    }
}
