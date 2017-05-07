import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ivanmagda.habito.models.Habit;
import com.ivanmagda.habito.models.HabitRecord;
import com.ivanmagda.habito.utils.HabitListUtils;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HabitListTest {

    @Mock
    DataSnapshot dataSnapshot;

    @Test
    public void DatasnapshotIsNull() throws Exception {

        List<Habit> result = HabitListUtils.CreateHabitListFromDataSnapshot(null);

        assertEquals(result.isEmpty(),true);
    }


}