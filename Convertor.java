// Addison DeBlieux
// 893178845

package project3csc4101;

import java.util.*;

public class Convertor
{
    
    public static void prefixToInfix(String Expression)
    {
        
        if(Expression.length() == 0)
        {
            System.out.println("[*]Error: Invalid Input");
            return;
        }
        else
        {
            Stack<String> stack = new Stack<>();
            Stack<Integer> stackEval = new Stack<>();
            String[] str_array = Expression.split(" ");
            
            for (int i = 0; i < str_array.length / 2; i++)
            {
                String tmp = str_array[i];
                str_array[i] = str_array[str_array.length - 1 - i];
                str_array[str_array.length - 1 - i] = tmp;
            }

            for(String str : str_array)
            {
                if(isNumeric(str))
                {
                    stackEval.push(Integer.parseInt(str));
                    stack.push(str);
                }
                else
                {
                    int i = stackEval.pop();
                    int j = stackEval.pop();
                    String k = stack.pop();
                    String l = stack.pop();
                    switch(str)
                    {
                        case "+": 
                            stackEval.push(i + j);
                            stack.push("(" + k + " + " + l + ")");
                            break;
                        case "-": 
                            stackEval.push(i - j);
                            stack.push("(" + k + " - " + l + ")");
                            break;
                        case "/": 
                            stackEval.push(i / j);
                            stack.push("(" + k + " / " + l + ")");
                            break;
                        case "*": 
                            stackEval.push(i * j);
                            stack.push("(" + k + " * " + l + ")");
                            break;
                        case "%":
                            stackEval.push(i % j);
                            stack.push("(" + k + " % " + l + ")");
                            break;
                        default:
                            System.out.println("[*]Error: Invalid Input. Try Again");
                            return;
                    }
                }
            } 
            String tmpStr = stack.pop();
            System.out.println(Expression + " --> " + tmpStr.substring(1, tmpStr.length() - 1) + " = " + stackEval.pop());
        }
    }
    
    private static boolean isNumeric(String str)
    {
        try {
            Integer.parseInt(str);
        } 
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
