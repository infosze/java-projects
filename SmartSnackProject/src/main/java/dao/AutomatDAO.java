package dao;

import java.util.List;

import entity.Automat;

public interface AutomatDAO {
	
	public List<Automat> getSoldOutProductMachines();

	public List<Automat> getSoldOutCoinMachines();

	public List<Automat> getOfflineMachines();


}
