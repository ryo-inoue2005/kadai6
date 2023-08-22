package seasar2.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Validator;

/**
 * OmikujiDateTypeアノテーション. <br>
 * OmikujiDateTypeアノテーションは、入力された日付が正しい形式かチェックします。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Validator("isDate")
public @interface OmikujiDateType {

	Msg msg() default @Msg(key = "errors.isdate");

	Arg arg0() default @Arg(key = "");

	String target() default "";

}
