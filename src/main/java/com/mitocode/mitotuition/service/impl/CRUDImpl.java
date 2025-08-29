package com.mitocode.mitotuition.service.impl;
import com.mitocode.mitotuition.exception.ModelNotFoundException;
import com.mitocode.mitotuition.repository.IGenericRepo;
import com.mitocode.mitotuition.service.ICRUD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

@Service
@RequiredArgsConstructor
public  abstract  class CRUDImpl<T,ID> implements ICRUD<T,ID> {

    
    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(ID id, T t) throws Exception {
        //java reflection
        String className = t.getClass().getSimpleName();
        //set identidad
        String methodName = "setId" + className;
        Method setIdMethod = t.getClass().getMethod(methodName, id.getClass());
        setIdMethod.invoke(t, id);





        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " +id));
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id ));
        getRepo().deleteById(id);
    }
}
