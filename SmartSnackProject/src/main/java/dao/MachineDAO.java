package dao;

import java.util.List;

import entity.Machine;

public interface MachineDAO {
	
	public Machine getMachineById(String id);
	
	public  List<Machine> getAllMachines();
	
	public boolean addMachine(Machine machine);
	
	public boolean modifyMachine(Machine machine);
	
	public boolean deleteMachine(Machine machine);
	
	public List<Machine> getSoldOutProductMachines();

	public List<Machine> getSoldOutCoinMachines();

	public List<Machine> getOfflineMachines();


}
