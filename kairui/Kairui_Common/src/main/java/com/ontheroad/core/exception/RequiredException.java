package com.ontheroad.core.exception;

/**       
 * <p>项目名称：Health_Scale_Common<br>
 * 类名称：RequiredException<br>
 * 类描述：缺少必须的参数<br>
 *</p>
 * @author 邓强<br>   
 * @date 2017年2月20日下午2:12:35<br>     
 * @version V1.0 <br>     
 */

public class RequiredException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/**  @Fields fieldName :缺少的必须参数的名称*/ 
	private String fieldName = "";
	
	/**  
	* <p>Title:缺少必须的参数</p>  
	* <p>Description: 需要参数名，和异常</p>  
	* @param fieldName
	* @param e  
	*/ 
	public RequiredException(String fieldName, Throwable e) {
		super("缺少参数"+fieldName,e);
		fieldName = this.fieldName;
	}

	/**  
	* <p>Title:缺少必须的参数</p>  
	* <p>Description: 需要缺少的必须参数的名称</p>  
	* @param fieldName
	*/ 
	public RequiredException(String fieldName) {
		super("缺少参数"+fieldName);
		fieldName = this.fieldName;
	}

	/**  
	 * @return the fieldName  
	 */
	public String getFieldName() {
		return fieldName;
	}
}
