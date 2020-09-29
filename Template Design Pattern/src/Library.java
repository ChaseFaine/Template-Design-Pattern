
import java.util.HashMap;
import java.text.SimpleDateFormat;  
import java.util.Date;  
/**
 * A template method is a design pattern that defines a skeleton/template
 * of an algorithm in a superclass but lets subclases overide steps
 * of the algorithm without changing its structure.
 */
public abstract class Library {

    private HashMap<String,Patron> patrons;

    public Library() {
    patrons = new HashMap<String,Patron>();
    }
    
    // Should be closed because it does not need to change for any reason. Same method works for EU and NC libraries.
    // Adds Patrons.
    final public void addPatron(String patronName) {
	patrons.put(patronName, new Patron(patronName));
    }
    
    // Should be closed because it does not need to change for any reason. Same method works for EU and NC libraries.
    // Checks Patron Accounts.
    final protected boolean checkPatronAccount(Patron p) {
	System.out.println("checking account...");
	return p.numCheckedOut() < 8;
    }
    
    /**
     * Algorithm Step.
     * Should be open for extension because Europeans use a different date format.
     * Sets the date format.
     */
    abstract protected SimpleDateFormat simpleDateFormat(); //REFACTOR: added this in so that we can add new date formats.

    /**
     * Algorithm step.
     * Should be open for extension because it should be able to return something other than NC related items for Europeans.
     * Returns suggestions.
     */
    abstract protected String getSuggestion(String item); //REFACTOR to abstract so that subclass can override.
    
    /**
     * Algorithm step.
     * Puts everything together in a receipt.
     */
    final protected void printReceipt(Patron p, String item, int days, String suggestion) {
	SimpleDateFormat formatter = simpleDateFormat(); //REFACTOR so that we can set different date formats. 
	Date date = new Date();
	String datetimeString = formatter.format(date);  	
	
	System.out.println("--------------------------------------");
	System.out.println("--  L I B R A R Y     R E C E I P T --");
	System.out.println();
	System.out.println(" " + datetimeString);
	System.out.println(" Patron: " + p.getName());
	System.out.println(" Item: " + item);
	Date due = new Date(date.getTime()+(days*24*60*60*1000));
	formatter = simpleDateFormat(); //REFACTOR so that we can set different date formats.
	datetimeString = formatter.format(due);  	
	System.out.println(" Due: " + datetimeString);
	System.out.println();
	System.out.println(" Suggested title:");
	System.out.println("   " + suggestion);
	System.out.println("--------------------------------------");
    }
    
    /**
     * Algorithm Step.
     * Should be open for extenstion because Europeans want a different duration than NC.
     */
    abstract protected int getDuration(Patron p); //REFACTOR to abstract so that we can change duration.
    
    // Algorithm.
    // Performs the checkout. 
    final public void checkout(String item, String name) {
	Patron p = patrons.get(name);
    
	boolean okayToCheckout = checkPatronAccount(p);

	if (okayToCheckout) {
	    int days = getDuration(p);
	    p.checkout(item);
	    String s = getSuggestion(item);
	    printReceipt(p, item, days, s);
	}
    }
}
