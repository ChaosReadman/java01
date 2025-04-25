
public class add{
    Integer a;
    Integer b;
    Integer add(Integer a, Integer b){
        this.a = a;
        this.b = b;
        return a+b;
    }
    // コンストラクタで初期化してみる
    add(){
        a = 0;
        b = 0;
    }
    void print(){
        System.out.printf("a = %d, b = %d\r\n",a,b);
    }
}