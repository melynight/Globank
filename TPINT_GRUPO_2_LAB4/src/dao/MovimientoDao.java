package dao;

import java.util.ArrayList;

import entidades.Movimiento;

public interface MovimientoDao {

	public boolean insert (Movimiento movimiento);
	public boolean delete (Movimiento movimiento);
	public ArrayList<Movimiento> readAll();
}
