package consumer.dao;

import consumer.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao  extends JpaRepository<Role,Integer> {
    @Modifying
    @Query(value = "INSERT INTO sys_role(name) VALUES(?)",nativeQuery = true)
    void insert(String name);
}
