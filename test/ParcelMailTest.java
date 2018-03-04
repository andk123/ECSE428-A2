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
	private final String from = "H7S1A4";
	private final String to = "K1P0A9";
	private final String len = "50";
	private final String width = "50";
	private final String height = "50";
	private final String weight = "5";
	private final String type_regular = "regular";
	private final String type_xpresspost = "xpresspost";
	private final String type_priority = "priority";
	
	private final String len_low = "5";
	private final String len_high = "300";
	private final String width_low = "5";
	private final String width_high = "500";
	private final String height_low = "0";
	private final String height_high = "300";
	private final String weight_high = "35";
	private final String width_max = "200"; // to test girth
	private final String height_max = "200"; // to test girth
	private final String type_invalid = "express";
	private final String from_invalid = "aaabbb";
	private final String to_invalid = "aaabbb";

	
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
    	
    	assertEquals("Usage: parcelmail from to len width height weight type", outContent.toString());
    }
    
    @Test
    public void less_7args() {
    	String args[] = {from, to, len, width, height};
    	ParcelMail.main(args);
    	
    	assertEquals("Usage: parcelmail from to len width height weight type", outContent.toString());
    }
    
    @Test
    public void more_7args() {
    	String additional = "additional Info";
    	String args[] = {from, to, len, width, height, weight, type_regular, additional};
    	ParcelMail.main(args);
    	
    	assertEquals("Usage: parcelmail from to len width height weight type", outContent.toString());
    }
    
    @Test
    public void length_out_of_range_low() {
    	String args[] = {from, to, len_low, width, height, weight, type_regular};
    	ParcelMail.main(args);
    	
    	assertEquals("Usage: parcelmail from to len width height weight type \nlen >= 10",outContent.toString());
    	
    }
    
    @Test
    public void length_out_of_range_high() {
    	String args[] = {from, to, len_high, width, height, weight, type_regular};
    	ParcelMail.main(args);
    	
    	assertEquals("Usage: parcelmail from to len width height weight type \nlen <= 200",outContent.toString());
    	
    }
    
    @Test
    public void width_out_of_range_low() {
    	String args[] = {from, to, len, width_low, height, weight, type_regular};
    	ParcelMail.main(args);
    	
    	assertEquals("Usage: parcelmail from to len width height weight type \nwidth >= 10",outContent.toString());
    	
    }
    
    @Test
    public void width_out_of_range_high() {
    	String args[] = {from, to, len, width_high, height, weight, type_regular};
    	ParcelMail.main(args);
    	
    	assertEquals("Usage: parcelmail from to len width height weight type \nwidth <= 200",outContent.toString());
    	
    }
    
    @Test
    public void height_out_of_range_low() {
    	String args[] = {from, to, len, width, height_low, weight, type_regular};
    	ParcelMail.main(args);
    	
    	assertEquals("Usage: parcelmail from to len width height weight type \nheight >= 0.1",outContent.toString());
    	
    }
    
    @Test
    public void height_out_of_range_high() {
    	String args[] = {from, to, len, width, height_high, weight, type_regular};
    	ParcelMail.main(args);
    	
    	assertEquals("Usage: parcelmail from to len width height weight type \nheight <= 200",outContent.toString());
    	
    }
    
    @Test
    public void weight_out_of_range_high() {
    	String args[] = {from, to, len, width, height, weight_high, type_regular};
    	ParcelMail.main(args);
    	
    	assertEquals("Usage: parcelmail from to len width height weight type \nweight <= 30",outContent.toString());
    	
    }
    
    @Test
    public void girth_out_of_range_high() {
    	String args[] = {from, to, len, width_max, height_max, weight, type_regular};
    	ParcelMail.main(args);
    	
    	assertEquals("Usage: parcelmail from to len width height weight type \ngirth <= 300",outContent.toString());
    	
    }
    
    @Test
    public void type_invalid() {
    	String args[] = {from, to, len, width, height, weight, type_invalid};
    	ParcelMail.main(args);
    	
    	assertEquals("Usage: parcelmail from to len width height weight type \ntype = {regular, xpresspost,priority}",outContent.toString());
    	
    }
    
    @Test
    public void from_invalid() {
    	String args[] = {from_invalid, to, len, width, height, weight, type_regular};
    	ParcelMail.main(args);
    	
    	assertEquals("Usage: parcelmail from to len width height weight type \nfrom: use valid postal code",outContent.toString());
    	
    }
    
    @Test
    public void to_invalid() {
    	String args[] = {from, to_invalid, len, width, height, weight, type_regular};
    	ParcelMail.main(args);
    	
    	assertEquals("Usage: parcelmail from to len width height weight type \nto: use valid postal code",outContent.toString());
    	
    }
    
    @Test
    public void regular_rate() {
    	String args[] = {from, to, len, width, height, weight, type_regular};
    	ParcelMail.main(args);
    	
    	assertEquals("14.81",outContent.toString());
    	
    }
    
    @Test
    public void xpresspost_rate() {
    	String args[] = {from, to, len, width, height, weight, type_xpresspost};
    	ParcelMail.main(args);
    	
    	assertEquals("17.4",outContent.toString());
    	
    }
    
    @Test
    public void priority_rate() {
    	String args[] = {from, to, len, width, height, weight, type_priority};
    	ParcelMail.main(args);
    	
    	assertEquals("29.15",outContent.toString());
    	
    }

}
