public class Prime{
    public static void main(String[] args) {
        int n=17;
        boolean isPrime=true;
        if(n<=1){
            isPrime=false;
        }else{
            for(int i=2;i<Math.sqrt(n);i++){
                if(n%i==0){
                    isPrime=false;
            }
        }
        }
        if(isPrime){
            System.out.println("Prime");
        }else{
            System.out.println("Not prime");
        }
        
    }
  
    }
