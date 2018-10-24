import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InsuranceDurationTest {
    
    @Test
    public void calculate_1() {
        List<InsuranceDuration.Duration> durations = Lists.newArrayList(
                new InsuranceDuration.Duration(6, 9),
                new InsuranceDuration.Duration(1, 5),
                new InsuranceDuration.Duration(3, 7)
        );
        int result = InsuranceDuration.calculate(durations);
        assertThat(result).isEqualTo(7);
    }
    
    @Test
    public void calculate_2() {
        List<InsuranceDuration.Duration> durations = Lists.newArrayList(
                new InsuranceDuration.Duration(1, 5),
                new InsuranceDuration.Duration(6, 7),
                new InsuranceDuration.Duration(8, 10)
        );
        int result = InsuranceDuration.calculate(durations);
        assertThat(result).isEqualTo(6);
    }
    
    @Test
    public void calculate_3() {
        List<InsuranceDuration.Duration> durations = Lists.newArrayList(
                new InsuranceDuration.Duration(1, 8),
                new InsuranceDuration.Duration(6, 7),
                new InsuranceDuration.Duration(9, 10)
        );
        int result = InsuranceDuration.calculate(durations);
        assertThat(result).isEqualTo(8);
    }
    
    @Test
    public void calculate_4() {
        List<InsuranceDuration.Duration> durations = Lists.newArrayList(
                new InsuranceDuration.Duration(1, 5),
                new InsuranceDuration.Duration(3, 7),
                new InsuranceDuration.Duration(6, 9),
                new InsuranceDuration.Duration(8, 10)
        );
        int result = InsuranceDuration.calculate(durations);
        assertThat(result).isEqualTo(8);
    }
    
    @Test
    public void calculate_5() {
        List<InsuranceDuration.Duration> durations = Lists.newArrayList(
                new InsuranceDuration.Duration(1, 5),
                new InsuranceDuration.Duration(3, 7),
                new InsuranceDuration.Duration(6, 9),
                new InsuranceDuration.Duration(8, 10),
                new InsuranceDuration.Duration(12, 15),
                new InsuranceDuration.Duration(13, 16),
                new InsuranceDuration.Duration(15, 18)
        );
        int result = InsuranceDuration.calculate(durations);
        assertThat(result).isEqualTo(15);
    }

}