package com.ivanmagda.habito;

import com.ivanmagda.habito.barchart.HabitoBarChartRange;
import com.ivanmagda.habito.utils.HabitoDateUtils;
import com.ivanmagda.habito.view.model.HabitDetailViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class HabitDetailViewModelUnitTests {

    private static final SimpleDateFormat WEEK_FORMAT = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
    private static final SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
    private static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("MMM yyyy", Locale.getDefault());

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {HabitoBarChartRange.DateRange.WEEK, formatDates(WEEK_FORMAT, HabitoDateUtils.getStartOfCurrentWeek(), HabitoDateUtils.getEndOfCurrentWeek())},
                {HabitoBarChartRange.DateRange.MONTH, formatDates(MONTH_FORMAT, HabitoDateUtils.getStartOfCurrentMonth(), HabitoDateUtils.getEndOfCurrentMonth())},
                {HabitoBarChartRange.DateRange.YEAR, formatDates(YEAR_FORMAT, HabitoDateUtils.getStartOfCurrentYear(), HabitoDateUtils.getEndOfCurrentYear())}
        });
    }

    private HabitDetailViewModel mViewModel;

    @Parameterized.Parameter
    public HabitoBarChartRange.DateRange mInput;

    @Parameterized.Parameter(1)
    public String mExpected;

    @Test
    public void getDateRangeStringTest() {
        mViewModel = new HabitDetailViewModel(mInput);
        String actual = mViewModel.getDateRangeString();
        assertEquals(mExpected, actual);
    }

    private static String formatDates(SimpleDateFormat dateFormat, long start, long end) {
        return dateFormat.format(new Date(start))
                + " - "
                + dateFormat.format(new Date(end));
    }
}
