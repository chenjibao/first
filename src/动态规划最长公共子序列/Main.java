/**
 * 
 */
package 动态规划最长公共子序列;

import java.util.Scanner;
//github测试啊

/**
 *c[i][j] :用来存储序列Xi和Yi的最长公共子序列的长度,i,j分别为数组的下标
 *
 *c[i][j]=
 *0   							i=0,j=0
 *c[i-1][j-1]+1					i,j>0;xi==yi
 *max{c[i-1][j],c[i][j-1]}		i,j>0;xi!=yi
 *程序设计;
 *构造两个方法：
 *
 *求出[][]c数组和[][]b数组
 *LCSLength  参数：m(x序列的长度) n(y序列的长度)  []x  []y  [][]c   [][]b
 *首先要明确：如果c[i][j]是通过c[i-1][j-1]+1得到的，b[i][j]=1
 *		      如果c[i][j]是通过c[i-1][j]得到的，b[i][j]=2
 *		      如果c[i][j]是通过c[i][j-1]得到的，b[i][j]=3
 *传入的[][]c和[][]b 必须是行的长度为x.length+1  ,列的长度为y.length+1
 *
 *
 *输出最长公共子序列
 *LCS    参数：i(x序列的长度)  j(y序列的长度)  []x  [][]b
 *如果m,n==0,return
 *如果b[i][j]==1	 LCS[i-1][j-1]  s输出x[i]
 *如果b[i][j]==2	 LCS[i-1][j]  
 *如果b[i][j]==3	 LCS[i][j-1]
 *
 *主方法里面
 *先构造[][]c [][]b
 *然后调用LCS(m,n,x,b)
 *
 *输入：第一行   x序列的长度    y序列的长度
 *	     第二行  x序列  
 *	     第三行   y序列
 *输出：最长公共子序列
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int m=Integer.parseInt(sc.next());
		int n=Integer.parseInt(sc.next());
		
		String []x=new String[m+1];
		String []y=new String[n+1];
		
		for (int i = 1; i < x.length; i++) {
			x[i]=sc.next();
		}
		
		for (int i = 1; i < y.length; i++) {
			y[i]=sc.next();
		}
		
		int [][]c=new int[m+1][n+1];
		int [][]b=new int[m+1][n+1];
		
		LCSLength(m, n, x, y, c, b);
		LCS(m, n, x, b);
		
	}
//	7 6
//	A B C B D A B
//	B D C A B A
	
	public static void LCSLength(int m,int n,String[]x,String []y,int[][]c,int[][]b){
		for (int i = 1; i < m; i++) {
			for (int j = 1; j <n; j++) {
				if(x[i]==y[j]){
					c[i][j]=c[i-1][j-1]+1;
					b[i][j]=1;
				}else if(c[i-1][j]>=c[i][j-1]){
					c[i][j]=c[i-1][j];
					b[i][j]=2;
				}else{
					c[i][j]=c[i][j-1];
					b[i][j]=3;
				}
			}
		}
	}
	
	
	public static void LCS(int i,int j,String []x,int [][]b){
		if(i==0 || j==0){
			return ;
		}
		if(b[i][j]==1){
			LCS(i-1, j-1, x, b);
			System.out.print(x[i]+" ");
		}else if(b[i][j]==2){
			LCS(i-1, j, x, b);
		}else {
			LCS(i, j-1, x, b);
		}
	}
}
