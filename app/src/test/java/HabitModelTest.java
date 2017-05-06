import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ivanmagda.habito.models.Habit;
import com.ivanmagda.habito.models.HabitList;
import com.ivanmagda.habito.models.HabitRecord;
import com.ivanmagda.habito.utils.HabitListUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HabitModelTest {


    @Test
    public void CopyHabitTest() throws Exception {

        Habit original = new Habit();
        Habit  newHabit = original.copy();

        HabitRecord originalRecord = original.getRecord();
        HabitRecord newRecord = newHabit.getRecord();

        assertEquals(original.getId(),newHabit.getId());
        assertEquals(originalRecord.getName(),newRecord.getName());
        assertEquals(originalRecord.describeContents(),newRecord.describeContents());
        assertEquals(originalRecord.getColor(),newRecord.getColor());
        assertEquals(originalRecord.getTarget(),newRecord.getTarget());
        assertEquals(originalRecord.getResetFreq(),newRecord.getResetFreq());
        assertEquals(originalRecord.getScore(),newRecord.getScore());
        assertEquals(originalRecord.getReminderHour(),newRecord.getReminderHour());
        assertEquals(originalRecord.getReminderMin(),newRecord.getReminderMin());
        assertEquals(originalRecord.getResetTimestamp(),newRecord.getResetTimestamp());

    }

    @Test
    public void ShortHabitListByNameTest() throws Exception {

        Habit habit = new Habit();
        habit.getRecord().setName("C");
        habit.getRecord().setCreatedAt(36541615);
        Habit habit2 = new Habit();
        habit2.getRecord().setName("A");
        habit2.getRecord().setCreatedAt(26541615);
        Habit habit3 = new Habit();
        habit3.getRecord().setName("B");
        habit3.getRecord().setCreatedAt(96541615);

        List<Habit> list = new ArrayList<Habit>();

        list.add(habit);
        list.add(habit2);
        list.add(habit3);

        HabitList habitList = new HabitList(list, HabitList.SortOrder.NAME);

        assertEquals(habitList.getHabits().get(0),habit2);
        assertEquals(habitList.getHabits().get(1),habit3);
        assertEquals(habitList.getHabits().get(2),habit);


        habitList.setSortOrder(HabitList.SortOrder.DATE);


        assertEquals(habitList.getHabits().get(0),habit3);
        assertEquals(habitList.getHabits().get(1),habit);
        assertEquals(habitList.getHabits().get(2),habit2);


    }

}