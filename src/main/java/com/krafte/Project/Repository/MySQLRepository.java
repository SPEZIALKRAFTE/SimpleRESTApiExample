package com.krafte.Project.Repository;

import com.krafte.Project.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySQLRepository extends JpaRepository<Address, Integer> {

}
