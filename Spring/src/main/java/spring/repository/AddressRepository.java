package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.model.address.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
