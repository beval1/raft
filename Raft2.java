import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Raft2 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String[] inputCount = scanner.nextLine().split(" ");
            //int goatsCount = Integer.parseInt(inputCount[0]);
            int courseCountMax = Integer.parseInt(inputCount[1]);
            //fill data
            Integer[] goats = Arrays.stream(scanner.nextLine().split(" "))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            //sort
            goats = Arrays.stream(goats)
                    .sorted()
                    .toArray(Integer[]::new);
            System.out.println(Arrays.toString(goats));

            int raftCapacity = goats[goats.length-1];
            int coursesTaken = 0;
            Integer[] goatsCopy = goats.clone();
            StringBuilder courseGoats = new StringBuilder();
            while (true) {
                if (Arrays.stream(goats).distinct().count() <= 1 && coursesTaken <= courseCountMax){
                    break;
                }
                if (coursesTaken > courseCountMax) {
                    coursesTaken = 0;
                    raftCapacity = raftCapacity + 1;
                    goats = goatsCopy.clone();
                    courseGoats.setLength(0);
                }

                int arrPointer = goats.length-1;
                int currentTotalCourseWeight = 0;
                while (arrPointer>=0) {
                    int biggestGoat = goats[arrPointer];
                    if (biggestGoat != -1 && currentTotalCourseWeight + biggestGoat <= raftCapacity) {
                        currentTotalCourseWeight = currentTotalCourseWeight + biggestGoat;
                        courseGoats.append(biggestGoat).append(" ");
                        goats[arrPointer] = -1;
                    }
                    arrPointer--;
                }
                courseGoats.append("\n");
                coursesTaken++;
            }
            System.out.println(courseGoats);
            System.out.println(raftCapacity);
        }
}
