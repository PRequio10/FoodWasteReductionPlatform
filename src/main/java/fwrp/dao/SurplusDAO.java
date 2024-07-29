package fwrp.dao;

import java.sql.*;
import fwrp.model.SurplusFood;

/**
 * Data Access Object for the surplus food.
 * 
 * @author John Philip William Requio
 */
public interface SurplusDAO {
	
	public void insertFood(SurplusFood cFood) throws SQLException;
	public void foodTable()throws SQLException;
	public void updateFood(SurplusFood uFood, int foodID) throws SQLException;
}
