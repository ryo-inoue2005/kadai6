package seasar2.entity;

import javax.annotation.Generated;
import seasar2.entity.CustomerInfoNames._CustomerInfoNames;
import seasar2.entity.OmikujiboxNames._OmikujiboxNames;
import seasar2.entity.ResultNames._ResultNames;
import seasar2.entity.UnseimasterNames._UnseimasterNames;
import seasar2.entity.ZipcodeNames._ZipcodeNames;

/**
 * 名前クラスの集約です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesAggregateModelFactoryImpl"}, date = "2023/09/25 11:41:45")
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
     * {@link Zipcode}の名前クラスを返します。
     * 
     * @return Zipcodeの名前クラス
     */
    public static _ZipcodeNames zipcode() {
        return new _ZipcodeNames();
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

    /**
     * {@link CustomerInfo}の名前クラスを返します。
     * 
     * @return CustomerInfoの名前クラス
     */
    public static _CustomerInfoNames customerInfo() {
        return new _CustomerInfoNames();
    }
}