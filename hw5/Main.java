package lesson5hw;

public class Main {

    private static final int SIZE = 10000000;
    private static final int HALF = SIZE / 2;

    public float[] cellСount(float[] array) {
        for (int i = 0; i < array.length; i++)
            array[i] = (float) (array[i] * Math.sin(0.2f + array[i] / 5) * Math.cos(0.2f + array[i] / 5) * Math.cos(0.4f + array[i] / 2));
            return array;
    }

    public void firstThread() {
        float[] array = new float[SIZE];
        for (int i = 0; i < array.length; i++) array[i] = 1.0f;
        long a = System.currentTimeMillis();
        cellСount(array);
        System.out.println("Первый метод: " + (System.currentTimeMillis() - a));
    }

    public void secondThread() {
        float[] array = new float[SIZE];
        float[] array1 = new float[HALF];
        float[] array2 = new float[HALF];
        for (int i = 0; i < array.length; i++) array[i] = 1.0f;

        long a = System.currentTimeMillis();
        System.arraycopy(array,0, array1, 0, HALF);
        System.arraycopy(array, HALF, array2, 0, HALF);

        new Thread() {
            public void run() {
                float[] a1 = cellСount(array1);
                System.arraycopy(a1, 0, array1, 0, a1.length);
            }
        }.start();

        new Thread() {
            public void run() {
                float[] a2 = cellСount(array2);
                System.arraycopy(a2, 0, array2, 0, a2.length);
            }
        }.start();

        System.arraycopy(array1, 0, array, 0, HALF);
        System.arraycopy(array2, 0, array, HALF, HALF);
        System.out.println("Второй поток " + (System.currentTimeMillis() - a));
    }

    public static void main(String s[]){
        Main o = new Main();
        o.firstThread();
        o.secondThread();
    }
}
