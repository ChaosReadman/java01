public class rectangle extends shape
{
    private Integer w;
    private Integer h;
    rectangle(Integer x,Integer y,Integer w,Integer h){
        super(x,y);
        this.w = w;
        this.h = h;
    }
    void draw(){
        System.out.printf("矩形の描画(x,y,w,h) = (%d,%d,%d,%d)\r\n",super.x,super.y,this.w,this.h);
    }
}