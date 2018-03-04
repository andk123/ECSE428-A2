import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParcelMailTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final String from = "h7s1a4";
	private final String to = "k1p0a9";
	private final String len = "50";
	private final String width = "50";
	private final String height = "50";
	private final String weight = "5";
	private final String type = "regular";
	
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    	System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() throws Exception {
    	System.setOut(System.out);
    }

    @Test
    public void no_args() {
    	String args[] = null;
    	ParcelMail.main(args);
    	
    	assertEquals(outContent.toString(),"Enter arguments");
    }
    
    @Test
    public void less_7args() {
    	String args[] = {from, to, len, width, height};
    	ParcelMail.main(args);
    	
    	assertEquals(outContent.toString(),"Missing arguments");
    }
    
    @Test
    public void more_7args() {
    	String additional = "additional Info";
    	String args[] = {from, to, len, width, height, weight, type, additional};
    	ParcelMail.main(args);
    	
    	assertEquals(outContent.toString(),"Too many arguments");
    }

}
