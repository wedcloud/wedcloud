package club.wedcloud.www;

public class BaseTest {
    public static void main(String[] args) {
        /*
         byte
         byte数据类型是8位、有符号，以二进制补码表示的整数
         最小值 -128 （-2^7）
         最大值 127（2^7-1）
         默认值是 0
         byte 类型用在大型数组中节约空间，主要代替整数，因为 byte 变量占用的空间只有 int 类型的四分之一
         例子：byte a=100, byte b = -50
         */
        System.out.println("Byte 最大值"+Byte.MAX_VALUE);
        System.out.println("Byte 最小值"+Byte.MIN_VALUE);

        /*
         short
         short数据类型是16位、有符号的以二进制补码表示的整数
         最大值  -32768（-2^15）
         最小值 32767（2^15 - 1）
         Short 数据类型也可以像 byte 那样节省空间。一个short变量是int型变量所占空间的二分之一
         默认值 0
         例子：short s =1000, short r = -20000
         */
        System.out.println("Short 最大值"+Short.MAX_VALUE);
        System.out.println("Short 最小值"+Short.MIN_VALUE);

        /*
         int
         int 数据类型是32位、有符号的以二进制补码表示的整数
         最小值 -2,147,483,648（-2^31）
         最大值 2,147,483,647（2^31 - 1）
         一般的整型变量默认是 int 类型
         默认值 0
         例子： int a =100000,int b= -200000
         */
        System.out.println("Integer 最大值"+Integer.MAX_VALUE);
        System.out.println("Integer 最小值"+Integer.MIN_VALUE);

        /*
         long
         long 数据类型是64位、有符号的以二进制补码表示的整数
         最小值 -9,223,372,036,854,775,808（-2^63）
         最大值 9,223,372,036,854,775,807（2^63 -1）
         这种类型主要使用在需要比较大整数的系统上
         默认值是0L
         例子：ong a = 100000L，Long b = -200000L。 "L"理论上不分大小写，但是若写成"l"容易与数字"1"混淆，不容易分辩。所以最好大写
         */
        System.out.println("Long 最大值"+Long.MAX_VALUE);
        System.out.println("Long 最小值"+Long.MIN_VALUE);

        /*
         float
         float 数据类型是单精度、32位、符合IEEE 754 标准的浮点数
         float 在存储大型浮点数组的时候可以节省内存空间
         默认值是0.0f
         浮点数不能用来表示精确的值，例如货币
         例子：float f = 234.5f
         */
        System.out.println("Float 最大值"+Float.MAX_VALUE);
        System.out.println("Float 最小值"+Float.MIN_VALUE);

        /*
         double
         double数据类型是6双精度数、64位、符合IEEE 754 变准的浮点数
         浮点数默认类型是double类型
         double类型同样不能表示精确的值，例如货币
         默认值0.0d
         例子： double d =12.4
         */
        System.out.println("Double 最大值"+Double.MAX_VALUE);
        System.out.println("Double 最小值"+Double.MIN_VALUE);

        /*
         boolean
         boolean 数据类型表示一位的信息
         只取两个值：TRUE 和 FALSE
         这种类型只作为一种标记来记录true/false情况
         默认值是 false
         例子： boolean b = true
         */
        System.out.println("Boolean FALSE"+Boolean.FALSE);
        System.out.println("Boolean TRUE"+Boolean.TRUE);

        /*
         char
         char类型是一个单一的16位Unicode字符
         最小值 \u0000 (即为0)
         最大值 \uffff (即为 65535)
         char 数据类型可以储存任何字符
         例如：char c = 'A'
         */
        System.out.println("Character 最大值"+Character.MAX_VALUE);
        System.out.println("Character 最小值"+Character.MIN_VALUE);
    }
}