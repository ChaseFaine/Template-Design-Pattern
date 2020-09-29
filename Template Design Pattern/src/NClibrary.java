import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NClibrary extends Library {
    // Returns NC related suggestions.
    protected String getSuggestion(String item) {
	    System.out.println("looking up suggestions...");
	    return "NC-centric suggested items...";
    }
    
    // Sets the date format to American style.
    protected SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    }
    
    // Sets the duration to 14.
    protected int getDuration(Patron p) {
	    return p.ckoutDuration();
    }
}
