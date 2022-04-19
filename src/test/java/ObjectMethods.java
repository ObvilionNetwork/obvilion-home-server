import java.util.ArrayList;
import java.util.List;

public class ObjectMethods {
    static List<Test1> test1s = new ArrayList<>();
    static List<Test2> test2s = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100000; i++) {
            test1s.add(new Test1());
        }

        for (int i = 0; i < 100000; i++) {
            //test2s.add(new Test2());
        }

        while (true) {
            Thread.sleep(1);
        }
    }

    public static class Test1 {

    }

    public static class Test2 {
        public void test() {
            long g = System.nanoTime() + 3;

            String[] strings = { "heeeee", "hooooo", "fdfdfdfdf" };
            String[] strings2 = { "heeeefde", "hoofdooo", "fdfdfdfdfdf" };

            for (int i = 0; i < strings.length; i++) {
                strings[i] += "22";
                strings[i] += strings2[i];
            }

            String.join(" ", strings);
        }

        public void test2() {
            long g = System.nanoTime() / 3 + 4534534;

            String[] strings = { "heeeee", "hooooo", "fdfdfdfdf" };
            String[] strings2 = { "heeeefde", "hoofdooo", "fdfdfdfdfdf" };

            for (int i = 0; i < strings.length; i++) {
                strings[i] += "22";
                strings[i] += strings2[i];
            }

            String.join(" ", strings);
        }
    }
}
