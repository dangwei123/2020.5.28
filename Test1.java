统计一个数字在排序数组中出现的次数。
public class Solution {
    public int GetNumberOfK(int [] array , int k) {
        if(array.length==0) return 0;
        int left=0;
        int right=array.length-1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(array[mid]<k){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        if(array[left]!=k) return 0;
        int leftEdge=left;
        left=0;
        right=array.length-1;
        while(left<right){
            int mid=left+(right-left+1)/2;
            if(array[mid]>k){
                right=mid-1;
            }else{
                left=mid;
            }
        }
        return left-leftEdge+1;
    }
}

输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
public class Solution {
    public int TreeDepth(TreeNode root) {
        if(root==null) return 0;
        return 1+Math.max(TreeDepth(root.left),TreeDepth(root.right));
    }
}

输入一棵二叉树，判断该二叉树是否是平衡二叉树。

在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
public class Solution {
    public boolean IsBalanced_Solution(TreeNode root) {
        return isBalance(root)>=0;
    }
    private int isBalance(TreeNode root){
        if(root==null) return 0;
        int left=isBalance(root.left);
        int right=isBalance(root.right);
        if(left<0||right<0||Math.abs(left-right)>1) return -1;
        return 1+Math.max(left,right);
    }
}

一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果
public class Solution {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int tmp=0;
        for(int num:array){
            tmp^=num;
        }
        
        int flag=(tmp)&(-tmp);
        for(int num:array){
            if((num&flag)==0){
                num1[0]^=num;
            }else{
                num2[0]^=num;
            }
        }
    }
}

小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有
多少种连续的正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题
交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
import java.util.ArrayList;
public class Solution {
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res=new ArrayList<>();
       int[] pre=new int[sum+1];
        pre[0]=0;
        for(int i=1;i<=sum;i++){
            pre[i]=pre[i-1]+i;
        }
        for(int i=0;i<sum;i++){
            for(int j=i+1;j<sum;j++){
                if(pre[i]==pre[j]-sum){
                    ArrayList<Integer> path=new ArrayList<>();
                    for(int k=i;k<j;k++){
                        path.add(k+1);
                    }
                    res.add(path);
                }
            }
        }
        return res;
    }
}


输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。

import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> list=new ArrayList<>();
        int len=array.length;
        if(len<1||array[len-1]+array[len-2]<sum) return list;
        int[] res=new int[2];
        int min=Integer.MAX_VALUE;
        int left=0;
        int right=array.length-1;
        boolean flag=false;
        while(left<right-1){
            int s=array[left]+array[right];
            if(s==sum){
               flag=true;
                if(array[left]*array[right]<min){
                    res[0]=array[left];
                    res[1]=array[right];
                    min=array[left]*array[right];
                }
                left++;
                right--;
            }else if(s<sum){
                left++;
            }else{
                right--;
            }
        }
        if(flag){
            list.add(res[0]);
            list.add(res[1]);
        }
        return list;
    }
}


汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符
序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
是不是很简单？OK，搞定它！
public class Solution {
    public String LeftRotateString(String str,int n) {
        if("".equals(str)) return "";
        char[] chars=str.toCharArray();
        reverse(chars,0,chars.length-1);
        reverse(chars,0,chars.length-n-1);
        reverse(chars,chars.length-n,chars.length-1);
        return new String(chars);
    }
    private void reverse(char[] chars,int left,int right){
        while(left<right){
            char c=chars[left];
            chars[left++]=chars[right];
            chars[right--]=c;
        }
    }
}


牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，
有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
public class Solution {
    public String ReverseSentence(String str) {
        char[] chars=str.toCharArray();
        reverse(chars,0,chars.length-1);
        int left=0;
        for(int i=0;i<=chars.length;i++){
            if(i==chars.length||chars[i]==' '){
                reverse(chars,left,i-1);
                left=i+1;
            }
        }
        return new String(chars);
    }
    private void reverse(char[] chars,int left,int right){
        while(left<right){
            char c=chars[left];
            chars[left++]=chars[right];
            chars[right--]=c;
        }
    }
}

LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,
想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”
不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成
“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我
们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。

import java.util.Arrays;
public class Solution {
    public boolean isContinuous(int [] numbers) {
        if(numbers.length==0) return false;
        Arrays.sort(numbers);
        int count=0;
        int i=0;
        for(;i<5;i++){
            if(numbers[i]!=0) break;
            count++;
        }
        for(int j=i;j<5;j++){
           if(j!=i){
               if(numbers[j]==numbers[j-1]) return false;
               count-=(numbers[j]-numbers[j-1]-1);
            }
        }
        return count>=0;
    }
}


每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。其中,
有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出
列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最
后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？
(注：小朋友的编号是从0到n-1)
public class Solution {
    public int LastRemaining_Solution(int n, int m) {
        if(n==0||m==0) return -1;
        int res=0;
        for(int i=2;i<=n;i++){
            res=(res+m)%i;
        }
        return res;
    }
}



如果没有小朋友，请返回-1
