package com.ontheroad.utils;

/**
 * 100：参数异常 200：业务异常 300：服务器异常
 * 
 * @author Administrator
 * 
 */
public class MessageConstant {
	
	/**
	 * 用户注册
	 * 
	 */
	public interface R_USER_INFO_REG{
		public static final String METHOD = "daboo.romoom.user.reg_";
		/**
		 * 手机不能为空
		 */
		public static final String ERROR_100 = "100";
		/**
		 * 验证码不能为空
		 */
		public static final String ERROR_101 = "101";
		/**
		 * 密码不能为空
		 */
		public static final String ERROR_102 = "102";
		
		/**
		 * AccessKey不能为空
		 */
		public static final String ERROR_103 = "103";
		
		/**
		 * 请输入密码
		 */
		public static final String ERROR_104 = "104";
		/**
		 * 旧密码不能为空
		 */
		public static final String ERROR_105 = "105";
		/**
		 * 新密码不能为空
		 */
		public static final String ERROR_106 = "106";
		
		
		/**
		 * 用户基本信息不合法
		 */
		public static final String ERROR_108 = "108";
		
		/**
		 * 参数为空
		 */
		public static final String ERROR_109 = "109";
		
		/**
		 * 日期格式不合法
		 */
		public static final String ERROR_110 = "110";
		
		/**
		 * 您输入的手机未注册或密码错误
		 */
		public static final String ERROR_200 = "200";
		/**
		 * 该用户不存在
		 */
		public static final String ERROR_201 = "201";
		
		/**
		 * accessKey 无效
		 */
		public static final String ERROR_202 = "202";
		
		/**
		 * 该用户已存在
		 */
		public static final String ERROR_203 = "203";
		
		/**
		 * 上传文件失败
		 */
		public static final String ERROR_204 = "204";
		
		/**
		 * 密码错误，请重试
		 */
		public static final String ERROR_206 = "206";
		
		/**
		 * 远程http请求异常
		 */
		public static final String ERROR_207 = "207";
		
		/**
		 * 尊敬的用户请直接登录
		 */
		public static final String ERROR_208 = "208";
		
		
	}
	
	
	/**
	 * 用户验证码
	 * 
	 */
	public interface R_USER_CODE_LOGIN{
		public static final String METHOD = "daboo.romoom.user.code.login_";
		/**
		 * 验证码已发送，请稍后再试
		 */
		public static final String ERROR_200 = "200";
		
		/**
		 * 发送次数过多，请稍后再试
		 */
		public static final String ERROR_201 = "201";
		
		/**
		 * 缓存信息失败
		 */
		public static final String ERROR_202 = "202";
		
		/**
		 * 发送验证码失败
		 */
		public static final String ERROR_203 = "203";
		
		/**
		 * 验证码错误，请重新输入
		 */
		public static final String ERROR_204 = "204";
		
		/**
		 * 验证码错误次数过多，请稍后重试
		 */
		public static final String ERROR_205 = "205";
		
	}
	
	/**
	 * 用户验证码
	 * 
	 */
	public interface R_USER_CODE{
		public static final String METHOD = "daboo.romoom.user.code_";
		/**
		 * 手机不能为空
		 */
		public static final String ERROR_100 = "100";
		/**
		 * 类型不能为空
		 */
		public static final String ERROR_101 = "101";
		/**
		 * 类型不合法
		 */
		public static final String ERROR_102 = "102";
		/**
		 * 业务类型不能为空
		 */
		public static final String ERROR_103 = "103";
		/**
		 * 业务类型不合法
		 */
		public static final String ERROR_104 = "104";
		/**
		 * 验证码不能为空
		 */
		public static final String ERROR_105 = "105";
		/**
		 * 是否使用不合法
		 */
		public static final String ERROR_106 = "106";
		/**
		 * 手机mac不能为空
		 */
		public static final String ERROR_107 = "107";
		/**
		 * 该用户不存在
		 */
		public static final String ERROR_200 = "200";
		/**
		 * 该用户已存在
		 */
		public static final String ERROR_201 = "201";
		/**
		 * 验证码发送失败
		 */
		public static final String ERROR_202 = "202";
		/**
		 * 验证码已失效（验证码不正确，请重新输入）
		 */
		public static final String ERROR_203 = "203";
		/**
		 * 验证码不一致（验证码错误）
		 */
		public static final String ERROR_204 = "204";
		/**
		 * 一分钟只能发送一次
		 */
		public static final String ERROR_205 = "205";
		/**
		 * 30分钟只能发送三次
		 */
		public static final String ERROR_206 = "206";
		/**
		 * 一个手机一天只能发送20次
		 */
		public static final String ERROR_207 = "207";
		/**
		 * 缓存验证码失败
		 */
		public static final String ERROR_208 = "208";
		/**
		 * 5分钟输错10次，10分钟之后再试
		 */
		public static final String ERROR_209 = "209";
	}
	
	/**
	 * 重置密码前检验或者注册用户时检测用户是否存在
	 */
	public interface GET_USERCODE {

		public static final String METHOD = "jiuwu.user.usercode.find_";
		/**
		 * 请求对象为空
		 */
		public static final String ERROR_100 = "100";
		/**
		 * 手机号为空
		 */
		public static final String ERROR_101 = "101";
		/**
		 * mac地址为空
		 */
		public static final String ERROR_102 = "102";
		/**
		 * 类型type为空
		 */
		public static final String ERROR_103 = "103";
		/**
		 * 用户手机号不存在
		 */
		public static final String ERROR_200 = "200";

		/**
		 * 超出单日发送验证码次数限制
		 */
		public static final String ERROR_201 = "201";
		/**
		 * 超出发送验证码总次数限制
		 */
		public static final String ERROR_202 = "202";
		/**
		 * 距离上次发送必须间隔60秒
		 */
		public static final String ERROR_203 = "203";
		/**
		 * 30分钟之内只能发送3次验证码
		 */
		public static final String ERROR_204 = "204";
		/**
		 * 此mac发送验证码次数超出限制
		 */
		public static final String ERROR_205 = "205";
		/**
		 * 验证过于频繁，请{0}分钟后发送验证码 
		 */
		public static final String ERROR_206 = "206";
		/**
		 * 重置密码前检验失败
		 */
		public static final String ERROR_207 = "207";
		/**
		 * 用户已经注册
		 */
		public static final String ERROR_208 = "208";
		/**
		 * 发送验证码失败
		 */
		public static final String ERROR_209 = "209";
		/**
		 * 系统错误
		 */
		public static final String ERROR_300 = "300";
		/**
		 * 验证码类型(1为注册)
		 */
		public static final Integer CODE_TYPE_ONE = 1;
		/**
		 * 验证码类型(2为重置)
		 */
		public static final Integer CODE_TYPE_TWO = 2;
		/**
		 * 验证码状态(1为未使用)
		 */
		public static final Integer CODE_STATE_VALID = 1;
		/**
		 * 验证码状态（3为已经验证成功）
		 */
		public static final Integer CODE_STATE_ISUSED = 3;
		/**
		 * 验证码状态（2为已经验证失效）
		 */
		public static final Integer CODE_STATE_INVALID = 2;
		/**
		 * 短信发送成功
		 */
		public static final String SEND_CODE_SUCCESS = "03";
	}
	/**
	 * 文件上传常量
	 * 
	 * @author xiaoyong
	 * 
	 */
	public interface FILE_CONSTANT {
		/**
		 * 聊天过程中发送的各种文件
		 */
		public static final int FILE_SOURCE_CHAT_FILE = 1;
		/**
		 * 聊天过程中的语音、以及拍摄的视频文件
		 */
		public static final int FILE_SOURCE_CHAT_MSG = 2;
		/**
		 * 用户发布的动态图片,压缩，并生成不同尺寸
		 */
		public static final int FILE_SOURCE_TIME_LINE = 3;
		/**
		 * 用户发布的动态图片,压缩，并生成不同尺寸
		 */
		public static final int FILE_SOURCE_USER_ALBUM = 4;
		/**
		 * 用户设置的头像、背景（各种尺寸）
		 */
		public static final int FILE_SOURCE_USER_SET = 5;

		/**
		 * 图片的格式-GIF
		 */
		public static final String FILE_TYPE_GIF = "GIFImageReader";
		/**
		 * 图片的格式-JPEG
		 */
		public static final String FILE_TYPE_JPEG = "JPEGImageReader";
		/**
		 * 图片的格式-PNG
		 */
		public static final String FILE_TYPE_PNG = "PNGImageReader";
		/**
		 * 图片的格式-BMP
		 */
		public static final String FILE_TYPE_BMP = "BMPImageReader";
		/**
		 * 图片后缀-gif
		 */
		public static final String FILE_EXT_GIF = ".gif";

		/**
		 * 图片后缀-bmp
		 */
		public static final String FILE_EXT_BMP = ".bmp";
		/**
		 * 图片后缀-jpg
		 */
		public static final String FILE_EXT_JPG = ".jpg";
		/**
		 * 图片后缀-jpeg
		 */
		public static final String FILE_EXT_JPEG = ".jpeg";
		/**
		 * 图片后缀-PNG
		 */
		public static final String FILE_EXT_PNG = ".png";

	}

	
	
	/**
	 * 获取个人信息
	 * @author zhuchong
	 */
	public interface GET_USER_INFO {
		
		public static final String METHOD = "daboo.user.info.find_";
		/**
		 * 用户信息不存在
		 */
		public static final String ERROR_200 = "200";
		/**
		 * 系统异常
		 */
		public static final String ERROR_300 = "300";
		
	}
}
