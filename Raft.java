import java.util.*;
import java.util.stream.Collectors;

public class Raft {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputCount = scanner.nextLine().split(" ");
        //int goatCount = Integer.parseInt(inputCount[0]);
        int courseCountMax = Integer.parseInt(inputCount[1]);
        //fill data
        List<Integer> goats = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        //sort
        goats = goats.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(goats);

        //int raftCapacity = Collections.max(goats);
        int raftCapacity = goats.get(goats.size() - 1);
        int coursesTaken = 0;
        List<Integer> goatsCopy = new ArrayList<>(goats);
        while (true) {
            if (goatsCopy.size() == 0 && coursesTaken <= courseCountMax){
                break;
            }
            if (coursesTaken > courseCountMax) {
                coursesTaken = 0;
                raftCapacity = raftCapacity + 1;
                goatsCopy.addAll(goats);
            }

            int currentTotalCourseWeight = 0;
            for (int i = 1; i < goatsCopy.size() + 1; i++) {
                int biggestGoat = goatsCopy.get(goatsCopy.size() - i);
                if (currentTotalCourseWeight + biggestGoat <= raftCapacity) {
                    currentTotalCourseWeight = currentTotalCourseWeight + goatsCopy.remove(goatsCopy.size() - i);
                    i--;
                }
            }

            coursesTaken++;
        }
        System.out.println(raftCapacity);
    }
}
