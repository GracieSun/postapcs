
/**
 * Write a description of class Questions here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Questions {
    private String definition;
    private String term;
    public Questions(String term, String definition) {
        this.definition = definition;
        this.term = term;
    }
    
    public String getTerm() {
        return this.term;
    }
    
    public String getDefinition() {
        return this.definition;
    }
    
    public String toString() {
        return term;
    }
}