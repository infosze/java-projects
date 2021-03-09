package dao;

import java.util.List;

import entity.Automat;

public interface AutomatErrorDAO {

	List<Automat> findSoldOutCoinMachines();

	List<Automat> findSoldOutProductMachines();

	List<Automat> findWrongMachines();

}
