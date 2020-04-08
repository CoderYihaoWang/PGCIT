public class EasyAI implements AI {
    @Override
    public String guess(Record feedback) {
        char[] arr = "0123456789".toCharArray();
        for (int i = 0; i < arr.length; ++i) {
            int rand = (int)(Math.random() * (arr.length - i)) + i;
            char temp = arr[i];
            arr[i] = arr[rand];
            arr[rand] = temp;
        }
        return new String(arr, 0, Compute.LENGTH);
    }
}
