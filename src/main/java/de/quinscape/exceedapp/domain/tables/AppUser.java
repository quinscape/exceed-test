/**
 * This class is generated by jOOQ
 */
package de.quinscape.exceedapp.domain.tables;


import de.quinscape.exceedapp.domain.Keys;
import de.quinscape.exceedapp.domain.Shipping;
import de.quinscape.exceedapp.domain.tables.records.AppUserRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AppUser extends TableImpl<AppUserRecord> {

	private static final long serialVersionUID = 1948262205;

	/**
	 * The reference instance of <code>shipping.app_user</code>
	 */
	public static final AppUser APP_USER = new AppUser();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<AppUserRecord> getRecordType() {
		return AppUserRecord.class;
	}

	/**
	 * The column <code>shipping.app_user.id</code>.
	 */
	public final TableField<AppUserRecord, String> ID = createField("id", org.jooq.impl.SQLDataType.VARCHAR.length(36).nullable(false), this, "");

	/**
	 * The column <code>shipping.app_user.login</code>.
	 */
	public final TableField<AppUserRecord, String> LOGIN = createField("login", org.jooq.impl.SQLDataType.VARCHAR.length(64).nullable(false), this, "");

	/**
	 * The column <code>shipping.app_user.password</code>.
	 */
	public final TableField<AppUserRecord, String> PASSWORD = createField("password", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * The column <code>shipping.app_user.created</code>.
	 */
	public final TableField<AppUserRecord, Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

	/**
	 * The column <code>shipping.app_user.last_login</code>.
	 */
	public final TableField<AppUserRecord, Timestamp> LAST_LOGIN = createField("last_login", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

	/**
	 * The column <code>shipping.app_user.roles</code>.
	 */
	public final TableField<AppUserRecord, String> ROLES = createField("roles", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * Create a <code>shipping.app_user</code> table reference
	 */
	public AppUser() {
		this("app_user", null);
	}

	/**
	 * Create an aliased <code>shipping.app_user</code> table reference
	 */
	public AppUser(String alias) {
		this(alias, APP_USER);
	}

	private AppUser(String alias, Table<AppUserRecord> aliased) {
		this(alias, aliased, null);
	}

	private AppUser(String alias, Table<AppUserRecord> aliased, Field<?>[] parameters) {
		super(alias, Shipping.SHIPPING, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<AppUserRecord> getPrimaryKey() {
		return Keys.PK_APP_USER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<AppUserRecord>> getKeys() {
		return Arrays.<UniqueKey<AppUserRecord>>asList(Keys.PK_APP_USER);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AppUser as(String alias) {
		return new AppUser(alias, this);
	}

	/**
	 * Rename this table
	 */
	public AppUser rename(String name) {
		return new AppUser(name, null);
	}
}