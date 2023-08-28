package seasar2.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

/**
 * ExecutionInterceptorクラス. <br>
 * ExecutionInterceptorクラスは、アプリケーションが実行している処理に前後処理をするクラスです。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class ExecutionInterceptor extends AbstractInterceptor {

	/** ログを制御するオブジェクトを表します */
	Logger logger = Logger.getLogger(getClass());

	/**
	 * アプリケーションが実行している処理に前後処理を実行します。
	 * 
	 * @return Object
	 */
	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {

		String className = getTargetClass(arg0).getSimpleName();
		String methodName = arg0.getMethod().getName();
		String actionName = "[クラス名：" + className + "] [" + "メソッド名：" + methodName + "]";

		// メソッド開始前処理
		logger.info(actionName + "を開始します");

		Object ret = arg0.proceed();

		// メソッド終了後処理
		logger.info(actionName + "が終了しました");

		return ret;
	}

}
