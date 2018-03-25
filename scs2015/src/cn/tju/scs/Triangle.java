package cn.tju.scs;

public class Triangle {
	//0表示不是三角形 1 意味着是普通三角形 2表示等腰三角形 3表示等边三角形
	public int check(int a,int b,int c){
		if(a<=0 || b<=0 || c<=0 ||(a+b<=c || a+c<=b || b+c<=a)) return 0;
		if( a==b && b==c) return 3;
		if(a==b || b==c || a==c) return 2;	
		return 1;
	}
}
