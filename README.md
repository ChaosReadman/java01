- Javaにおけるオブジェクト指向、継承、抽象クラス、インターフェースを説明します。

1. はじめに
    - 前提
        - Windows + WSLの環境でビルド環境を作ります。
    - vscodeをインストール
        - 説明は他に譲ります
    - jdkをインストール
        - vscodeを立ち上げ、wslに接続し、ターミナルを開いてください。
        - ターミナルから以下のコマンドを入れます。
            ```
            sudo apt install openjdk-21-jdk
            javac --version
            ```
            インストール途中でYes/Noと聞かれたときはとりあえずYesで続けてください。
            最後の１行でバージョンが確認できればOKです。

2. クラスの作成とインスタンス化
    - Javaにおけるオブジェクト指向はとは何を指すのかというとクラスであると言えます。以降の継承、抽象、インターフェースというものはクラスの特徴や性質の説明となります。
    - クラス定義はある機能を実装する際にひとまとまりにしたい変数やメソッドなどをまとめたもので、オブジェクトの設計図です。
    - 普通のクラスはインスタンス化しないと使えません。インスタンス化とはクラスが実体を持った状態です。
    - vscodeで打ち込みながらやってみましょう。
    - vscodeのターミナルから以下のように打ち込んでディレクトリを作成してください。
        ```
        mkdir java01
        ```
    - vscodeのファイルから、ディレクトリを開くで、java01を開いてください。
    - vscodeでctrl+nで新規ファイルを作成、名前を add.javaにしてください。
    - add.javaに以下を打ち込んでください。
        ```
        public class add{
            Integer a;
            Integer b;
            Integer add(Integer a, Integer b){
                this.a = a;
                this.b = b;
                return a+b;
            }
            void print(){
                System.out.printf("a = %d, b = %d\r\n",a,b);
            }
        }
        ```
    - vscodeのターミナルから以下のように打ち込みコンパイルしましょう。
        ```
        javac add.java
        ```
    - add.classが作成されればOKです。
    
    - これでは何も動きませんので、main.javaを作り、オブジェクトを作って実行してみます。
    - vscodeでctrl+nで新規ファイルを作成し、名前をmain.javaにして以下のように打ち込みます。
        ```
        class main {
            public static void main(String[] args) {
                add b = new add();
                Integer c = 0;
                c = b.add(10,20);
                System.out.printf("c = %d\r\n",c);
                b.print();
            }
        }
        ```
    - 続いてコンパイルします。
        ```
        javac main.java add.java
        ```
    - main.classが出来上がりますので実行します。
        ```
        java main
        ```
    - すると次のように出力されるはずです。
        ```
        c = 30
        a = 10, b = 20
        ```

    - ここまでのまとめ（クラス、オブジェクト、インスタンス化）
        - addクラスとmainクラスという二つのクラスを作りました。
        - Java の mainメソッドは特別で、プログラムはmainメソッドから始まります。このメソッドを持つクラスはエントリーポイントとしてプログラム実行時にインスタンス化されメインメソッドが呼び出されます。
        - mainメソッドではaddクラスをnewしてbに代入しています。この時bはaddクラスをインスタンス化した状態となっています。
        - addクラスの実体を持ったbは、addクラスのメソッドを実行できますので、c = b.add(10,20);とすることで、c に 10 + 20の結果が入り、c = 30となります。
        - クラスはオブジェクトの設計図であり、実体を持たないままだと使えません。実体を持たせるためにはnewする必要があります。（ただしstaticクラスはまた別）
        - 最後にmainメソッドではb.printを呼び出しています。bの中にはInteger aと Integer bが定義されていますが、addを呼び出したときにaとbが初期化されますので、printしたときにはa = 10, b = 20と表示されます。
        - クラスを作る際には一連の機能とそれに関わる変数などをまとめクラスにすると良いでしょう。この考え方がオブジェクト指向なのですが、クラスとインスタンス化がわかっただけではまだオブジェクト指向の出だしでしかありません。以降の説明を続けて読んでください。
        - このような継承の仕組みはオブジェクト指向の元となっていて、Windowsでは、CRadio、CButtonなどのコントロールはCWndから派生しています。つまり、Windowsにおいてこれらのコントロールはすべてウインドウであり、ウインドウにウインドウを貼り付けて画面が構成されているためまさにWindowsといえます。

    - 少し修正してみましょう。mainのb.add呼び出しをコメントアウトしてコンパイルし、実行してみてください。結果は次のようになります。
        ```
        c = 0
        a = null, b = null
        ```
    - nullです。これでは少しまずいので、コンストラクタで初期化する処理を追加しましょう。
        ```
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
        ```
    - もう一度コンパイルして実行してみてください。出力は以下のようになります。
        ```
        c = 0
        a = 0, b = 0
        ```
    - コンストラクタとはクラスのインスタンス作成時に自動的に実行されるメソッドで、クラス名と同じメソッド名で、戻り値の無い特別なメソッドです。ここでは、addコンストラクタでaとbの初期化をしていますので、先ほどのようにnullになりません。
        
3. スタティッククラス
    - 次にインスタンス化しなくても使えるスタティッククラスを説明します。
    - vscodeでmath.javaを新規作成します。
        ```
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
        ```
    - 続いてmainを修正します。
        ```
        class main {
            public static void main(String[] args) {
                // クラスとオブジェクト
                // add b = new add();
                // Integer c = 0;
                // c = b.add(10,20);
                // System.out.printf("c = %d\r\n",c);
                // b.print();
                
                // スタティッククラス
                System.out.printf("math.add(100,200) = %d\r\n",math.add(100,200));
                math.print();
                System.out.printf("math.sub(1000,2000) = %d\r\n",math.sub(1000,2000));
                math.print();
            }
        }
        ```
    - 出力は以下のようになります。
        ```
        math.add(100,200) = 300
        a = 100, b = 200
        math.sub(1000,2000) = -1000
        a = 1000, b = 2000
        ```
    - スタティッククラスのまとめ
        - インスタンス化せずとも呼び出せます。
        - 中身はすべてstatic修飾子をつける必要があります。
        - thisは使えません（インスタンス化されていないため）ので、クラス名ドット〇〇でアクセスします。(math.a, math.bの部分)
        - staticクラスは、文字列等のユーティリティ的なクラス（たとえば、和暦西暦変換とか、タイトル文字を作るとか）をまとめておいて、インスタンス化せずとも様々なクラスから使えるようにすると言う使い方をします。
4. 継承
    - 継承とは、元クラスから派生クラスを作ることです。
    - vscodeでctrl+nからanimal.javaを作成しましょう。
        ```
        public class animal
        {
            void shout(){
                System.out.println("あにまーる！");
            }
        }
        ```
    - 次にdog.javaを作ります。
        ```
        public class dog extends animal{
            @Override
            void shout(){
                System.out.println("わんわん！");
            }
        }
        ```
    - ついでにcat.javaも作ります。
        ```
        public class cat extends animal
        {
            @Override
            void shout(){
                System.out.println("にゃー！");
            }
        }
        ```
    - 呼び出すmain.javaも修正しましょう。
        ```
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
                animal a = new animal();
                dog d = new dog();
                cat c = new cat();
                a.shout();
                d.shout();
                c.shout();
            }
        }
        ```
    - コンパイルします。
        ```
        javac main.java animal.java dog.java cat.java
        ```
    - 実行します。
        ```
        java main
        ```
    - 実行結果は以下のようになるでしょう。
        ```
        あにまーる！
        わんわん！
        にゃー！
        ```
    - 継承のまとめ
        - animalクラスを継承してdogクラスとcatクラスを作りました。その際、@Overrideによって、shoutメソッドをオーバーライド（上書き）しています。
        - 継承ではベースクラスに基本的な処理を書き、それを継承して別の処理を加えることができます。また、ベースクラスのメソッドを上書きも出来ます。

5. 抽象クラス
    - 続いて抽象クラスですが、これも継承をしますが、抽象化も行うことでポリモーフィズムという考え方を実現できます。
    - vscodeでshape.javaを作りましょう。
        ```
        abstract  class shape
        {
            Integer x;
            Integer y;
            shape(Integer x,Integer y){
                this.x = x;
                this.y = y;
            }
            abstract void draw();
        }
        ```
    - shapeを継承したrectangle.javaを作ります。
        ```
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
        ```
    - shapeを継承したellipse.javaを作ります。
        ```
        public class ellipse extends shape
        {
            private Integer w;
            private Integer h;
            ellipse(Integer x,Integer y,Integer w,Integer h){
                super(x,y);
                this.w = w;
                this.h = h;
            }
            void draw(){
                System.out.printf("楕円の描画(x,y,w,h) = (%d,%d,%d,%d)\r\n",super.x,super.y,this.w,this.h);
            }
        }
        ```
    - 最後に呼び出す部分を作ります。
        ```
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
                List<shape> l = new ArrayList<>();
                l.add(new rectangle(1000,1000,200,200));
                l.add(new ellipse(1500,1500,400,300));
                l.forEach(a->{a.draw();});
            }
        }
        ```
    - コンパイルしましょう。
        ```
        javac main.java shape.java rectangle.java ellipse.java
        ```
    - 実行します。
        ```
        java main
        ```
    - 結果は以下のようになります。
        ```
        矩形の描画(x,y,w,h) = (1000,1000,200,200)
        楕円の描画(x,y,w,h) = (1500,1500,400,300)
        ```
    - 抽象クラスのまとめ
        - 抽象クラスはインスタンス化して使うクラスではなく、継承して使うクラスです。
        - 通常の継承とは異なり@Overrdideせずとも同じメソッドを上書きできます。
        - ポリモーフィズムでは、同じメソッド名を複数のクラスで使えます。
        - デザインパターンのCompositteパターンを実装する際に基底クラスのListとして追加可能です。(main.javaを見てください)ちなみにCompositteパターンはGodotゲームエンジンでも使われているデザインパターンで、ゲームエンジンではかなり昔からある手法です。
        - 今回はCompositteパターンを実装するため、ListとArrayListをインポートしています。

6. インターフェース（その１）
    - インターフェースは「共通インターフェース」といった考え方を実装するためのオブジェクトです。データと実装するべきメソッドを定義しておいてそれを様々なクラスで使いまわすといった使い方をします。
    - vscodeでIAnimal.javaを作ります。
        ```
        interface IAnimal{
            String shout();
        }
        ```
    - vscodeでdocImpl.javaを作ります。
        ```
        class dogImpl implements IAnimal
        {
            public String shout()
            {
                String s = "わん";
                return s;
            }
        }
        ```
    - vscodeでcatImpl.javaを作ります。
        ```
        class catImpl implements IAnimal
        {
            public String shout()
            {
                String s = "にゃー";
                return s;
            }
        }
        ```
    - main.javaを修正します。
        ```
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
        ```
    - コンパイルします。
        ```
        javac main.java catImpl.java dogImpl.java IAnimal.java
        ```
    - 出力は以下のようになります
        ```
        にゃー
        わん
        ```

    - インターフェース（その１）まとめ
        - インターフェースはメソッドやメンバーだけを記述しますが、メソッドの具体的な実装は書きません。
        - クラスでインターフェースの実装をしますが、継承ではなく、実装ですのでextendsではなくimplementsを使います。実装であるため、インターフェースに記述しているメソッドは絶対に実装しなければなりません。
        - （次の章でやりますが）複数のインターフェースを実装できます。（クラス継承では多重継承はできません）
        - javaSpringのRepositoryはインターフェースで実装されます。JavaSpringの講義ではないので、コード例をここに記しますがコンパイル等はしません。
            ```
            // Repositoryインターフェース
            public interface UserRepository {
                User findById(Long id);
                void save(User user);
                // ...他の操作
            }
            // MySQL実装
            public class MySQLUserRepository implements UserRepository {
                // MySQLデータベースへの接続
                @Override
                public User findById(Long id) {
                    // MySQLでユーザーを検索
                }
                @Override
                public void save(User user) {
                    // MySQLにユーザーを保存
                }
                // ...
            }

            // SQLite実装
            public class SQLiteUserRepository implements UserRepository {
                // SQLiteデータベースへの接続
                @Override
                public User findById(Long id) {
                    // SQLiteでユーザーを検索
                }
                @Override
                public void save(User user) {
                    // SQLiteにユーザーを保存
                }
                // ...
            }
            ```
            このように、Repositoryインターフェースを定義することで、ドメインロジックは UserRepository インターフェースを通じてデータアクセスを行うため、MySQLまたはSQLiteなどの具体的な実装を意識せずに、柔軟なデータアクセス層を構築できます。

7. インターフェース（その２）
    - つぎに、インターフェースを複数つくり、それをImplementsしてみましょう。
    - vscodeでIRun.javaを作ります
        ```
        public interface IRun{
            public abstract boolean Runnable();
        }
        ```
    - vscodeでIFly.javaを作ります
        ```
        public interface IFly{
            public abstract boolean Flyable();
        }
        ```
    - vscodeでCheetah.javaを作ります
        ```
        public class Cheetah implements IRun,IFly
        {
            public String Name = "Cheetah";
            public boolean Runnable(){ return true;}
            public boolean Flyable(){ return false;}
        }  
        ```
    - 呼び出してみましょう
        ```
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

                // // インターフェース（その１）
                // catImpl c = new catImpl();
                // dogImpl d = new dogImpl();
                // System.out.println(c.shout());
                // System.out.println(d.shout());

                // インターフェース（その２）
                Cheetah c = new Cheetah();
                System.out.printf("%s's Runnable = %s and Flyable = %s\r\n",c.Name, c.Runnable(), c.Flyable());
                IRun r = new Cheetah();
                System.out.printf("In case of IRun r = new Cheetah(), Runnable = %s\r\n",r.Runnable());
                IFly f = new Cheetah();
                System.out.printf("In case of IFly f = new Cheetah(), Flyable = %s\r\n",f.Flyable());

            }
        }
        ```

        - 出力は次のようになります
            ```
            Cheetah's Runnable = true and Flyable = false
            In case of IRun r = new Cheetah(), Runnable = true
            In case of IFly f = new Cheetah(), Flyable = false
            ```

        - インターフェース（その２）まとめ
            - インターフェースは複数継承できます。
            - 継承元のインターフェースクラス名を型として扱うことができます。

8. 最後に
    - 取り急ぎまとめたもので、間違い等あるかもしれませんがご容赦ください。
    - ご意見等ありましたら、お気軽にご連絡ください。
