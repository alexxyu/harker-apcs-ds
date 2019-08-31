import java.util.ArrayList;

/**
 * OldMacDonaldsFarm manages all the farm animals and prints
 * the song.
 * 
 * @author Alex Yu
 * @version November 3, 2016
 */
public class OldMacDonaldsFarm
{
    private String farmerName;
    private ArrayList<Animal> farmAnimals;

    /**
     * Constructs an object of class OldMacDonaldsFarm
     */
    public OldMacDonaldsFarm()
    {
        farmerName = "Old MacDonald";
        farmAnimals = new ArrayList<Animal>( );
    }
    
    /**
     * Sings a verse of the song.
     */
    public void singVerse()
    {
        String phrase1 = farmerName + " had a farm,"; 
        String ei = " E-I-E-I-O";
        
        Animal a = farmAnimals.get(farmAnimals.size()-1);
        System.out.println(phrase1 + ei + " and on his farm he had some " + 
                           a.getCommonName() + "s," + ei);
        for(int index=farmAnimals.size()-1; index>=0; index--)
        {
            a = farmAnimals.get(index);
            
            System.out.println("With a " + a.speak()+"-"+a.speak() + " here, and a "
                                + a.speak()+"-"+a.speak() + " there,");
            System.out.println("Here a " + a.speak() + ", there a " + a.speak() +
                               ", everywhere a " 
                               + a.speak()+"-"+a.speak() + ",");                  
        }
        
        System.out.println(phrase1 + ei + ".\n\n\n");
    }

    /**
     * The main program for OldMacDonaldsFarm
     * 
     * @param args  information read from the command line
     */
    public static void main (String [ ] args)
    {
        OldMacDonaldsFarm singer = new OldMacDonaldsFarm( ); 
        singer.farmAnimals.add(new Chicken( )); 
        singer.singVerse( );
        singer.farmAnimals.add(new Chick()); 
        singer.singVerse( );
        singer.farmAnimals.add(new Rooster( )); 
        singer.singVerse( ); 
        singer.farmAnimals.add(new Pig( )); 
        singer.singVerse( );
    }
}
