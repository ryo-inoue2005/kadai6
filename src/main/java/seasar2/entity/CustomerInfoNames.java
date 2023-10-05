package seasar2.entity;

import java.util.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;
import seasar2.entity.OmikujiboxNames._OmikujiboxNames;

/**
 * {@link CustomerInfo}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2023/09/25 11:41:45")
public class CustomerInfoNames {

    /**
     * customerIdのプロパティ名を返します。
     * 
     * @return customerIdのプロパティ名
     */
    public static PropertyName<Integer> customerId() {
        return new PropertyName<Integer>("customerId");
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
     * orderDateのプロパティ名を返します。
     * 
     * @return orderDateのプロパティ名
     */
    public static PropertyName<Date> orderDate() {
        return new PropertyName<Date>("orderDate");
    }

    /**
     * lastNameのプロパティ名を返します。
     * 
     * @return lastNameのプロパティ名
     */
    public static PropertyName<String> lastName() {
        return new PropertyName<String>("lastName");
    }

    /**
     * firstNameのプロパティ名を返します。
     * 
     * @return firstNameのプロパティ名
     */
    public static PropertyName<String> firstName() {
        return new PropertyName<String>("firstName");
    }

    /**
     * zipCodeのプロパティ名を返します。
     * 
     * @return zipCodeのプロパティ名
     */
    public static PropertyName<String> zipCode() {
        return new PropertyName<String>("zipCode");
    }

    /**
     * prefectureのプロパティ名を返します。
     * 
     * @return prefectureのプロパティ名
     */
    public static PropertyName<String> prefecture() {
        return new PropertyName<String>("prefecture");
    }

    /**
     * cityのプロパティ名を返します。
     * 
     * @return cityのプロパティ名
     */
    public static PropertyName<String> city() {
        return new PropertyName<String>("city");
    }

    /**
     * addressのプロパティ名を返します。
     * 
     * @return addressのプロパティ名
     */
    public static PropertyName<String> address() {
        return new PropertyName<String>("address");
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
    public static class _CustomerInfoNames extends PropertyName<CustomerInfo> {

        /**
         * インスタンスを構築します。
         */
        public _CustomerInfoNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _CustomerInfoNames(final String name) {
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
        public _CustomerInfoNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * customerIdのプロパティ名を返します。
         *
         * @return customerIdのプロパティ名
         */
        public PropertyName<Integer> customerId() {
            return new PropertyName<Integer>(this, "customerId");
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
         * orderDateのプロパティ名を返します。
         *
         * @return orderDateのプロパティ名
         */
        public PropertyName<Date> orderDate() {
            return new PropertyName<Date>(this, "orderDate");
        }

        /**
         * lastNameのプロパティ名を返します。
         *
         * @return lastNameのプロパティ名
         */
        public PropertyName<String> lastName() {
            return new PropertyName<String>(this, "lastName");
        }

        /**
         * firstNameのプロパティ名を返します。
         *
         * @return firstNameのプロパティ名
         */
        public PropertyName<String> firstName() {
            return new PropertyName<String>(this, "firstName");
        }

        /**
         * zipCodeのプロパティ名を返します。
         *
         * @return zipCodeのプロパティ名
         */
        public PropertyName<String> zipCode() {
            return new PropertyName<String>(this, "zipCode");
        }

        /**
         * prefectureのプロパティ名を返します。
         *
         * @return prefectureのプロパティ名
         */
        public PropertyName<String> prefecture() {
            return new PropertyName<String>(this, "prefecture");
        }

        /**
         * cityのプロパティ名を返します。
         *
         * @return cityのプロパティ名
         */
        public PropertyName<String> city() {
            return new PropertyName<String>(this, "city");
        }

        /**
         * addressのプロパティ名を返します。
         *
         * @return addressのプロパティ名
         */
        public PropertyName<String> address() {
            return new PropertyName<String>(this, "address");
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