/*
 * This file is generated by jOOQ.
*/
package io.cattle.platform.core.model.tables;


import io.cattle.platform.core.model.CattleTable;
import io.cattle.platform.core.model.Keys;
import io.cattle.platform.core.model.tables.records.IpAddressNicMapRecord;
import io.cattle.platform.db.jooq.converter.DataConverter;
import io.cattle.platform.db.jooq.converter.DateConverter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Schema;
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
        "jOOQ version:3.9.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class IpAddressNicMapTable extends TableImpl<IpAddressNicMapRecord> {

    private static final long serialVersionUID = 2109361325;

    /**
     * The reference instance of <code>cattle.ip_address_nic_map</code>
     */
    public static final IpAddressNicMapTable IP_ADDRESS_NIC_MAP = new IpAddressNicMapTable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<IpAddressNicMapRecord> getRecordType() {
        return IpAddressNicMapRecord.class;
    }

    /**
     * The column <code>cattle.ip_address_nic_map.id</code>.
     */
    public final TableField<IpAddressNicMapRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>cattle.ip_address_nic_map.name</code>.
     */
    public final TableField<IpAddressNicMapRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>cattle.ip_address_nic_map.kind</code>.
     */
    public final TableField<IpAddressNicMapRecord, String> KIND = createField("kind", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>cattle.ip_address_nic_map.uuid</code>.
     */
    public final TableField<IpAddressNicMapRecord, String> UUID = createField("uuid", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

    /**
     * The column <code>cattle.ip_address_nic_map.description</code>.
     */
    public final TableField<IpAddressNicMapRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(1024), this, "");

    /**
     * The column <code>cattle.ip_address_nic_map.state</code>.
     */
    public final TableField<IpAddressNicMapRecord, String> STATE = createField("state", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

    /**
     * The column <code>cattle.ip_address_nic_map.created</code>.
     */
    public final TableField<IpAddressNicMapRecord, Date> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP, this, "", new DateConverter());

    /**
     * The column <code>cattle.ip_address_nic_map.removed</code>.
     */
    public final TableField<IpAddressNicMapRecord, Date> REMOVED = createField("removed", org.jooq.impl.SQLDataType.TIMESTAMP, this, "", new DateConverter());

    /**
     * The column <code>cattle.ip_address_nic_map.remove_time</code>.
     */
    public final TableField<IpAddressNicMapRecord, Date> REMOVE_TIME = createField("remove_time", org.jooq.impl.SQLDataType.TIMESTAMP, this, "", new DateConverter());

    /**
     * The column <code>cattle.ip_address_nic_map.data</code>.
     */
    public final TableField<IpAddressNicMapRecord, Map<String,Object>> DATA = createField("data", org.jooq.impl.SQLDataType.CLOB, this, "", new DataConverter());

    /**
     * The column <code>cattle.ip_address_nic_map.ip_address_id</code>.
     */
    public final TableField<IpAddressNicMapRecord, Long> IP_ADDRESS_ID = createField("ip_address_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>cattle.ip_address_nic_map.nic_id</code>.
     */
    public final TableField<IpAddressNicMapRecord, Long> NIC_ID = createField("nic_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>cattle.ip_address_nic_map</code> table reference
     */
    public IpAddressNicMapTable() {
        this("ip_address_nic_map", null);
    }

    /**
     * Create an aliased <code>cattle.ip_address_nic_map</code> table reference
     */
    public IpAddressNicMapTable(String alias) {
        this(alias, IP_ADDRESS_NIC_MAP);
    }

    private IpAddressNicMapTable(String alias, Table<IpAddressNicMapRecord> aliased) {
        this(alias, aliased, null);
    }

    private IpAddressNicMapTable(String alias, Table<IpAddressNicMapRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return CattleTable.CATTLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<IpAddressNicMapRecord, Long> getIdentity() {
        return Keys.IDENTITY_IP_ADDRESS_NIC_MAP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<IpAddressNicMapRecord> getPrimaryKey() {
        return Keys.KEY_IP_ADDRESS_NIC_MAP_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<IpAddressNicMapRecord>> getKeys() {
        return Arrays.<UniqueKey<IpAddressNicMapRecord>>asList(Keys.KEY_IP_ADDRESS_NIC_MAP_PRIMARY, Keys.KEY_IP_ADDRESS_NIC_MAP_IDX_IP_ADDRESS_NIC_MAP_UUID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<IpAddressNicMapRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<IpAddressNicMapRecord, ?>>asList(Keys.FK_IP_ADDRESS_NIC_MAP__IP_ADDRESS_ID, Keys.FK_IP_ADDRESS_NIC_MAP__NIC_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IpAddressNicMapTable as(String alias) {
        return new IpAddressNicMapTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public IpAddressNicMapTable rename(String name) {
        return new IpAddressNicMapTable(name, null);
    }
}
