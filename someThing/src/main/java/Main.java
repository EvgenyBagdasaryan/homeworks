import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
//import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class Main {
    public static void main (String[] args)  {
        System.out.print("Test");

        /*ArrayList<Integer> testValues = new ArrayList();
        testValues.add(0,15);
        testValues.add(1,1);
        testValues.add(2,2);
        testValues.add(3,100);
        testValues.add(4,50);

        Optional<Integer> maxValue = testValues.stream().max(Integer::compareTo);
        System.out.println("MaxValue="+maxValue);
        Optional<Integer> minValue = testValues.stream().min(Integer::compareTo);
        System.out.println("MinValue="+minValue);*/

        //////

        ArrayList<Integer> testValuesNull = new ArrayList();
        testValuesNull.add(0,null);
        testValuesNull.add(1,1);
        testValuesNull.add(2,2);
        testValuesNull.add(3,70);
        testValuesNull.add(4,50);

        testValuesNull.stream().filter((p) -> p != null).max(Integer::compareTo);

        Optional<Integer> maxValueNotNull =  testValuesNull.stream().filter((p) -> p != null).max(Integer::compareTo);
        System.out.println("maxValueNotNull="+maxValueNotNull);

        /*Collection<SportsCamp> sport = Arrays.asList(
                new SportsCamp("Ivan", 5),
                new SportsCamp("Petr", 7),
                new SportsCamp("Ira", 10)
        );
        // Пример 3
        // Поиск имени самого большого по продолжительности нахождения в лагере

        String name = sport.stream().max((p1,p2) -> p1.getDay().compareTo(p2.getDay())).get().getName();
        System.out.println("Name="+name);*/
    }

}


