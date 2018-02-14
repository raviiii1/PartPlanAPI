package com.party.plan;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
	
	static int ans;
	
	static class Ele
    {
        int x = 0;
        int y = 0;
        Ele(int x,int y)
        {
            this.x = x;
            this.y = y;
        }
    }
	
	public static void main(String[] args) {
		int arr[][] = {	{2, 1, 0, 2, 1},
		                {1, 0, 1, 2, 1},
		                {1, 0, 0, 2, 1}};
		int ans = rotOranges(arr);
			System.out.println(ans);
	}

	private static int rotOranges(int[][] arr) {
		Queue<Ele> que = new LinkedList<>();
		Ele tmp;
		for(int i=0;i<3;i++)
			for(int j = 0;j<5; j++){
				if(arr[i][j] == 2)
					que.add(new Ele(i,j));
			}
		que.add(new Ele(-1,-1));
		
		while(!que.isEmpty()){
			tmp = que.peek();
			boolean flag = false;
			while(!isDelimit(tmp)){
				
				flag = processNeibhour(tmp, -1, 0, arr,flag);
				flag = processNeibhour(tmp, 1, 0, arr, flag);
				flag = processNeibhour(tmp, 0, -1, arr,flag);
				flag = processNeibhour(tmp, 0, 1, arr, flag);
				
				que.remove();
			}
			que.remove();
		}
		
		return 0;
		
	}
	
	private static boolean isDelimit(Ele tmp) {
		if(tmp.x == -1 && tmp.y == -1)
			return false;
		return true;
	}

	private static boolean processNeibhour(Ele tmp, int i, int j, int[][] arr, boolean flag){
		if(isValid(tmp.x+i, tmp.y+j) && arr[tmp.x+i][tmp.y+j] == 1){
			if(!flag){
				flag = true;
				ans++;
			}
		}
		return flag;
	}

	private static boolean isValid(int i, int j) {
		return (i>0 && j>0 && i<3 && j<5);
	}
}
