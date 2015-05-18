package br.com.giselepm.healthish.service.impl;

import br.com.giselepm.healthish.dao.ICrudDao;
import br.com.giselepm.healthish.entity.Doctor;
import br.com.giselepm.healthish.service.ICrudService;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CrudServiceTest {

    ICrudDao crudDaoMock;
    ICrudService crudService;

    @Before
    public void setup(){
        crudDaoMock = mock(ICrudDao.class);
        crudService = new CrudService(crudDaoMock);
    }

    @Test
    public void findAllReturnsWhateverIsReturnedByDao() throws Exception {
        when(crudDaoMock.findAll(Doctor.class)).thenReturn(new ArrayList<Doctor>(){{
            add(new Doctor(1, "Phil"));
            add(new Doctor(2, "Arnold"));
        }});

        List<Doctor> allDoctors = crudService.findAll(Doctor.class);
        Collection<Long> allIds = CollectionUtils.collect(allDoctors, new BeanToPropertyValueTransformer("id"));
        Collection<Long> allNames = CollectionUtils.collect(allDoctors, new BeanToPropertyValueTransformer("name"));

        assertEquals(2, allDoctors.size());
        assertTrue(allIds.containsAll(new ArrayList<Object>() {{
            add(1L);
            add(2L);
        }}));
        assertTrue(allNames.containsAll(new ArrayList<Object>(){{
            add("Phil");
            add("Arnold");
        }}));
    }

    @Test
    public void getReturnsWhateverComesFromDao() throws Exception {
        when(crudDaoMock.get(Doctor.class, 1L)).thenReturn(new Doctor(1, "Steve"));

        Doctor d = crudService.get(Doctor.class, 1L);

        assertEquals(d, new Doctor(1, "Steve"));
    }

    @Test
    public void saveCallsDaoAndReturnsWhateverDaoReturns() throws Exception {
        Doctor d = new Doctor();

        d.setName("name");
        d.setStreet("street");
        d.setStreetNo("streetNo");
        d.setCity("city");
        d.setZip("10829");

        when(crudDaoMock.save(d)).thenReturn(new Doctor(1, "other name"));

        Doctor doctorReturn = crudService.save(d);

        assertEquals(doctorReturn, new Doctor(1, "other name"));
    }

    @Test
    public void updateCallsDaoAndReturnsWhateverDaoReturns() throws Exception {
        Doctor d = new Doctor(1, "name");
        when(crudDaoMock.update(d)).thenReturn(new Doctor(1, "other name"));

        Doctor doctorReturn = crudService.update(d);

        assertEquals(doctorReturn, new Doctor(1, "other name"));
    }

    @Test
    public void deleteCallsDao() throws Exception {
        crudService.delete(any(Doctor.class));
        verify(crudDaoMock, times(1)).delete(any(Doctor.class));
    }
}