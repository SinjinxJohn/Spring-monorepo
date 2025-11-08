package com.example.todo.Repository;

import com.example.todo.Entities.Todo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

    boolean existsByTitle(String Title);

    @Modifying
    @Transactional
    @Query("Update Todo t SET t.description = :description,t.title = :title,t.updatedAt = CURRENT_TIMESTAMP WHERE t.ID = :ID")
    int updateTaskById(int ID,String description,String title);

    @Modifying
    @Transactional
    @Query("DELETE FROM Todo t WHERE t.ID = :ID")
    int deleteByID(@Param("ID") int ID);

}
