package com.ontheroad.system.entity;

/**
 * 联合主键测试
 * @author db
 *
 */
public class PKS {
	    //用户ID
	    private Long userId; 			
			
		//登录名称
		private String userName;

		public Long  getUserId(){
			return this.userId;
		}

		public void setUserId(Long userId){
			this.userId=userId;
		}

		public String  getUserName(){
			return this.userName;
		}

		public void setUserName(String userName){
			this.userName = userName == null ? null : userName.trim();
		}
		
		
		public static void main(String args[]){
			String arr[] = {};
			
//			System.out.println(Ognl.isEmpty(arr));
		}
}
