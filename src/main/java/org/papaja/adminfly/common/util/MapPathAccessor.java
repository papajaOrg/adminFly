package org.papaja.adminfly.common.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.util.Map;

@SuppressWarnings({"unchecked"})
public class MapPathAccessor<V> implements Accessor<String, V> {

    private Map<String, V> map;

    public MapPathAccessor(Map<String, V> map) {
        this.map = map;
    }

    @Override
    public V get(String key) {
        try {
            return (V) PropertyUtils.getProperty(map, key);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

}
