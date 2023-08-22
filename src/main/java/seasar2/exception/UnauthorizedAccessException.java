package seasar2.exception;

import org.seasar.framework.exception.SRuntimeException;

/**
 * UnauthorizedAccessExceptionクラス. <br>
 * UnauthorizedAccessExceptionクラスは、不正アクセス例外を表します。
 *
 * @author Ryo.inoue
 * @version 1.00
 */
public class UnauthorizedAccessException extends SRuntimeException {
	
	/** デフォルトコンストラクタ */
	public UnauthorizedAccessException(String msg) {
		super(msg);
	}

}
