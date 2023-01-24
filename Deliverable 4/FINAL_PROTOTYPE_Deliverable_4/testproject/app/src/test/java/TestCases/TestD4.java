package TestCases;
import static org.junit.Assert.assertEquals;


import com.example.testproject.ui.Request;
import com.example.testproject.users.Cook;

import org.junit.Test;


public class TestD4 {
    Request TestRequest = new Request("Food","Cook1","Chef1", "Client1");
    Cook TestCook= new Cook("Best","Ever","bestEver","None","");
    //Checks if a new Request's status is automatically set to pending
    @Test
    public void testStatus() {
        String expected = "pending";
        String actual = TestRequest.getStatus();
        assertEquals(expected,actual);
    }

    @Test
    //Checks if the rating is 0 by default
    public void testRating() {
        double expected = 0;
        double actual = TestCook.getRating();
        assertEquals(expected,actual,0);
    }

    @Test
    //Checks if the rating is changed after setting it to a new value
    public void testNewRating() {
        TestCook.setRating(4.5);
        double expected = 4.5;
        double actual = TestCook.getRating();
        assertEquals(expected,actual,0);
    }

    @Test
    //Checks if the cook's description and Address have the correct default values.
    public void testDescriptionAndAddress(){
        String expected = "Address unset";
        String actual = TestCook.getAddress();
        assertEquals(expected,actual);

        String wanted ="No description";
        String gotten=TestCook.getDescription();
        assertEquals(wanted,gotten);
    }


}
