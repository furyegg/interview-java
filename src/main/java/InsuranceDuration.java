import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InsuranceDuration {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";
    
    static class Duration {
        private int start;
        private int end;
        private int intersect1;
        private int intersect2;
        
        public Duration(int start, int end) {
            if (start >= end) {
                throw new IllegalArgumentException("Start should less than end");
            }
            this.start = start;
            this.end = end;
        }
        
        public static Duration of(String line) {
            String[] items = line.split(" ");
            if (items.length != 2) {
                throw new IllegalArgumentException("Each line should includes two values");
            }
            try {
                Integer start = Integer.valueOf(items[0]);
                Integer end = Integer.valueOf(items[1]);
                return new Duration(start, end);
            } catch (Exception e) {
                throw new IllegalArgumentException("Include non digital value");
            }
        }
        
        public int duration() {
            return end - start;
        }
        
        public int twoIntersectLength() {
            return intersect1 - start + end - intersect2;
        }
        
        public int nonTwoIntersectLength() {
            if (hasTwoIntersectParts()) {
                return duration() - twoIntersectLength();
            } else {
                return Integer.MAX_VALUE;
            }
        }
        
        public boolean hasTwoIntersectParts() {
            return intersect1 > 0 && intersect2 > 0;
        }
        
        public void allIntersect() {
            intersect1 = end;
            intersect2 = end;
        }
        
        public int getStart() {
            return start;
        }
        
        public int getEnd() {
            return end;
        }
    
        public void setIntersect1(int intersect1) {
            this.intersect1 = intersect1;
        }
    
        public void setIntersect2(int intersect2) {
            this.intersect2 = intersect2;
        }
    }
    
    public static void main(String[] args) {
        String inputFile = INPUT_FILE;
        if (args.length == 1) {
            inputFile = args[0];
        }
        
        List<Duration> durations = null;
        try {
            durations = loadDurations(inputFile);
        } catch (Exception e) {
            System.err.println("Failed to load data");
            e.printStackTrace();
            System.exit(1);
        }
        
        int longestDuration = calculate(durations);
        
        try {
            outputResult(longestDuration);
        } catch (FileNotFoundException e) {
            System.err.println("Failed to save result");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    static int calculate(List<Duration> durations) {
        Collections.sort(durations, (o1, o2) -> o1.getStart() - o2.getStart());
        
        Duration lastDuration = durations.get(0);
        for (int i = 1; i < durations.size(); ++i) {
            Duration duration = durations.get(i);
            if (duration.getEnd() <= lastDuration.getEnd()) {
                duration.allIntersect();
            } else if (duration.getStart() < lastDuration.getEnd()) {
                lastDuration.setIntersect2(duration.getStart());
                duration.setIntersect1(lastDuration.getEnd());
                lastDuration = duration;
            } else if (duration.getStart() >= lastDuration.getEnd()) {
                lastDuration = duration;
            }
        }
        
        return processDuration(durations);
    }
    
    private static int processDuration(List<Duration> durations) {
        boolean hasTwoIntersect = durations.stream()
                .anyMatch(d -> d.hasTwoIntersectParts());
        
        if (hasTwoIntersect) {
            durations = processTwoIntersect(durations);
        } else {
            durations = processNonTwoIntersect(durations);
        }
    
        List<Duration> finalDurations = durations.subList(1, durations.size());
        Collections.sort(finalDurations, (o1, o2) -> o1.getStart() - o2.getStart());
        return calculateDuration(finalDurations);
    }
    
    private static List<Duration> processTwoIntersect(List<Duration> durations) {
        Collections.sort(durations, (o1, o2) -> o1.nonTwoIntersectLength() - o2.nonTwoIntersectLength());
        return durations;
    }
    
    private static List<Duration> processNonTwoIntersect(List<Duration> durations) {
        Collections.sort(durations, (o1, o2) -> o1.duration() - o2.duration());
        return durations;
    }
    
    private static int calculateDuration(List<Duration> durations) {
        int result = 0;
        Duration lastDuration = null;
        for (int i = 0; i < durations.size(); ++i) {
            Duration duration = durations.get(i);
            
            if (result == 0) {
                result = duration.duration();
                lastDuration = duration;
            }
            if (duration.getEnd() <= lastDuration.getEnd()) {
                continue;
            }
            if (duration.getStart() < lastDuration.getEnd()) {
                result += duration.getEnd() - lastDuration.getEnd();
                lastDuration = duration;
            }
            if (duration.getStart() >= lastDuration.getEnd()) {
                result += duration.duration();
                lastDuration = duration;
            }
        }
        return result;
    }
    
    private static List<Duration> loadDurations(String inputFile) throws IOException {
        List<Duration> durations = new ArrayList<>();
        
        InputStream in = InsuranceDuration.class.getClassLoader().getResourceAsStream(inputFile);
        Scanner scanner = new Scanner(in);
        boolean firstLine = true;
        while (scanner.hasNextLine()) {
            if (firstLine) {
                scanner.nextLine();
                firstLine = false;
                continue;
            }
            durations.add(Duration.of(scanner.nextLine()));
        }
        in.close();
        
        return durations;
    }
    
    private static void outputResult(int longestDuration) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(OUTPUT_FILE);
        out.println(longestDuration);
        out.close();
    }
    
}