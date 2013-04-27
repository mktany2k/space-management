@TypeDefs({
    @TypeDef(name = "local_date_time", defaultForType = LocalDateTime.class, typeClass = PersistentLocalDateTime.class)})
package com.osm.model;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime;
import org.joda.time.LocalDateTime;
