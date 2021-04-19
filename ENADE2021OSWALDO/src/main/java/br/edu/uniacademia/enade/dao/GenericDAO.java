/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uniacademia.enade.dao;

import br.edu.uniacademia.enade.model.BaseEnt;
import br.edu.uniacademia.enade.util.PersistenceUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Oswaldo
 * @param <T>
 */
public abstract class GenericDAO<T extends BaseEnt> {
    private static EntityManager entityManager = getEM();
    
    public static EntityManager getEM(){
        return PersistenceUtil.getEntityManager();
    }
    
    public static GenericDAO genericDAO;
    
    
    protected GenericDAO(){
    }
    
    public T buscar(Class<T> t, int id){
        return entityManager.find(t, id);
    }
    
    public List<T> buscarTodos(Class<T> t){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(t);
        query.from(t);
        return entityManager.createQuery(query).getResultList();
    }
    
    public void remover(Class<T> classe, int id){
        T t = buscar(classe, id);
        entityManager.getTransaction().begin();
        if(!entityManager.contains(t)){
            t = entityManager.merge(t);
        }
        entityManager.remove(t);
        entityManager.getTransaction().commit();
    }
    
    public T persistir(T t){
        entityManager.getTransaction().begin();
        try{
            t = entityManager.merge(t);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }
    
    public T atualizar(T t) {
        entityManager.getTransaction().begin();
        try {
            t = entityManager.merge(t);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return t;
    }
    
    public void removeAll(Class<T> t){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from T");
        query.setParameter("T", t);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}
