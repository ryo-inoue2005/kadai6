package seasar2.entity;

import javax.annotation.Generated;

import seasar2.entity.OmikujiboxNames._OmikujiboxNames;
import seasar2.entity.ResultNames._ResultNames;
import seasar2.entity.UnseimasterNames._UnseimasterNames;

/**
 * 名前クラスの集約です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesAggregateModelFactoryImpl"}, date = "2023/08/28 9:25:32")
public class Names {

    /**
     * {@link Unseimaster}の名前クラスを返します。
     * 
     * @return Unseimasterの名前クラス
     */
    public static _UnseimasterNames unseimaster() {
        return new _UnseimasterNames();
    }

    /**
     * {@link Result}の名前クラスを返します。
     * 
     * @return Resultの名前クラス
     */
    public static _ResultNames result() {
        return new _ResultNames();
    }

    /**
     * {@link Omikujibox}の名前クラスを返します。
     * 
     * @return Omikujiboxの名前クラス
     */
    public static _OmikujiboxNames omikujibox() {
        return new _OmikujiboxNames();
    }
}