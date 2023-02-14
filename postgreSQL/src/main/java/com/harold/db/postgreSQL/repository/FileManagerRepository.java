package com.harold.db.postgreSQL.repository;

import com.harold.db.postgreSQL.model.FileH;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileManagerRepository extends JpaRepository<FileH, String> {
}
