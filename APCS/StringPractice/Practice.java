public class Practice
{
    public Practice()
    {
        System.out.println(firstLastChar("pizza"));
        System.out.println(initials("Harry", "potter"));
        System.out.println(initialsUpper("Harry", "potter"));
        charLine("pizza");
        reverseCharLine("pizza");
        System.out.println(reverse("pizza"));
        System.out.println(countHo("Hohosantahofihi", true));
        System.out.println(countHo("Hohosantahofihi", false));
        
        System.out.println(createPlurals("happy"));
        System.out.println(createPlurals("cheese"));
        
        System.out.println(evenIndex("ap"));
        System.out.println(evenIndex("harker"));
        System.out.println(evenIndex("corns"));
        
        System.out.println(firstHalf("z"));
        System.out.println(firstHalf("wxyz"));
        System.out.println(firstHalf("uvwxyz"));
        
        System.out.println(secondHalf("z"));
        System.out.println(secondHalf("wxyz"));
        System.out.println(secondHalf("uvwxyz"));
        
        System.out.println(firstThird("Iliketohavebagels"));
        System.out.println(lastThird("Iliketohavebagels"));
        System.out.println(twoThirds("Iliketohavebagels"));
    }
    
    public String firstLastChar(String str)
    {
        String first = str.substring(0, 1);
        String last = str.substring(str.length()-1);
        
        return first + "\t" + last;
    }
    
    public String initials(String str1, String str2)
    {
        String firstInitial = str1.substring(0, 1);
        String secondInitial = str2.substring(0, 1);
        
        return firstInitial+"."+secondInitial;
    }
    
    public String initialsUpper(String str1, String str2)
    {
        String firstInitial = str1.substring(0, 1);
        String secondInitial = str2.substring(0, 1);
        
        return (firstInitial+"."+secondInitial).toUpperCase();
    }
    
    public void charLine(String str)
    {
        for(int i=0; i<str.length(); i++)
        {
            String eachChar = str.substring(i, i+1);
            System.out.println(eachChar);
        }
    }
    
    public void reverseCharLine(String str)
    {
        for(int i=str.length()-1; i>=0; i--)
        {
            String eachChar = str.substring(i, i+1);
            System.out.println(eachChar);
        }
    }
    
    public String reverse(String str)
    {
        String newString = "";
        for(int i=str.length()-1; i>=0; i--)
        {
            newString += str.substring(i, i+1);
        }
        
        return newString;
    }
    
    public int countHo(String str, boolean xmas)
    {
        int count = 0;
        if(xmas)
        {
            for(int i=0; i<str.length()-1; i++)
            {
                if(str.substring(i, i+2).equals("ho"))
                {
                    count++;
                }
            }
        }
        else
        {
            for(int i=0; i<str.length()-1; i++)
            {
                if(str.substring(i, i+2).equals("hi"))
                {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public String createPlurals(String str)
    {
        if(str.substring(str.length()-1).equals("y"))
        {
            return str.substring(0, str.length()-1) + "ies";
        }
        
        return str + "s";
    }
    
    public String evenIndex(String str)
    {
        String newString = "";
        for(int i=0; i<str.length(); i+=2)
        {
            newString += str.substring(i, i+1);
        }
        
        return newString;
    }
    
    public String firstHalf(String str)
    {
        if(str.length()<2) return str;
        
        int midIndex = (int) (str.length()/2);
        return str.substring(0, midIndex);
    }
    
    public String secondHalf(String str)
    {
        if(str.length()<2) return str;
        
        int midIndex = (int) (str.length()/2);
        return str.substring(midIndex);
    }
    
    public String firstThird(String str)
    {
        int thirdLength = (int) (str.length()/3);
        return str.substring(0, thirdLength);
    }
    
    public String lastThird(String str)
    {
        int thirdLength = (int) (str.length()/3);
        return str.substring(str.length()-thirdLength);
    }
    
    public String twoThirds(String str)
    {
        return lastThird(str) + firstThird(str);
    }
    
    public static void main(String[] args)
    {
        new Practice();
    }
}
