package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ReceptKompDAO;
import dto01917.ReceptKompDTO;

public class MySQLReceptKompDAO implements ReceptKompDAO {

	@Override
	public ReceptKompDTO getReceptKomp(int receptkompId, int raavareId)
			throws DALException {
		ReceptKompDTO resReceptKomp = null;
		ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent WHERE recept_id = " + receptkompId);
		try
		{
			while (rs.next()) 
			{
				
				resReceptKomp = new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance"));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return resReceptKomp;
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptkompId)
			throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent WHERE recept_id = " + receptkompId);
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM receptkomponent");
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent)
			throws DALException {
		Connector.doUpdate(
				"INSERT INTO receptkomponent(receptkomp_id, receptkomp_navn, nom_netto, tolerance) VALUES " +
				"(" + receptkomponent.getReceptId() + ", '" + receptkomponent.getRaavareId() + ", '" + receptkomponent.getNomNetto() 
				+ ", '" + receptkomponent.getTolerance() + "')"
			);
	}

	@Override
	public void updateReceptKomp(ReceptKompDTO receptkomponent)
			throws DALException {
		Connector.doUpdate(
				"UPDATE receptkomponent SET  recept_id = '" + receptkomponent.getReceptId() + "', raavare_id = '" 
				+ receptkomponent.getRaavareId() + "', nom_netto = '" + receptkomponent.getNomNetto() + "', tolerance = '" 
				+ receptkomponent.getTolerance() + "' WHERE recept_id = " + receptkomponent.getReceptId()
		);
	}

}
