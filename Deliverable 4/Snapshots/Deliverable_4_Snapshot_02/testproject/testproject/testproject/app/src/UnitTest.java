import androidx.core.widget.TextViewCompat;

import com.example.testproject.ui.LoginPage;
import com.example.testproject.users.Account;
import com.example.testproject.users.Client;
import com.example.testproject.users.accType;
import com.google.android.gms.common.api.Api;

import static org.junit.Assert.assertEquals;
import org.testng.annotations.Test;
import org.testng.mustache.Value;

public class UnitTest {

Account testclient= new Account("John","Doe","Johndoe@gmail.com","No Complaints", accType.CLIENT);
Account testcook= new Account("Clark","Kent","superman@gmail.com","Having issues flying",accType.COOK);
Account testAdmin= new Account("Bruce","Wayne","brucewayne@gmail.com","My Parents are dead",accType.ADMIN);
//Simple local tests that checks if the Emails match as well as the complaints
    @Test
    public void LoginTestClient(){
    String expected = "Johndoe@gmail.com";
    String actual = testclient.getEmail();
    assertEquals(expected,actual);

    accType wanted =accType.CLIENT;
    accType gotten=testclient.getRole();
    assertEquals(wanted,gotten);
    }

    @Test
    public void LoginTestCook(){
        String expected = "superman@gmail.com";
        String actual = testcook.getEmail();
        assertEquals(expected,actual);

        accType wanted =accType.COOK;
        accType gotten=testcook.getRole();
        assertEquals(wanted,gotten);
    }

    @Test
    public void LoginTestAdmin(){
        String expected = "brucewayne@gmail.com";
        String actual = testAdmin.getEmail();
        assertEquals(expected,actual);

        accType wanted =accType.ADMIN;
        accType gotten=testAdmin.getRole();
        assertEquals(wanted,gotten);
    }
    @Test
    public void ComplaintTest(){
        String expected = "Having issues flying";
        String actual = testcook.getComplaint();
        assertEquals(expected,actual);

        String wanted = "No Complaints";
        String gotten = testclient.getComplaint();
        assertEquals(wanted,gotten);

        String want = "My Parents are dead";
        String got = testAdmin.getComplaint();
        assertEquals(want,got);
    }



}
