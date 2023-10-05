package seasar2.entity;

import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import seasar2.entity.OmikujiboxNames._OmikujiboxNames;

/**
 * {@link Unseimaster}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2023/09/25 11:41:45")
public class UnseimasterNames {

    /**
     * unseiCodeのプロパティ名を返します。
     * 
     * @return unseiCodeのプロパティ名
     */
    public static PropertyName<String> unseiCode() {
        return new PropertyName<String>("unseiCode");
    }

    /**
     * unseiNameのプロパティ名を返します。
     * 
     * @return unseiNameのプロパティ名
     */
    public static PropertyName<String> unseiName() {
        return new PropertyName<String>("unseiName");
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
     * omikujiListのプロパティ名を返します。
     * 
     * @return omikujiListのプロパティ名
     */
    public static _OmikujiboxNames omikujiList() {
        return new _OmikujiboxNames("omikujiList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _UnseimasterNames extends PropertyName<Unseimaster> {

        /**
         * インスタンスを構築します。
         */
        public _UnseimasterNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _UnseimasterNames(final String name) {
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
        public _UnseimasterNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * unseiNameのプロパティ名を返します。
         *
         * @return unseiNameのプロパティ名
         */
        public PropertyName<String> unseiName() {
            return new PropertyName<String>(this, "unseiName");
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
         * omikujiListのプロパティ名を返します。
         * 
         * @return omikujiListのプロパティ名
         */
        public _OmikujiboxNames omikujiList() {
            return new _OmikujiboxNames(this, "omikujiList");
        }
    }
}