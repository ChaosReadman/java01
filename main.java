import java.util.ArrayList;
import java.util.List;

class main {
	public static void main(String[] args) {
//         // クラスとオブジェクト
//         add b = new add();
//         Integer c = 0;
// //        c = b.add(10,20);
//         System.out.printf("c = %d\r\n",c);
//         b.print();
        
        // スタティッククラス
        // System.out.printf("math.add(100,200) = %d\r\n",math.add(100,200));
        // math.print();
        // System.out.printf("math.sub(1000,2000) = %d\r\n",math.sub(1000,2000));
        // math.print();

        // 継承
        // animal a = new animal();
        // dog d = new dog();
        // cat c = new cat();
        // a.shout();
        // d.shout();
        // c.shout();

        // 抽象クラス
        // List<shape> l = new ArrayList<>();
        // l.add(new rectangle(1000,1000,200,200));
        // l.add(new ellipse(1500,1500,400,300));
        // l.forEach(a->{a.draw();});

        // インターフェース
        catImpl c = new catImpl();
        dogImpl d = new dogImpl();
        System.out.println(c.shout());
        System.out.println(d.shout());

	}
}
