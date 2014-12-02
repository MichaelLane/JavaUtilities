package SystemOutPrinting;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class ModularPrinting {
    
    private String modularMessage;
    
    private String otherMessage;
    
    private int mod;
    
    public ModularPrinting(String modularMessage, String otherMessage, int mod) {
        this.modularMessage = modularMessage;
        this.otherMessage = otherMessage;
        this.mod = mod;
    }
    
    public void print(int i) {
        if (i % mod == 0) {
            System.out.print(modularMessage);
        } else {
            System.out.print(otherMessage);
        }
    }
}
