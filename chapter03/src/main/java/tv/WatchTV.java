package tv;

public  class WatchTV {
    public static void main( String[] args ) {
       TV tv = new TV(7, 20, false);  	
        
        tv.status();	
        
        tv.power(true);
        tv.volume(120); //120값으로 세팅
        tv.status();		          
        
        tv.volume(false); //현재 값에서 1칸 내린다.
        tv.status();
        
        tv.channel(0);
        tv.status();		          
        
        tv.channel(true);
        tv.channel(true);
        tv.channel(true);
        tv.status();

        tv.power(false);
        tv.status();      		          
    }
}
