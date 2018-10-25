import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class InsuranceDurationBak {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";
    
    static class Duration {
        private int start;
        private int end;
        
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
        
        public int getStart() {
            return start;
        }
        
        public int getEnd() {
            return end;
        }
    }
    
    private static class DurationComparator implements Comparator<Duration> {
        @Override
        public int compare(Duration o1, Duration o2) {
            return o1.getStart() - o2.getStart();
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
        Collections.sort(durations, new DurationComparator());
        
        List<Integer> results = new ArrayList<>(durations.size());
        
        for (int i = 0; i < durations.size(); ++i) {
            int duration = calculateDuration(durations, i);
            results.add(duration);
        }
        
        Collections.sort(results);
        return results.get(results.size() - 1);
    }
    
    private static int calculateDuration(List<Duration> durations, int ignoreIndex) {
        int result = 0;
        Duration lastDuration = null;
        for (int i = 0; i < durations.size(); ++i) {
            if (i == ignoreIndex) {
                continue;
            }
            
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
    
    private static List<Duration> loadDurations(String inputFile) {
        List<Duration> durations = new ArrayList<>();
        
        InputStream in = InsuranceDurationBak.class.getClassLoader().getResourceAsStream(inputFile);
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
        
        return durations;
    }
    
    private static void outputResult(int longestDuration) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(OUTPUT_FILE);
        out.println(longestDuration);
        out.close();
    }
    
}