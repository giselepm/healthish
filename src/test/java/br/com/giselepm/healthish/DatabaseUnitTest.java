package br.com.giselepm.healthish;

import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;

@DataSet
@SpringApplicationContext({
        "file:src/main/resources/META-INF/spring/persistence.xml",
        "classpath:testContext.xml"})
public abstract class DatabaseUnitTest extends UnitilsJUnit4 {
}
