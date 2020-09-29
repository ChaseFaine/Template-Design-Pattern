import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EUlibrary extends Library {
    // Returns EU related suggestions.
    protected String getSuggestion(String item) {
	    System.out.println("looking up suggestions...");
	    return "EU-centric suggested items...";
    }
    
    // Sets the date format to European style.
    protected SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }
    
    // Sets the duration to 10 instead of 14.
    protected int getDuration(Patron p) {
	    return p.ckoutDuration() - 4;
    }
}
