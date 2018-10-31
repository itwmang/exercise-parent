package com.spring.cloud.framework.utils.constant;

/**
 * @author liuweijw
 */
public interface CommonConstant {

	/**
	 * token请求头名称
	 */
	String	REQ_HEADER						= "Authorization";


	/**
	 * 删除
	 */
	Integer	STATUS_DEL						= 1;

	/**
	 * 正常
	 */
	Integer	STATUS_NORMAL					= 0;

	/**
	 * 锁定
	 */
	Integer	STATUS_LOCK						= 9;

	/**
	 * 菜单
	 */
	String	MENU							= "0";

	/**
	 * 按钮
	 */
	String	BUTTON							= "1";

	/**
	 * 删除标记
	 */
	String	DEL_FLAG						= "del_flag";

	/**
	 * 编码
	 */
	String	UTF8							= "UTF-8";

	/**
	 * JSON 资源
	 */
	String	CONTENT_TYPE					= "application/json; charset=utf-8";

	/**
	 * 服务标识
	 */
	String	SERVICE_ID						= "serviceId";

	/**
	 * JWT 定义的user
	 */
	String	KEY_USER						= "user";

	/**
	 * JWT 手机号码登录
	 */
	String	SPRING_SECURITY_FORM_MOBILE_KEY	= "mobile";

//	swagger-api-version
	String SWAGGER_API_VERSION = "0.0.1";

	/**
	 * 基础分页数量
	 */
	Integer PAGE_NUM = 10;
}
