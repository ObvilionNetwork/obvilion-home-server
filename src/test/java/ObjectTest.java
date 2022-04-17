import java.util.ArrayList;
import java.util.List;

public class ObjectTest {
    public static void main(String[] args) {
        List<Test> tests = new ArrayList<>();
        tests.add(new Test(10));
        tests.add(new Test(15));
        tests.add(new Test(0));

        testMethod(tests.get(0));
        testMethod2(tests.get(1));

        System.out.println(tests.get(0));
        System.out.println(tests.get(1));
        System.out.println(tests.get(2));
    }

    // nope
    public static void testMethod(Test test) {
        test = new Test(2);
    }

    // works
    public static void testMethod2(Test test) {
        test.i = 1;
    }

    static class Test {
        public int i;

        public Test(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "i=" + i +
                    '}';
        }
    }
}
