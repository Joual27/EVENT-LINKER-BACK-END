package org.youcode.EventLinkerAPI.shared.utils.validators.interfaces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueFieldValidator implements ConstraintValidator<Unique , Object> {

    @PersistenceContext
    private EntityManager em;

    private Class<?> entity;
    private String field;

    public void initialize(Unique constraintAnnotation){
        this.entity = constraintAnnotation.entity();
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value , ConstraintValidatorContext context){
        if (value == null){
            return true;
        }

        String query = String.format("SELECT COUNT(e) FROM %s e WHERE LOWER(e.%s) = LOWER(:value) " , entity.getSimpleName() , field);
        Long count = em.createQuery(query , Long.class)
                .setParameter("value" , value)
                .getSingleResult();

        return count == 0;
    }
}

