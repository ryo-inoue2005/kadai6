package seasar2.entity;

import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import seasar2.entity.CustomerInfoNames._CustomerInfoNames;
import seasar2.entity.ResultNames._ResultNames;
import seasar2.entity.UnseimasterNames._UnseimasterNames;

/**
 * {@link Omikujibox}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2023/10/17 11:38:49")
public class OmikujiboxNames {

    /**
     * omikujiCodeのプロパティ名を返します。
     * 
     * @return omikujiCodeのプロパティ名
     */
    public static PropertyName<Integer> omikujiCode() {
        return new PropertyName<Integer>("omikujiCode");
    }

    /**
     * unseiCodeのプロパティ名を返します。
     * 
     * @return unseiCodeのプロパティ名
     */
    public static PropertyName<String> unseiCode() {
        return new PropertyName<String>("unseiCode");
    }

    /**
     * negaigotoのプロパティ名を返します。
     * 
     * @return negaigotoのプロパティ名
     */
    public static PropertyName<String> negaigoto() {
        return new PropertyName<String>("negaigoto");
    }

    /**
     * akinaiのプロパティ名を返します。
     * 
     * @return akinaiのプロパティ名
     */
    public static PropertyName<String> akinai() {
        return new PropertyName<String>("akinai");
    }

    /**
     * gakumonのプロパティ名を返します。
     * 
     * @return gakumonのプロパティ名
     */
    public static PropertyName<String> gakumon() {
        return new PropertyName<String>("gakumon");
    }

    /**
     * updateByのプロパティ名を返します。
     * 
     * @return updateByのプロパティ名
     */
    public static PropertyName<String> updateBy() {
        return new PropertyName<String>("updateBy");
    }

    /**
     * updateDateのプロパティ名を返します。
     * 
     * @return updateDateのプロパティ名
     */
    public static PropertyName<Date> updateDate() {
        return new PropertyName<Date>("updateDate");
    }

    /**
     * createByのプロパティ名を返します。
     * 
     * @return createByのプロパティ名
     */
    public static PropertyName<String> createBy() {
        return new PropertyName<String>("createBy");
    }

    /**
     * createDateのプロパティ名を返します。
     * 
     * @return createDateのプロパティ名
     */
    public static PropertyName<Date> createDate() {
        return new PropertyName<Date>("createDate");
    }

    /**
     * unseimasterのプロパティ名を返します。
     * 
     * @return unseimasterのプロパティ名
     */
    public static _UnseimasterNames unseimaster() {
        return new _UnseimasterNames("unseimaster");
    }

    /**
     * resultListのプロパティ名を返します。
     * 
     * @return resultListのプロパティ名
     */
    public static _ResultNames resultList() {
        return new _ResultNames("resultList");
    }

    /**
     * customerInfoListのプロパティ名を返します。
     * 
     * @return customerInfoListのプロパティ名
     */
    public static _CustomerInfoNames customerInfoList() {
        return new _CustomerInfoNames("customerInfoList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _OmikujiboxNames extends PropertyName<Omikujibox> {

        /**
         * インスタンスを構築します。
         */
        public _OmikujiboxNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _OmikujiboxNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _OmikujiboxNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * omikujiCodeのプロパティ名を返します。
         *
         * @return omikujiCodeのプロパティ名
         */
        public PropertyName<Integer> omikujiCode() {
            return new PropertyName<Integer>(this, "omikujiCode");
        }

        /**
         * unseiCodeのプロパティ名を返します。
         *
         * @return unseiCodeのプロパティ名
         */
        public PropertyName<String> unseiCode() {
            return new PropertyName<String>(this, "unseiCode");
        }

        /**
         * negaigotoのプロパティ名を返します。
         *
         * @return negaigotoのプロパティ名
         */
        public PropertyName<String> negaigoto() {
            return new PropertyName<String>(this, "negaigoto");
        }

        /**
         * akinaiのプロパティ名を返します。
         *
         * @return akinaiのプロパティ名
         */
        public PropertyName<String> akinai() {
            return new PropertyName<String>(this, "akinai");
        }

        /**
         * gakumonのプロパティ名を返します。
         *
         * @return gakumonのプロパティ名
         */
        public PropertyName<String> gakumon() {
            return new PropertyName<String>(this, "gakumon");
        }

        /**
         * updateByのプロパティ名を返します。
         *
         * @return updateByのプロパティ名
         */
        public PropertyName<String> updateBy() {
            return new PropertyName<String>(this, "updateBy");
        }

        /**
         * updateDateのプロパティ名を返します。
         *
         * @return updateDateのプロパティ名
         */
        public PropertyName<Date> updateDate() {
            return new PropertyName<Date>(this, "updateDate");
        }

        /**
         * createByのプロパティ名を返します。
         *
         * @return createByのプロパティ名
         */
        public PropertyName<String> createBy() {
            return new PropertyName<String>(this, "createBy");
        }

        /**
         * createDateのプロパティ名を返します。
         *
         * @return createDateのプロパティ名
         */
        public PropertyName<Date> createDate() {
            return new PropertyName<Date>(this, "createDate");
        }

        /**
         * unseimasterのプロパティ名を返します。
         * 
         * @return unseimasterのプロパティ名
         */
        public _UnseimasterNames unseimaster() {
            return new _UnseimasterNames(this, "unseimaster");
        }

        /**
         * resultListのプロパティ名を返します。
         * 
         * @return resultListのプロパティ名
         */
        public _ResultNames resultList() {
            return new _ResultNames(this, "resultList");
        }

        /**
         * customerInfoListのプロパティ名を返します。
         * 
         * @return customerInfoListのプロパティ名
         */
        public _CustomerInfoNames customerInfoList() {
            return new _CustomerInfoNames(this, "customerInfoList");
        }
    }
}