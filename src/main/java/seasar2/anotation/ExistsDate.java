package seasar2.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Validator;

/**
 * ExistsDateアノテーション. <br>
 * ExistsDateアノテーションは、日付が存在するかチェックします。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Validator("existsDate")
public @interface ExistsDate {
	
    Msg msg() default @Msg(key = "errors.existsdate");

    Arg arg0() default @Arg(key = "");
	
	String target() default "";

}
