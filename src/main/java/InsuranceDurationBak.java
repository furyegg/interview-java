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

    private static class DurationComparator implements Comparator<InsuranceDuration.Duration> {
        @Override
        public int compare(InsuranceDuration.Duration o1, InsuranceDuration.Duration o2) {
            return o1.getStart() - o2.getStart();
        }
    }
    
    public static void main(String[] args) {
        String inputFile = INPUT_FILE;
        if (args.length == 1) {
            inputFile = args[0];
        }
        
        List<InsuranceDuration.Duration> durations = null;
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
    
    static int calculate(List<InsuranceDuration.Duration> durations) {
        Collections.sort(durations, new DurationComparator());
        
        List<Integer> results = new ArrayList<>(durations.size());
        
        for (int i = 0; i < durations.size(); ++i) {
            int duration = calculateDuration(durations, i);
            results.add(duration);
        }
        
        Collections.sort(results);
        return results.get(results.size() - 1);
    }
    
    private static int calculateDuration(List<InsuranceDuration.Duration> durations, int ignoreIndex) {
        int result = 0;
        InsuranceDuration.Duration lastDuration = null;
        for (int i = 0; i < durations.size(); ++i) {
            if (i == ignoreIndex) {
                continue;
            }
            
            InsuranceDuration.Duration duration = durations.get(i);
            
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
    
    private static List<InsuranceDuration.Duration> loadDurations(String inputFile) {
        List<InsuranceDuration.Duration> durations = new ArrayList<>();
        
        InputStream in = InsuranceDurationBak.class.getClassLoader().getResourceAsStream(inputFile);
        Scanner scanner = new Scanner(in);
        boolean firstLine = true;
        while (scanner.hasNextLine()) {
            if (firstLine) {
                scanner.nextLine();
                firstLine = false;
                continue;
            }
            durations.add(InsuranceDuration.Duration.of(scanner.nextLine()));
        }
        
        return durations;
    }
    
    private static void outputResult(int longestDuration) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(OUTPUT_FILE);
        out.println(longestDuration);
        out.close();
    }
    
}