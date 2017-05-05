package com.ivanmagda.habito;

import com.ivanmagda.habito.barchart.HabitoBarChartRange;
import com.ivanmagda.habito.barchart.formatters.MonthAxisValueFormatter;
import com.ivanmagda.habito.barchart.formatters.WeekDayAxisValueFormatter;
import com.ivanmagda.habito.barchart.formatters.YearAxisValueFormatter;
import com.ivanmagda.habito.models.Habit;
import com.ivanmagda.habito.view.model.HabitoBarChartViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(Parameterized.class)
public class HabitBarChartViewModelUnitTests {

    private HabitoBarChartViewModel mViewModel;
    private Habit mHabit;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {HabitoBarChartRange.DateRange.WEEK, WeekDayAxisValueFormatter.class},
                {HabitoBarChartRange.DateRange.MONTH, MonthAxisValueFormatter.class},
                {HabitoBarChartRange.DateRange.YEAR, YearAxisValueFormatter.class}
        });
    }

    @Parameterized.Parameter
    public HabitoBarChartRange.DateRange mInput;

    @Parameterized.Parameter(1)
    public Class mExpected;

    @Before
    public void init() {
        mHabit = new Habit();
        mViewModel = new HabitoBarChartViewModel(mHabit, mInput);
    }

    @Test
    public void getXAxisFormatterReturnsCorrectClass() throws Exception {
        Class actual = mViewModel.getXAxisFormatter().getClass();

        assertEquals(mExpected, actual);
    }
}