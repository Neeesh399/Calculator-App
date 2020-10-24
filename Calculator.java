import java.util.Arrays;

public class Calculator{
   //The main function in which the basic string is input
   public static void main(String[] args){
      String m = "3 / /";
      String[] equation = pemdas(optimize(m));
      System.out.println(stringify(equation));
   }
   
   
   //What I am adjusting
   public static double factorial(String[] equationArr, double incrementingNum){
        
        
        int temp = Integer.parseInt(equationArr[0]);
        temp = temp-1;
        String[] incrementingArr = new String[1];
        incrementingArr[0] = temp + "";
        
        if (Integer.parseInt(equationArr[0]) <= 0){
            
            return incrementingNum;
        }
        else{
            
            incrementingNum = Integer.parseInt(equationArr[0]) * incrementingNum;
            return factorial(incrementingArr, incrementingNum);
        }
      
   }
   public static boolean factorialCheck(String[] equationArr){
        for (int i=0;i<equationArr.length; i++){
            if (equationArr[i].equals("!")){
                if (!equationArr[i-2].equals("-")){
                  return true;
                }
                else{
                  throw new IllegalArgumentException();
                }
            }
        }
        return false;
   }
   public static String[] factorialism(String[] equationArr){
        for (int i=0; i<equationArr.length; i++){
            if ( equationArr[i].equals("!") ){
                String[] temp = new String[1];
                for (int j=0; j<temp.length; j++ ){
                    temp[j] = equationArr[(i-1)+j];
                }
                for (int k=0; k<temp.length; k++ ){
                    //System.out.println(temp[k]);
                }

                float answer = (float)factorial(temp, 1);
                String[] answerArr = new String[equationArr.length];
                for (int b=0;b<answerArr.length;b++){
                    answerArr[b] = equationArr[b];
                }
                int posOfSign = findPosition(equationArr, "!");
                answerArr[posOfSign-1] = "B";
                answerArr[posOfSign] = answer+"";

                return basicMath(demagnetize(answerArr));
            }
        }
        return equationArr;
    }
   
   
   public static boolean containsOperators(String equationStr){
      String operators = "+-*/()^";
      for (int i = 0; i<operators.length(); i++){
         if ( equationStr.length() != 0 && equationStr.charAt(equationStr.length()-1) == (operators.charAt(i)) ){
            System.out.println("true");
            return true;
         }
      }
      return false;
   }
   
   public static String stringify(String[] equationArr){
      String answer = "";
      for (int i=0; i<equationArr.length; i++){
         answer = answer + equationArr[i];
      }
      
      return answer;
   }
   
   //Turns the strings into a an array of strings
   public static String[] optimize(String equationString){
      String[] temp = new String[equationString.length()];
      temp = equationString.split(" ");
      return temp;
   }
   //These methods exist to satisfy the basic mathematical equations
   
   //Addition
   public static double addition(String[] equationArr){
      double count = Double.parseDouble(equationArr[0]) + Double.parseDouble(equationArr[2]);
      return count;
   }
   //Subtraction
   public static double subtraction(String[] equationArr){
      
      System.out.println(equationArr[0]);
      
      if ( equationArr[0].equals("-") ){
         return 0 - Double.parseDouble(equationArr[1]);
      }
      else if ( equationArr[0].equals("") ){
         return 0 - Double.parseDouble(equationArr[2]);
      }
         
      return Double.parseDouble(equationArr[0]) - Double.parseDouble(equationArr[2]);
   }
   //Multipilaction
   public static double multiplication(String[] equationArr){
      double count = Double.parseDouble(equationArr[0]) * Double.parseDouble(equationArr[2]);
      return count;
   }
   //Division
   public static double division(String[] equationArr){
      double count = Double.parseDouble(equationArr[0]) / Double.parseDouble(equationArr[2]);
      return count;
   }
   //Exponents
   public static double exponent(String[] equationArr){
      double count = Math.pow(Double.parseDouble(equationArr[0]),Double.parseDouble(equationArr[2]));
      return count;
   }
   //Finds position of string "a" in the provided string array
   public static int findPosition(String[] equationArr, String a){
      for (int i=0;i<equationArr.length;i++){
         if ( equationArr[i].equals(a) ){
            return i;
         }
      }
      return -100;
   }
   
   //Finds position of opening parenthesis based on int "closingPosition" of closing parenthesis
   public static int findOpening(String[] equationArr, int closingPosition){
      int closingPos = (closingPosition-1);
      for (int i=closingPos; i>=0; i--){
         if (equationArr[i].equals("(") ){
            return i;
         }
      }
      return -1000;
   }
   
   public static boolean parenthesisCheck(String[] equationArr){
      for (int i=0; i<equationArr.length; i++){
         if (equationArr[i].equals(")") ){
            return true;
         }
      }
      return false;
   } 
   public static boolean exponentialCheck(String[] equationArr){
      for (int i=0; i<equationArr.length; i++){
         if (equationArr[i].equals("^") ){
            return true;
         }
      }
      return false;
   }

   //Removes "B" from all equation arrays entered into it
   public static String[] demagnetize(String[] equationArr){
      int count = 0;
      for (int i=0; i<equationArr.length; i++){
         if (equationArr[i].equals("B")){
            count++;
         }
      }
      
      String[] temp = new String[equationArr.length-count];

      int count2 = 0;
      for (int j=0;j<temp.length;j++) {
         while (count2 < equationArr.length){
            if (equationArr[count2].equals("B")){
               count2++;
            }
            else{
               temp[j] = equationArr[count2];
               count2++;
               break;
            }
         } 
      }
      
      return temp;
   }

   //Follows pemdas rules to solve an equation
   
   public static String[] pemdas(String[] equationArr){
      if (equationArr.length == 1){
         return equationArr;
      }
      else{
         if ( parenthesisCheck(equationArr) ){
            return parenthesis(equationArr);
         }
         else if ( exponentialCheck(equationArr) ){
            return exponential(equationArr);
         }
         else if (factorialCheck(equationArr)){
                    return factorialism(equationArr);
         }
         else{
            return basicMath(equationArr);
            
         }  
      }
   }
   
   public static String[] exponential(String[] equationArr){
      for (int i=0; i<equationArr.length; i++){
         if ( equationArr[i].equals("^") ){
            String[] temp = new String[3];
            for (int j=0; j<temp.length; j++ ){
               temp[j] = equationArr[(i-1)+j];
            }
            
            float answer = (float)exponent(temp);
            String[] answerArr = new String[equationArr.length];
            for (int b=0;b<answerArr.length;b++){
               answerArr[b] = equationArr[b];
            }
            int posOfSign = findPosition(equationArr, "^");
            answerArr[posOfSign-1] = "B";
            answerArr[posOfSign+1] = "B";
            answerArr[posOfSign] = answer+"";
            
            return basicMath(demagnetize(answerArr));
         }
      }
      return equationArr;
   }

   public static String[] parenthesis(String[] equationArr){
      int closing = findPosition(equationArr, ")");
      int opening = findOpening(equationArr, closing);
      
      if ( opening != 0 && !equationArr[opening-1].equals("*") && !equationArr[opening-1].equals("/") && !equationArr[opening-1].equals("+") && !equationArr[opening-1].equals("-") && !equationArr[opening-1].equals("^") && !equationArr[opening-1].equals(")") && !equationArr[opening-1].equals("(")){
         String[] temp = pemdas(Arrays.copyOfRange(equationArr, opening+1, closing));
         String answer = (Double.parseDouble(temp[0]) * Double.parseDouble(equationArr[opening-1])) + "";
         
         String[] answerArr = Arrays.copyOfRange(equationArr, 0, equationArr.length);
      
         for (int j=opening; j<=closing; j++){
         answerArr[j] = "B";
         }
         answerArr[opening-1] = "B";
         answerArr[closing] = answer;
      
         return pemdas(demagnetize(answerArr));
      }
      else if (equationArr.length <= 4){
         return equationArr;
      }
      else{
         String[] temp = pemdas(Arrays.copyOfRange(equationArr, opening+1, closing));
         String answer = temp[0];
         
         String[] answerArr = Arrays.copyOfRange(equationArr, 0, equationArr.length);
         
         for (int j=opening; j<=closing; j++){
            answerArr[j] = "B";
         }
         answerArr[closing] = answer;
      
         return pemdas(demagnetize(answerArr));
      }
   }  
   
   //Performs the basic mathematical operators (like the other basicMath but more useable?)
   public static String[] basicMath(String[] equationArr){
      for (int i=0; i<equationArr.length; i++){
         if ( equationArr[i].equals("*") ){
           
            //Creates a temporary list with the neccesary components 
            String[] temp = new String[3];
            for (int j=0; j<temp.length; j++ ){
               temp[j] = equationArr[(i-1)+j];
            }
            
            float answer = (float)multiplication(temp);
            String[] answerArr = new String[equationArr.length];
            for (int b=0;b<answerArr.length;b++){
               answerArr[b] = equationArr[b];
            }
            int posOfSign = findPosition(equationArr, "*");
            answerArr[posOfSign-1] = "B";
            answerArr[posOfSign+1] = "B";
            answerArr[posOfSign] = answer+"";
            
            return basicMath(demagnetize(answerArr));
         }
         else if ( equationArr[i].equals("/") ){
            String[] temp = new String[3];
            for (int j=0; j<temp.length; j++ ){
               temp[j] = equationArr[(i-1)+j];
            }
            
            float answer = (float)division(temp);
            String[] answerArr = new String[equationArr.length];
            for (int b=0;b<answerArr.length;b++){
               answerArr[b] = equationArr[b];
            }
            int posOfSign = findPosition(equationArr, "/");
            answerArr[posOfSign-1] = "B";
            answerArr[posOfSign+1] = "B";
            answerArr[posOfSign] = answer+"";
            
            return basicMath(demagnetize(answerArr));
         }
         else if ( equationArr[i].equals("+") ){
            String[] temp = new String[3];
            for (int j=0; j<temp.length; j++ ){
               temp[j] = equationArr[(i-1)+j];
            }
            
            float answer = (float)addition(temp);
            String[] answerArr = new String[equationArr.length];
            for (int b=0;b<answerArr.length;b++){
               answerArr[b] = equationArr[b];
            }
            int posOfSign = findPosition(equationArr, "+");
            answerArr[posOfSign-1] = "B";
            answerArr[posOfSign+1] = "B";
            answerArr[posOfSign] = answer+"";
            
            return basicMath(demagnetize(answerArr));
         }
         else if ( equationArr[i].equals("-") && i != 0){
            String[] temp = new String[3];
            
            for (int j=0; j<temp.length; j++ ){
               temp[j] = equationArr[(i-1)+j];
            }
            
            float answer = (float)subtraction(temp);
            String[] answerArr = new String[equationArr.length];
            for (int b=0;b<answerArr.length;b++){
               answerArr[b] = equationArr[b];
            }
            int posOfSign = findPosition(equationArr, "-");
            answerArr[posOfSign-1] = "B";
            answerArr[posOfSign+1] = "B";
            answerArr[posOfSign] = answer+"";
            
            return basicMath(demagnetize(answerArr));
         }
         else if ( equationArr[i].equals("-")){
            String[] temp = new String[2];
            
            for (int j=0; j<temp.length; j++ ){
               temp[j] = equationArr[(i)+j];
            }
            
            float answer = (float)subtraction(temp);
            String[] answerArr = new String[equationArr.length];
            for (int b=0;b<answerArr.length;b++){
               answerArr[b] = equationArr[b];
            }
            int posOfSign = findPosition(equationArr, "-");
            
            answerArr[posOfSign+1] = "B";
            answerArr[posOfSign] = answer+"";
            
            return basicMath(demagnetize(answerArr));
         }

      }
      return equationArr;
   }
//End of Calculator class   
}