public class CCIWk3 {

    static int[] array = {1,1,2,2,3,3,4,4,5,5,6,7,7,8,8,9,9};
    static String string = "Hello there children";

    public static void main(String[] args){
        Question1(array);
        Question2(string);
        Question3(array, 7);
    }

    public static void Question1(int[] array){
            for(int i = 0; i < array.length; i+=2){
                if(array[i] != array[i+1]){
                    System.out.println(array[i]);
                    break;
                }
            }
        }

    public static void Question2(String string){
        for(int i = 0;i<string.length();i++){
            string = string.substring(1 , string.length())+string.charAt(0);
        }
        System.out.println(string);
    }

    public static void Question3(int[] array, int k){
        
    }  
}
