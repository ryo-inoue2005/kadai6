package seasar2.entity;

import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import seasar2.entity.OmikujiboxNames._OmikujiboxNames;

/**
 * {@link Result}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2023/09/15 13:38:28")
public class ResultNames {

    /**
     * uranaiDateのプロパティ名を返します。
     * 
     * @return uranaiDateのプロパティ名
     */
    public static PropertyName<Date> uranaiDate() {
        return new PropertyName<Date>("uranaiDate");
    }

    /**
     * birthdayのプロパティ名を返します。
     * 
     * @return birthdayのプロパティ名
     */
    public static PropertyName<Date> birthday() {
        return new PropertyName<Date>("birthday");
    }

    /**
     * omikujiCodeのプロパティ名を返します。
     * 
     * @return omikujiCodeのプロパティ名
     */
    public static PropertyName<Integer> omikujiCode() {
        return new PropertyName<Integer>("omikujiCode");
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
     * omikujiboxのプロパティ名を返します。
     * 
     * @return omikujiboxのプロパティ名
     */
    public static _OmikujiboxNames omikujibox() {
        return new _OmikujiboxNames("omikujibox");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _ResultNames extends PropertyName<Result> {

        /**
         * インスタンスを構築します。
         */
        public _ResultNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _ResultNames(final String name) {
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
        public _ResultNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * uranaiDateのプロパティ名を返します。
         *
         * @return uranaiDateのプロパティ名
         */
        public PropertyName<Date> uranaiDate() {
            return new PropertyName<Date>(this, "uranaiDate");
        }

        /**
         * birthdayのプロパティ名を返します。
         *
         * @return birthdayのプロパティ名
         */
        public PropertyName<Date> birthday() {
            return new PropertyName<Date>(this, "birthday");
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
         * omikujiboxのプロパティ名を返します。
         * 
         * @return omikujiboxのプロパティ名
         */
        public _OmikujiboxNames omikujibox() {
            return new _OmikujiboxNames(this, "omikujibox");
        }
    }
}