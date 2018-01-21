import com.mqul.mp.QueryBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryBuilderTest {

    @Test
    public void sqlQueryBuilderTest()
    {
        QueryBuilder qb = new QueryBuilder("SELECT * FROM test");
        qb.where("test_column", "value1")
                .where("other_column", "value2")
                .where("final_column", "value3");

        final String qbQuery = qb.build();

        final String sqlQuery = "SELECT * FROM test " +
                "WHERE test_column = 'value1' " +
                "AND other_column = 'value2' " +
                "AND final_column = 'value3'";

        assertEquals(qbQuery, sqlQuery);
    }
}
