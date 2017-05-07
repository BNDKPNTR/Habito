package com.ivanmagda.habito;

import android.content.Context;
import android.graphics.Color;
import android.support.test.InstrumentationRegistry;

import com.ivanmagda.habito.models.Habit;
import com.ivanmagda.habito.models.ResetFrequency;
import com.ivanmagda.habito.view.model.HabitListItemViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import retrofit2.Response;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class HabitListItemViewModelUnitTests {
    private static Context mContext;
    private static Habit mHabit;
    private static HabitListItemViewModel mViewModel;

    @Before
    public void init() {
        mContext = InstrumentationRegistry.getTargetContext();
        mHabit = new Habit();
        mViewModel = new HabitListItemViewModel(mContext, mHabit);
    }

    @Test
    public void getHabitNameTextColorReturnWhite() {
        mHabit.getRecord().setColor(Color.BLACK);
        int actual = mViewModel.getHabitNameTextColor();
        assertEquals(Color.WHITE, actual);
    }

    @Test
    public void getHabitNameTextColorReturnsPrimaryTextColor() {
        mHabit.getRecord().setColor(Color.WHITE);
        int actual = mViewModel.getHabitNameTextColor();
        assertEquals(mContext.getColor(R.color.primary_text), actual);
    }

    @Test
    public void getScoreReturnsScoreAndTarget() {
        mHabit.getRecord().setTarget(2);
        String expected = String.valueOf(mHabit.getRecord().getScore()) + "/" + String.valueOf(mHabit.getRecord().getTarget());
        String actual = mViewModel.getScore();
        assertEquals(expected, actual);
    }

    @Test
    public void getScoreReturnsScore() {
        String expected = String.valueOf(mHabit.getRecord().getScore());
        String actual = mViewModel.getScore();
        assertEquals(expected, actual);
    }

    @RunWith(Parameterized.class)
    public static class GetResetFreqTests {
        private static final SimpleDateFormat FORMAT = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            Context context = InstrumentationRegistry.getTargetContext();
            mHabit = new Habit();

            return Arrays.asList(new Object[][]{
                    {ResetFrequency.DAY, context.getString(R.string.list_item_reset_today)},
                    {ResetFrequency.WEEK, resetFreqStringWithParameter(ResetFrequency.WEEK)},
                    {ResetFrequency.MONTH, resetFreqStringWithParameter(ResetFrequency.MONTH)},
                    {ResetFrequency.YEAR, resetFreqStringWithParameter(ResetFrequency.YEAR)},
                    {ResetFrequency.NEVER, context.getString(R.string.list_item_reset_never, FORMAT.format(new Date(mHabit.getRecord().getCreatedAt())))}
            });
        }

        @Parameterized.Parameter
        public String mInput;

        @Parameterized.Parameter(1)
        public String mExpected;

        @Before
        public void init() {
            mContext = InstrumentationRegistry.getTargetContext();
            mHabit = new Habit();
            mViewModel = new HabitListItemViewModel(mContext, mHabit);
        }

        @Test
        public void getResetFreqTest() {
            mHabit.getRecord().setResetFreq(mInput);
            String actual = mViewModel.getResetFreq();
            assertEquals(mExpected, actual);
        }

        private static String resetFreqStringWithParameter(String freqPeriod) {
            return InstrumentationRegistry.getTargetContext().getResources().getString(R.string.list_item_reset_freq, freqPeriod);
        }
    }
}
