输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
import java.util.*;
public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res=new ArrayList<>();
        if(input.length==0||k==0||k>input.length) return res;
        PriorityQueue<Integer> queue=new PriorityQueue<>((a,b)->b-a);
        for(int i=0;i<k;i++){
            queue.offer(input[i]);
        }
        for(int i=k;i<input.length;i++){
            if(input[i]<queue.peek()){
                queue.poll();
                queue.offer(input[i]);
            }
        }
        
        
        while(!queue.isEmpty()){
            res.add(queue.poll());
        }
        return res;
    }
}

HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量
的最大和,当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。给一个数组，返回它的最大连续子序列的和，
你会不会被他忽悠住？(子向量的长度至少是1)
public class Solution {
    public int FindGreatestSumOfSubArray(int[] array) {
        int max=array[0];
        int sum=Integer.MIN_VALUE;
        for(int num:array){
            
            if(sum>0){
                sum+=num;
            }else{
                sum=num;
            }
            
            max=Math.max(max,sum);
        }
        return max;
    }
}


求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13
因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现
的次数（从1 到 n 中1出现的次数）。
public class Solution {
    public int NumberOf1Between1AndN_Solution(int n) {
        int res=0;
        int high=0;
        int cur=0;
        int low=0;
        int index=1;
        while(n/index!=0){
            high=n/(index*10);
            cur=n/index%10;
            low=n%index;
            if(cur<1) res+=high*index;
            if(cur==1) res+=high*index+low+1;
            if(cur>1) res+=(high+1)*index;
            index*=10;
        }
        return res;
    }
}

输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组
{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
import java.util.*;

public class Solution {
    public String PrintMinNumber(int [] numbers) {
        String[] str=new String[numbers.length];
        for(int i=0;i<numbers.length;i++){
            str[i]=String.valueOf(numbers[i]);
        }
        Arrays.sort(str,(a,b)->((a+b).compareTo(b+a)));
        StringBuilder sb=new StringBuilder();
        for(String s:str){
            sb.append(s);
        }
        return new String(sb);
    }
}


把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 
习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
public class Solution {
    public int GetUglyNumber_Solution(int index) {
        if(index<1) return 0;
        int p2=0;
        int p3=0;
        int p5=0;
        int[] dp=new int[index];
        dp[0]=1;
        for(int i=1;i<index;i++){
            dp[i]=Math.min(2*dp[p2],Math.min(3*dp[p3],5*dp[p5]));
            if(dp[i]==2*dp[p2]) p2++;
            if(dp[i]==3*dp[p3]) p3++;
            if(dp[i]==5*dp[p5]) p5++;
        }
        return dp[index-1];
    }
}


在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 
如果没有则返回 -1（需要区分大小写）.（从0开始计数）
public class Solution {
    public int FirstNotRepeatingChar(String str) {
        int[] arr=new int[128];
        for(int i=0;i<str.length();i++){
            arr[str.charAt(i)]++;
        }
        for(int i=0;i<str.length();i++){
            if(arr[str.charAt(i)]==1) return i;
        }
        return -1;
    }
}

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
并将P对1000000007取模的结果输出。 即输出P%1000000007
public class Solution {
    private int res;
    private int mod=1000000007;
    public int InversePairs(int [] array) {
        mergeSort(array,0,array.length-1);
        return res;
    }
    private void mergeSort(int[] array,int left,int right){
        if(left>=right) return ;
        int mid=left+(right-left)/2;
        mergeSort(array,left,mid);
        mergeSort(array,mid+1,right);
        merge(array,left,mid,right);
    }
    private void merge(int[] array,int left,int mid,int right){
        int i=left;
        int j=mid+1;
        int len=right-left+1;
        int[] tmp=new int[len];
        int k=0;
        while(i<=mid&&j<=right){
            if(array[i]<=array[j]){
                res=(res+j-mid-1)%mod;
                tmp[k++]=array[i++];
            }else{
                tmp[k++]=array[j++];
            }
        }
        while(i<=mid){
            res=(res+right-mid)%mod;
            tmp[k++]=array[i++];
        }
        while(j<=right){
            tmp[k++]=array[j++];
        }
        System.arraycopy(tmp,0,array,left,len);
    }
}






