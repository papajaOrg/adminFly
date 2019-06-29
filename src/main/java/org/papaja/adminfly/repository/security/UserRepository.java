package org.papaja.adminfly.repository.security;

import org.hibernate.query.Query;
import org.papaja.adminfly.entity.security.User;
import org.papaja.adminfly.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserRepository extends AbstractRepository<User> {

    public User getUser(String name) {
        CriteriaBuilder     builder = criteriaBuilder();
        CriteriaQuery<User> query   = builder.createQuery(getReflection());
        Root<User>          root    = query.from(getReflection());

        query.select(root);
        query.where(builder.equal(root.get("username"), name));

        return uniqueResult(query);
    }

    public User getUser(Integer id) {
        return get(id);
    }

    public List<User> getUsers() {
        return getList();
    }

    public Query<User> getUsersQuery() {
        return createQuery();
    }

    @Override
    public Class<User> getReflection() {
        return User.class;
    }
}
