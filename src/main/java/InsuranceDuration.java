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
                int start = Integer.valueOf(items[0]);
                int end = Integer.valueOf(items[1]);
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
        
        @Override
        public String toString() {
            return String.format("(%d, %d)", start, end);
        }
    }
    
    public static void main(String[] args) {
        List<Duration> durations = null;
        try {
            durations = loadDurations();
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
        Collections.sort(durations, (d1, d2) -> {
            int start = d1.getStart() - d2.getStart();
            if (start == 0) {
                return d2.duration() - d1.duration();
            } else {
                return start;
            }
        });
        
        int minDurationIndex = 0;
        int minGapDurationIndex = -1;
        int minGap = -1;
        
        for (int i = 1; i < durations.size(); ++i) {
            Duration current = durations.get(i);
            
            if (durations.get(minDurationIndex).duration() > current.duration()) {
                minDurationIndex = i;
            }
            
            if (i == durations.size() - 1) {
                break;
            }
            
            Duration previous = durations.get(i - 1);
            Duration next = durations.get(i + 1);
            
            if (current.getEnd() <= previous.getEnd()) {
                minGapDurationIndex = i;
                minGap = 0;
                break;
            }
            
            if (current.getStart() < previous.getEnd()
                    && current.getEnd() > next.getStart() && current.getEnd() <= next.getEnd()) {
                int currentGap = next.getStart() - previous.getEnd();
                if (minGap == -1) {
                    minGap = currentGap;
                    minGapDurationIndex = i;
                }
                if (currentGap < minGap) {
                    minGap = currentGap;
                    minGapDurationIndex = i;
                }
            }
        }
        
        int ignoreIndex;
        if (minGap < 0) {
            ignoreIndex = minDurationIndex;
        } else {
            ignoreIndex = minGap > durations.get(minDurationIndex).duration() ? minDurationIndex : minGapDurationIndex;
        }
        
        return calculateDuration(durations, ignoreIndex);
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
    
    private static List<Duration> loadDurations() throws IOException {
        List<Duration> durations = new ArrayList<>();
        
        InputStream in = InsuranceDuration.class.getClassLoader().getResourceAsStream(INPUT_FILE);
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