public class math{
    static Integer a = 0;
    static Integer b = 0;
    static Integer add(Integer a, Integer b)
    {
        math.a = a;
        math.b = b;
        return a+b;
    }
    static Integer sub(Integer a, Integer b)
    {
        math.a = a;
        math.b = b;
        return a-b;
    }
    static void print(){
        System.out.printf("a = %d, b = %d\r\n",a,b);
    }
}