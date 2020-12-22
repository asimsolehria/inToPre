
package intopre;

import java.util.Stack;

/**
 *
 * @author asim
 */

public class InToPre 
{
    
    private String reverse(String str) 
    {
        if ((null == str) || (str.length() <= 1)) {
            return str;
        }
        return reverse(str.substring(1)) + str.charAt(0);
    }
    
    
    private int Prec(char op)
    {
        switch(op)
        {
            case '+','-' -> {
                return 1;
            }
            case '*','/' -> {
                return 2;
            }
            case '^' -> {
                return 3;
            }
        }
        return -1;
    }
    

        public String inToPos(String exp)
    {
        Stack<Character> stack=new Stack<>();
        String result="";
        for (int i = 0; i < exp.length(); i++) 
        {
           char c=exp.charAt(i);
           
           if(Character.isLetterOrDigit(c))
               result+=c;
           else if (c=='(')
               stack.push(c);
           else if(c==')')
           {
               while (!stack.isEmpty() && stack.peek()!='(') 
                   result+=stack.pop();
               stack.pop();
           } 
           else
           {
               while(!stack.isEmpty() && Prec(c)<=Prec(stack.peek()))
                   result+=stack.pop();
               stack.push(c);
           }
        }
        while (!stack.isEmpty()) 
        {
            result+=stack.pop();
        }
        return result;
    }
    
    
    
    public  String inToPre(String exp)
    {
        Stack<String> stack=new Stack<>();
        
        String rev=reverse(exp);
        char arr[]=rev.toCharArray();
        
        for (int i = 0; i <arr.length; i++) 
        {
            if(arr[i]=='(')
                arr[i]=')';
            else if(arr[i]==')')
                arr[i]='(';
        }
        
        rev=new String(arr);
        rev=inToPos(rev);
        rev=reverse(rev);
        return rev;
    }
}
