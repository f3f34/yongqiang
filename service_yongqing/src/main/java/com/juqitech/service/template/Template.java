package com.juqitech.service.template;

public class Template {
 
    public static String REGIST_EMAIL_TMPLATE = "<img src=\"http://10.15.212.51/images/adv/logo.png\"/><br/><br/>尊敬的%s:<br/><br/>您好！<br/><br/> 请点击智到金融帐号确认链接，以校验您的电子邮箱：<br/><br/> "
    		+ "<a href=\"%s\" target=_blank><img src=\"http://10.15.212.51/images/adv/active_btn.png\" /></a>"
            + "<br/><br/>如果以上按钮无法打开，请把下面的链接复制到浏览器地址栏中<br/>打开:<a href=\"%s\">%s</a>";
    public static String REGIST_EMAIL_URL = System.getProperty("regist_active_mail_url");
    
    
    public static String CHANGE_PWD_SMS_TEMPLATE = "【智到】效验码%s，您正在使用手机找回密码，需要进行效验，请勿向任何人提供您收到的短效验码"; 
    public static String CHANGE_PWD_EMAIL_TMPLATE = "您好，请点击链接修改密码<br/><br/> <a href=\"%s\">%s</a> <br/><br/> 【我知道】";
    public static String CHANGE_PWD_EMAIL_URL = System.getProperty("forget_pwd_mail_url");

    
    public static String SMS_TEMPLATE = "【智到】您的账号已创建成功，账号%s，密码%s";
    
    public static String REGIST_SMS_TEMPLATE = "【牛魔王】验证码：%S，10分钟内有效。如非本人操作请忽略。";
	public static String RESET_SMS_TEMPLATE = "【智到】你的平台密码已通过后台重置为%S，请登录后尽快修改密码，请勿向任何人提供您收到的平台密码。";
}
