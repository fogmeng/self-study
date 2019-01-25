package enums;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.junit.experimental.theories.DataPoint;

import java.util.ArrayList;
import java.util.List;

public class EnumTest {

    private static List<DataPoint> createListWithZerosForTimeInterval(DateTime from,
                                                                      DateTime to,
                                                                      ImmutableSet<Metric<? extends Number>> metrics) {
        List<DataPoint> points = new ArrayList<>();
        for (int i = 0; i <= Days.daysBetween(from, to).getDays(); i++) {
            points.add(new DataPoint().withDatas(createDatasWithZeroValues(metrics))
                    .withDayOfYear(from.withZone(DateTimeZone.UTC)
                            .plusDays(i)
                            .withTimeAtStartOfDay()));
        }
        return points;
    }
}
