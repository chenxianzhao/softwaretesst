package cn.tju.scs;

public class Triangle {
	//0��ʾ���������� 1 ��ζ������ͨ������ 2��ʾ���������� 3��ʾ�ȱ�������
	public int check(int a,int b,int c){
		if(a<=0 || b<=0 || c<=0 ||(a+b<=c || a+c<=b || b+c<=a)) return 0;
		if( a==b && b==c) return 3;
		if(a==b || b==c || a==c) return 2;	
		return 1;
	}
}
