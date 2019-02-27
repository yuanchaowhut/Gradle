import java.io.File;

/**
 * Created by Administrator on 2017/4/26 0026.
 */
public class listener7_0_java {

    public static void main(String[] args) {
//        Object file = new File("");
//        file.getParent();

        long start = System.nanoTime();
        for (int i = 0;i<10000000;i++){

        }
        long end = System.nanoTime();
        System.out.println ((end-start)/1.0e9);
    }
}
