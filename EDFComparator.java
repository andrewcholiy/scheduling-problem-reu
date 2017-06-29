import java.util.Comparator;

public class EDFComparator implements Comparator<Job> {
    @Override
    public int compare(Job x, Job y) {
        // Assume neither string is null. Real code should
        // probably be more robust
        // x.length() - y.length(),
        if (x.getDeadline() < y.getDeadline()) {
            return -1;
        }
        if (x.getDeadline() > y.getDeadline()) {
            return 1;
        }
        return 0;
    }
}